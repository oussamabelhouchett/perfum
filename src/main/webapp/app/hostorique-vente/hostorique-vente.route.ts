import { Routes } from '@angular/router';

import { HostoriqueVenteComponent } from './hostorique-vente.component';

export const HOSRORIQUE_VENTE_ROUTE: Routes = [
  {
    path: '',
    component: HostoriqueVenteComponent,
    data: {
      authorities: [],
      pageTitle: 'navs.hostoriquevente',
    },
  },
];
