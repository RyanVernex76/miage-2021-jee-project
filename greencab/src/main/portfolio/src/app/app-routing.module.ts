import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ConnexionFormComponent} from "./connexion-form/connexion-form.component";
import {PassengerComponent} from "./passenger/passenger.component";
import {NewFareComponent} from "./new-fare/new-fare.component";

const routes: Routes = [
  {path: 'login', component: ConnexionFormComponent},
  {path: 'passenger/:id', component: PassengerComponent},
  {path: 'passenger/:id/bookFare', component: NewFareComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
