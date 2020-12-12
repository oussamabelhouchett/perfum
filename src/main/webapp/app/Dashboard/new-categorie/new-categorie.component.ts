import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpResponse } from '@angular/common/http';
import { NgbModalRef } from '@ng-bootstrap/ng-bootstrap';

import { Categorie, ICategorie } from 'app/shared/model/categorie.model';
import { CategorieService } from 'app/entities/categorie/categorie.service';
import { DashboardComponent } from 'app/Dashboard/dashboard.component';

@Component({
  selector: 'jhi-new-categorie',
  templateUrl: './new-categorie.component.html',
  styleUrls: ['./new-categorie.component.scss'],
})
export class NewCategorieComponent implements OnInit {
  categorie: Categorie;
  modelRef: NgbModalRef | undefined;
  isSaving: boolean;

  parent: DashboardComponent | undefined;

  constructor(private categorieService: CategorieService) {
    this.categorie = {};
    this.isSaving = false;
  }

  ngOnInit(): void {}

  save(): void {
    this.isSaving = true;
    this.subscribeToSaveResponse(this.categorieService.create(this.categorie));
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICategorie>>): void {
    result.subscribe(
      categorieRes => this.onSaveSuccess(categorieRes.body),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(categorie: ICategorie | null): void {
    if (this.parent && categorie) this.parent.addCategorie();
    this.closeModal();
  }

  closeModal(): void {
    if (this.modelRef) this.modelRef.close();
  }

  protected onSaveError(): void {}
}
