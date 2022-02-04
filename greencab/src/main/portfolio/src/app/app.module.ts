import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ConnexionFormComponent } from './connexion-form/connexion-form.component';
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import { PassengerComponent } from './passenger/passenger.component';
import { HeaderComponent } from './header/header.component';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';
import { FareTableComponent } from './fare-table/fare-table.component';
import { NewFareComponent } from './new-fare/new-fare.component';

@NgModule({
  declarations: [
    AppComponent,
    ConnexionFormComponent,
    PassengerComponent,
    HeaderComponent,
    FareTableComponent,
    NewFareComponent
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
export class AppModule { }
