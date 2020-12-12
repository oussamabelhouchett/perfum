import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'product',
        loadChildren: () => import('./product/product.module').then(m => m.JhipsterProductModule),
      },
      {
        path: 'categorie',
        loadChildren: () => import('./categorie/categorie.module').then(m => m.JhipsterCategorieModule),
      },
      {
        path: 'achat',
        loadChildren: () => import('./achat/achat.module').then(m => m.JhipsterAchatModule),
      },
      {
        path: 'vente',
        loadChildren: () => import('./vente/vente.module').then(m => m.JhipsterVenteModule),
      },

      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class JhipsterEntityModule {}
