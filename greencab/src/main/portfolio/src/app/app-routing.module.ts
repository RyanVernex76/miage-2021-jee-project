import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import {ConnexionFormComponent} from "./connexion-form/connexion-form.component";
import {PassengerComponent} from "./passenger/passenger.component";
import {NewFareComponent} from "./new-fare/new-fare.component";
import {InscriptionPassengerComponent} from "./inscription-passenger/inscription-passenger.component";
import {InscriptionJuicerComponent} from "./inscription-juicer/inscription-juicer.component";

const routes: Routes = [
  {path: 'login', component: ConnexionFormComponent},
  {path: 'inscription', component: InscriptionPassengerComponent},
  {path: 'inscription/juicer', component: InscriptionJuicerComponent},
  {path: 'passenger/:id', component: PassengerComponent},
  {path: 'passenger/:id/bookFare', component: NewFareComponent},
  {path: '**', redirectTo: 'login'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
