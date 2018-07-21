import { CommonModule } from '@angular/common';
import { Routes, RouterModule } from '@angular/router';
import { AdminComponent } from '../components/admin-components/admin/admin.component';
import { AddVendorComponent } from '../components/admin-components/add-vendor/add-vendor.component';
import { UpdateProductComponent } from '../components/admin-components/update-product/update-product.component';
import { DeleteComponent } from '../components/admin-components/delete/delete.component';
import { OrderDetailComponent } from '../components/admin-components/order-detail/order-detail.component';
import { AddProductComponent } from '../components/admin-components/add-product/add-product.component';
import { ExportDataBaseComponent } from '../components/admin-components/export-data-base/export-data-base.component';
import { DescriptionComponent } from '../components/description/description.component';
import { LoginComponent } from '../components/user-authentication-component/login/login.component';
import { SignupComponent } from '../components/user-authentication-component/signup/signup.component';
import { ViewAllComponent } from '../components/view-all/view-all.component';
import { HomeComponent } from '../components/home/home.component';
import { NgModule } from '@angular/core';
import { CartComponent } from '../components/cart/cart.component';
import { OrderComponent } from '../components/order/order.component';
import { SearchResultComponentComponent } from '../components/search-result-component/search-result-component.component';
import { UserModuleComponent } from '../components/user-module/user-module.component';
import { ModifyVendorComponent } from '../components/admin-components/modify-vendor/modify-vendor.component';

const appRoutes: Routes = [
  {path:'admin',component:AdminComponent,
      children: [
    { path: 'addUser',component:AddVendorComponent, outlet:'admin'},
    { path: 'modifyProduct',component:UpdateProductComponent, outlet:'admin'},
    { path: 'delete',component:DeleteComponent, outlet:'admin'},
    { path: 'order',component:OrderDetailComponent, outlet:'admin'},
    { path: 'addProduct',component:AddProductComponent, outlet:'admin'},
    { path: 'modifyUser',component:ModifyVendorComponent, outlet:'admin'},
    { path: 'exportdatabase',component:ExportDataBaseComponent, outlet:'admin'}

  ]},
  {path:'description/:id',component:DescriptionComponent},
  {path:'cart',component:CartComponent},
 // {path:'login',component:LoginComponent},
 // {path:'signup',component:SignupComponent},
  {path:'viewAll/:id',component:ViewAllComponent},
  { path: 'order/:id', component: OrderComponent },
  { path: 'search/:category/:name',component:SearchResultComponentComponent},
  { path :'account/user_account',component:UserModuleComponent},
  { path: '**', component: HomeComponent }
 
];

@NgModule({
  
    imports:[RouterModule.forRoot(appRoutes)],
    exports:[RouterModule]
})
export class AppRoutingModule { }


//export const routing = RouterModule.forRoot(appRoutes);