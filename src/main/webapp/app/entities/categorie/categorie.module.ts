import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared/shared.module';
import { CategorieComponent } from './categorie.component';
import { CategorieDetailComponent } from './categorie-detail.component';
import { CategorieUpdateComponent } from './categorie-update.component';
import { CategorieDeleteDialogComponent } from './categorie-delete-dialog.component';
import { categorieRoute } from './categorie.route';

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(categorieRoute)],
  declarations: [CategorieComponent, CategorieDetailComponent, CategorieUpdateComponent, CategorieDeleteDialogComponent],
  entryComponents: [CategorieDeleteDialogComponent],
})
export class JhipsterCategorieModule {}
