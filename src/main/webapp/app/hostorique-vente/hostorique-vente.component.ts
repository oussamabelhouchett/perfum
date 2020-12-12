import { Component, OnInit, OnDestroy } from '@angular/core';
import { IVente } from 'app/shared/model/vente.model';
import { Subscription } from 'rxjs';
import { VenteService } from 'app/entities/vente/vente.service';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute } from '@angular/router';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { VenteDeleteDialogComponent } from 'app/entities/vente/vente-delete-dialog.component';
import { DatePipe } from '@angular/common';

@Component({
  selector: 'jhi-hostorique-vente',
  templateUrl: './hostorique-vente.component.html',
  styleUrls: ['hostorique-vente.scss'],
})
export class HostoriqueVenteComponent implements OnInit, OnDestroy {
  private dateFormat = 'yyyy-MM-dd';
  fromDate = '';
  toDate = '';
  ventes: IVente[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;
  currentSearch: string;

  constructor(
    protected venteService: VenteService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks,
    protected activatedRoute: ActivatedRoute,
    private datePipe: DatePipe
  ) {
    this.ventes = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['search']
        ? this.activatedRoute.snapshot.queryParams['search']
        : '';
  }

  loadAll(): void {
    if (this.currentSearch) {
      this.venteService
        .search({
          query: this.currentSearch,
          page: this.page,
          size: this.itemsPerPage,
          sort: this.sort(),
        })
        .subscribe((res: HttpResponse<IVente[]>) => this.paginateVentes(res.body, res.headers));
      return;
    }

    this.venteService
      .query({
        'datevente.greaterThanOrEqual': this.fromDate,
        'datevente.lessThanOrEqual': this.toDate,
        page: this.page,
        size: this.itemsPerPage,
        sort: this.sort(),
      })
      .subscribe((res: HttpResponse<IVente[]>) => this.paginateVentes(res.body, res.headers));
  }

  reset(): void {
    this.page = 0;
    this.ventes = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  search(query: string): void {
    this.ventes = [];
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
    this.toDate = this.today();
    this.fromDate = this.previousMonth();
    this.loadAll();
    this.registerChangeInVentes();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: IVente): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInVentes(): void {
    this.eventSubscriber = this.eventManager.subscribe('venteListModification', () => this.reset());
  }

  delete(vente: IVente): void {
    const modalRef = this.modalService.open(VenteDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.vente = vente;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateVentes(data: IVente[] | null, headers: HttpHeaders): void {
    this.ventes = [];
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.ventes.push(data[i]);
      }
    }
  }

  private previousMonth(): string {
    let date = new Date();

    if (date.getMonth() === 0) {
      date = new Date(date.getFullYear() - 1, 11, date.getDate());
    } else {
      date = new Date(date.getFullYear(), date.getMonth() - 1, date.getDate());
    }
    return this.datePipe.transform(date, this.dateFormat)!;
  }

  private today(): string {
    // Today + 1 day - needed if the current day must be included
    const date = new Date();
    date.setDate(date.getDate() + 1);
    return this.datePipe.transform(date, this.dateFormat)!;
  }
}
