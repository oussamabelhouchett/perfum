import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { LoginComponent } from './login.component';
import { LOGIN_ROUTE } from './login.route';
import { JhipsterSharedModule } from 'app/shared/shared.module';

@NgModule({
  imports: [JhipsterSharedModule, RouterModule.forChild([LOGIN_ROUTE])],
  declarations: [LoginComponent],
})
export class LoginModule {}
