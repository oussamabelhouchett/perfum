import { Routes } from '@angular/router';

import { HostoriqueAchatComponent } from './hostorique-achat.component';

export const HOSRORIQUE_ACHAT_ROUTE: Routes = [
  {
    path: '',
    component: HostoriqueAchatComponent,
    data: {
      authorities: [],
      pageTitle: 'navs.hostoriqueachat',
    },
  },
];
