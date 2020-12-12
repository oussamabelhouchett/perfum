import { Component, OnDestroy, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ProductService } from 'app/entities/product/product.service';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { IProduct } from 'app/shared/model/product.model';
import { Subscription } from 'rxjs';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { ProductDeleteDialogComponent } from 'app/entities/product/product-delete-dialog.component';
import { NewVenteComponent } from 'app/Dashboard/product-list/new-vente/new-vente.component';
import { NewProductComponent } from 'app/Dashboard/product-list/new-product/new-product.component';

@Component({
  selector: 'jhi-product-list',
  templateUrl: './product-list.component.html',
  styleUrls: ['./product-list.component.scss'],
})
export class ProductListComponent implements OnInit, OnDestroy {
  categorieId: number;

  products: IProduct[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;
  currentSearch: string;

  constructor(
    protected productService: ProductService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks,
    protected activatedRoute: ActivatedRoute
  ) {
    this.categorieId = 0;
    this.products = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = false;
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['search']
        ? this.activatedRoute.snapshot.queryParams['search']
        : '';
  }

  loadAll(categorieId?: number): void {
    if (this.currentSearch) {
      this.productService
        .search({
          query: this.currentSearch,
          page: this.page,
          size: this.itemsPerPage,
          sort: this.sort(),
        })
        .subscribe((res: HttpResponse<IProduct[]>) => this.paginateProducts(res.body, res.headers));
      return;
    }
    if (categorieId) {
      this.productService
        .query({
          'categorieId.equals': categorieId,
          page: this.page,
          size: this.itemsPerPage,
          sort: this.sort(),
        })
        .subscribe((res: HttpResponse<IProduct[]>) => this.paginateProducts(res.body, res.headers));
    }
  }

  reset(): void {
    this.page = 0;
    this.products = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  search(query: string): void {
    this.products = [];
    this.links = {
      last: 0,
    };
    this.page = 0;
    if (query) {
      this.predicate = '_score';
      this.ascending = false;
    } else {
      this.predicate = 'id';
      this.ascending = true;
    }
    this.currentSearch = query;
    this.loadAll();
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe(res => {
      this.categorieId = res.id;
      this.loadAll(res.id);
    });

    this.registerChangeInProducts();
  }

  trackId(index: number, item: IProduct): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInProducts(): void {
    this.eventSubscriber = this.eventManager.subscribe('productListModification', () => this.reset());
  }

  delete(product: IProduct): void {
    const modalRef = this.modalService.open(ProductDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.product = product;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateProducts(data: IProduct[] | null, headers: HttpHeaders): void {
    this.products = [];
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.products.push(data[i]);
      }
    }
  }

  changeProductQuantity(product: IProduct): void {
    const index = this.products.findIndex(pro => pro.id === product.id);
    this.products[index] = product;
  }

  addProduct(product: IProduct): void {
    this.products.unshift(product);
  }

  openNewVente(product: IProduct): void {
    const modelRef = this.modalService.open(NewVenteComponent as Component);
    modelRef.componentInstance.modelRef = modelRef;
    modelRef.componentInstance.parent = this;
    modelRef.componentInstance.product = product;
  }

  openNewProduct(): void {
    const modelRef = this.modalService.open(NewProductComponent as Component);
    modelRef.componentInstance.modelRef = modelRef;
    modelRef.componentInstance.parent = this;
    modelRef.componentInstance.categorieId = this.categorieId;
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }
}
