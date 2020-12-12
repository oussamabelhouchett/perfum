import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { JhipsterSharedModule } from 'app/shared/shared.module';
import { HOSRORIQUE_ACHAT_ROUTE } from './hostorique-achat.route';
import { HostoriqueAchatComponent } from './hostorique-achat.component';

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(HOSRORIQUE_ACHAT_ROUTE)],
  declarations: [HostoriqueAchatComponent],
})
export class HostoriqueAchatModule {}
