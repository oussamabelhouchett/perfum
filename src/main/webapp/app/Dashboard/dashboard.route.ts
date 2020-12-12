import { Routes } from '@angular/router';

import { DashboardComponent } from './dashboard.component';
import { ProductListComponent } from 'app/Dashboard/product-list/product-list.component';

export const DASHBOARD_ROUTE: Routes = [
  {
    path: '',
    component: DashboardComponent,

    data: {
      authorities: [],
      pageTitle: 'navs.products',
    },
    children: [
      {
        path: ':id/product',
        component: ProductListComponent,
      },
    ],
  },
];
