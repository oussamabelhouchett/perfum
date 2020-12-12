import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared/shared.module';
import { VenteComponent } from './vente.component';
import { VenteDetailComponent } from './vente-detail.component';
import { VenteUpdateComponent } from './vente-update.component';
import { VenteDeleteDialogComponent } from './vente-delete-dialog.component';
import { venteRoute } from './vente.route';

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(venteRoute)],
  declarations: [VenteComponent, VenteDetailComponent, VenteUpdateComponent, VenteDeleteDialogComponent],
  entryComponents: [VenteDeleteDialogComponent],
})
export class JhipsterVenteModule {}
