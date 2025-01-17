import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConnexionFormComponent } from './connexion-form/connexion-form.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { PassengerComponent } from './passenger/passenger.component';
import { HeaderComponent } from './header/header.component';
import {FaIconLibrary, FontAwesomeModule} from '@fortawesome/angular-fontawesome';
import { FareTableComponent } from './fare-table/fare-table.component';
import { NewFareComponent } from './new-fare/new-fare.component';
import { InscriptionPassengerComponent } from './inscription-passenger/inscription-passenger.component';
import { InscriptionJuicerComponent } from './inscription-juicer/inscription-juicer.component';
import { JuicerComponent } from './juicer/juicer.component';
import { CarRechargeComponent } from './car-recharge/car-recharge.component';
import { CarInChargeComponent } from './car-in-charge/car-in-charge.component';
import { UserOptionComponent } from './user-option/user-option.component';
import { HomeComponent } from './home/home.component';
import {fas} from "@fortawesome/free-solid-svg-icons";

@NgModule({
  declarations: [
    AppComponent,
    ConnexionFormComponent,
    PassengerComponent,
    HeaderComponent,
    FareTableComponent,
    NewFareComponent,
    InscriptionPassengerComponent,
    InscriptionJuicerComponent,
    JuicerComponent,
    CarRechargeComponent,
    CarInChargeComponent,
    UserOptionComponent,
    HomeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule,
    FontAwesomeModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
  constructor(lib: FaIconLibrary) {
  lib.addIconPacks(fas);
} }
