import { Component, OnInit, OnDestroy } from '@angular/core';
import { CategorieService } from 'app/entities/categorie/categorie.service';

import { Categorie, ICategorie } from 'app/shared/model/categorie.model';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { NewCategorieComponent } from 'app/Dashboard/new-categorie/new-categorie.component';

@Component({
  selector: 'jhi-dashboard',
  templateUrl: './dashboard.component.html',
  styleUrls: ['dashboard.scss'],
})
export class DashboardComponent implements OnInit, OnDestroy {
  categories: Observable<Categorie[]>;

  constructor(private categorieService: CategorieService, protected modalService: NgbModal) {
    this.categories = of([]);
  }

  ngOnInit(): void {
    this.loadAll();
  }

  loadAll(): void {
    /*
    if (this.currentSearch) {
       this.categorieService
         .search({
           query: this.currentSearch,
         })
         .subscribe((res: HttpResponse<ICategorie[]>) => (this.categories = res.body || []));
       return;
     }
 */
    this.categories = this.categorieService.query().pipe(map(categorie => categorie.body || []));
  }

  search(): void {
    // this.currentSearch = query;
    this.loadAll();
  }

  trackId(index: number, item: ICategorie): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  addCategorie(): void {
    this.categories = this.categories.pipe(map(res => res));
  }
  openNewCategorie(): void {
    const modelRef = this.modalService.open(NewCategorieComponent as Component);
    modelRef.componentInstance.modelRef = modelRef;
    modelRef.componentInstance.parent = this;
  }
  /*
  delete(categorie: ICategorie): void {
    const modalRef = this.modalService.open(CategorieDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.categorie = categorie;
  }*/

  ngOnDestroy(): void {}
}
