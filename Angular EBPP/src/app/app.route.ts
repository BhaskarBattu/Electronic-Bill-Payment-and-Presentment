import { ModuleWithProviders } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { LoginComponent } from './login/login.component';
import { MembersComponent } from './members/members.component';
import { AuthGuard } from './auth.service';
import { SignupComponent } from './signup/signup.component';
import {HomeComponent} from './home/home.component';
import {CustomerComponent} from './customer/customer.component';
import {BillsmanagementComponent} from './billsmanagement/billsmanagement.component';
import {BillsandpaymentmanagementComponent} from './billsandpaymentmanagement/billsandpaymentmanagement.component';


export const router: Routes = [
  { path: '', redirectTo: 'login', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'signup', component: SignupComponent },
  { path: 'members', component: MembersComponent, canActivate: [AuthGuard] },
  { path: 'home', component: HomeComponent, canActivate: [AuthGuard] },
  { path: 'home/customermanagement', component: CustomerComponent, canActivate: [AuthGuard] },
  { path: 'home/billsmanagement', component: BillsmanagementComponent, canActivate: [AuthGuard] },
  { path: 'home/billsandpaymentmanagement', component: BillsandpaymentmanagementComponent, canActivate: [AuthGuard] }

]

export const routes: ModuleWithProviders = RouterModule.forRoot(router);
