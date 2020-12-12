import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IAchat, Achat } from 'app/shared/model/achat.model';
import { AchatService } from './achat.service';
import { IProduct } from 'app/shared/model/product.model';
import { ProductService } from 'app/entities/product/product.service';

@Component({
  selector: 'jhi-achat-update',
  templateUrl: './achat-update.component.html',
})
export class AchatUpdateComponent implements OnInit {
  isSaving = false;
  products: IProduct[] = [];
  dateachatDp: any;

  editForm = this.fb.group({
    id: [],
    quanttiy: [],
    price: [],
    dateachat: [],
    time: [],
    productId: [],
  });

  constructor(
    protected achatService: AchatService,
    protected productService: ProductService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ achat }) => {
      if (!achat.id) {
        const today = moment().startOf('day');
        achat.time = today;
      }

      this.updateForm(achat);

      this.productService.query().subscribe((res: HttpResponse<IProduct[]>) => (this.products = res.body || []));
    });
  }

  updateForm(achat: IAchat): void {
    this.editForm.patchValue({
      id: achat.id,
      quanttiy: achat.quanttiy,
      price: achat.price,
      dateachat: achat.dateachat,
      time: achat.time ? achat.time.format(DATE_TIME_FORMAT) : null,
      productId: achat.productId,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const achat = this.createFromForm();
    if (achat.id !== undefined) {
      this.subscribeToSaveResponse(this.achatService.update(achat));
    } else {
      this.subscribeToSaveResponse(this.achatService.create(achat));
    }
  }

  private createFromForm(): IAchat {
    return {
      ...new Achat(),
      id: this.editForm.get(['id'])!.value,
      quanttiy: this.editForm.get(['quanttiy'])!.value,
      price: this.editForm.get(['price'])!.value,
      dateachat: this.editForm.get(['dateachat'])!.value,
      time: this.editForm.get(['time'])!.value ? moment(this.editForm.get(['time'])!.value, DATE_TIME_FORMAT) : undefined,
      productId: this.editForm.get(['productId'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAchat>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }

  trackById(index: number, item: IProduct): any {
    return item.id;
  }
}
