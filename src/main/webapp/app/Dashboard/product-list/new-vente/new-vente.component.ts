import { Component, OnInit } from '@angular/core';
import { IProduct, Product } from 'app/shared/model/product.model';
import { VenteService } from 'app/entities/vente/vente.service';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { IVente, Vente } from 'app/shared/model/vente.model';
import * as moment from 'moment';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { ProductService } from 'app/entities/product/product.service';
import { ProductListComponent } from 'app/Dashboard/product-list/product-list.component';

@Component({
  selector: 'jhi-new-vente',
  templateUrl: './new-vente.component.html',
  styleUrls: ['./new-vente.component.scss'],
})
export class NewVenteComponent implements OnInit {
  product: Product;
  modelRef: NgbModalRef | undefined;
  vente: Vente;
  parent: ProductListComponent | undefined;
  message = '';
  isok: boolean;

  constructor(protected venteService: VenteService, private productService: ProductService) {
    this.product = {};
    this.vente = {};
    this.isok = false;
  }

  ngOnInit(): void {
    this.vente.productId = this.product.id;
    const start = new Date();
    start.setHours(20, 0, 0, 0);
    this.vente.datevente = moment().startOf('day');
    // eslint-disable-next-line no-console
    console.log('**********************', this.vente.datevente);
    this.vente.time = moment.utc(new Date());
  }
  save(): void {
    this.subscribeToSaveResponse(this.venteService.create(this.vente));
  }
  cheakValiditeOfQuantity(): void {
    if (this.vente.quantite) {
      if (this.vente.quantite > this.product.quantity!) {
        this.message = "cette quantite n' est pas disponible pour ce moment";
        this.isok = false;
      } else if (this.vente.quantite <= 0) {
        this.message = ' quantite doit etre > 0';
        this.isok = false;
      } else {
        this.isok = true;
        this.message = '';
      }
    }
  }
  protected subscribeToSaveResponse(result: Observable<HttpResponse<IVente>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.product.quantity = (this.product.quantity || 0) - (this.vente.quantite || 0);
    this.productService.update(this.product).subscribe(res => this.onProductSaveSuccess(res.body));
    this.closeModal();
  }
  protected onProductSaveSuccess(product: IProduct | null): void {
    if (this.parent && product) {
      this.parent.changeProductQuantity(product);
    }
  }

  closeModal(): void {
    if (this.modelRef) this.modelRef.close();
  }

  protected onSaveError(): void {}
}
