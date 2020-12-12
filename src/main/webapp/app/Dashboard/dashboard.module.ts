import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { JhipsterSharedModule } from 'app/shared/shared.module';
import { DashboardComponent } from 'app/Dashboard/dashboard.component';
import { DASHBOARD_ROUTE } from 'app/Dashboard/dashboard.route';
import { ProductListComponent } from './product-list/product-list.component';
import { NewVenteComponent } from './product-list/new-vente/new-vente.component';
import { NewProductComponent } from 'app/Dashboard/product-list/new-product/new-product.component';
import { NewCategorieComponent } from 'app/Dashboard/new-categorie/new-categorie.component';

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild(DASHBOARD_ROUTE)],
  declarations: [DashboardComponent, ProductListComponent, NewVenteComponent, NewProductComponent, NewCategorieComponent],
})
export class DashboardModule {}
