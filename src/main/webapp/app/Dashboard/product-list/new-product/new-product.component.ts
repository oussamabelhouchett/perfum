import { Component, OnInit } from '@angular/core';
import { IProduct, Product } from 'app/shared/model/product.model';
import { VenteService } from 'app/entities/vente/vente.service';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ProductService } from 'app/entities/product/product.service';
import { ProductListComponent } from 'app/Dashboard/product-list/product-list.component';
import { FormBuilder } from '@angular/forms';

@Component({
  selector: 'jhi-new-product',
  templateUrl: './new-product.component.html',
  styleUrls: ['./new-product.component.scss'],
})
export class NewProductComponent implements OnInit {
  product: Product;
  modelRef: NgbModalRef | undefined;
  categorieId: number;
  editForm = this.fb.group({
    name: [],
    code: [],
    quantity: [],
    price: [],
    categorieId: [],
  });

  parent: ProductListComponent | undefined;

  constructor(protected venteService: VenteService, private productService: ProductService, private fb: FormBuilder) {
    this.product = {};
    this.categorieId = 0;
  }

  ngOnInit(): void {}
  private createFromForm(): IProduct {
    return {
      ...new Product(),
      name: this.editForm.get(['name'])!.value,
      code: this.editForm.get(['code'])!.value,
      quantity: this.editForm.get(['quantity'])!.value,
      price: this.editForm.get(['price'])!.value,
      categorieId: this.editForm.get(['categorieId'])!.value,
    };
  }
  save(): void {
    this.product = this.createFromForm();
    this.product.categorieId = this.categorieId;
    // eslint-disable-next-line no-console
    console.log('**********************', this.product);
    this.subscribeToSaveResponse(this.productService.create(this.product));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IProduct>>): void {
    result.subscribe(
      productRes => this.onSaveSuccess(productRes.body),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(product: IProduct | null): void {
    if (this.parent && product) this.parent.addProduct(product);
    this.closeModal();
  }

  closeModal(): void {
    if (this.modelRef) this.modelRef.close();
  }

  protected onSaveError(): void {}
}
