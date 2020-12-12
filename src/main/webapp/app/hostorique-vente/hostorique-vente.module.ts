import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared/shared.module';
import { HOSRORIQUE_VENTE_ROUTE } from './hostorique-vente.route';
import { HostoriqueVenteComponent } from './hostorique-vente.component';

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(HOSRORIQUE_VENTE_ROUTE)],
  declarations: [HostoriqueVenteComponent],
})
export class HostoriqueVenteModule {}
