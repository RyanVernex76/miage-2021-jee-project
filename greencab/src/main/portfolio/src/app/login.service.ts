import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Passenger } from 'src/model/Passenger';
import {Router} from "@angular/router";
import {Juicer} from "../model/Juicer";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  readonly prefix = "http://localhost:8080/";

  private _currentUser: Passenger | Juicer | undefined;
    //new Passenger(
    //1, "Ryan","Vernex", "118 218", "ryanvernex@yahoo.fr", "password12"
  //);

  constructor(private http: HttpClient, private router: Router) { }

  getPassenger(id: number) {
    return this.http.get<Passenger>(this.prefix + "passenger/" + id);
  }

  getJuicer(id: number) {
    return this.http.get<Juicer>(this.prefix + "juicer/" + id);
  }

  createPassenger(pass: Passenger){
    return this.http.post(this.prefix + "passenger/add", {
      "firstName" : pass.firstName,
      "lastName" : pass.lastName,
      "phone" : pass.phone,
      "emailAddress" : pass.emailAddress,
      "password" : pass.password
    });
  }

  createJuicer(juicer: Juicer){
    return this.http.post(this.prefix + "juicer/add", {
      "firstName" : juicer.firstName,
      "lastName" : juicer.lastName,
      "phone" : juicer.phone,
      "emailAddress" : juicer.emailAddress,
      "password" : juicer.password,
      "juicerAccount" : {
          "iban" : juicer.juicer_account.IBAN,
          "bic" : juicer.juicer_account.BIC
      }
    });


  }

  getPassengers(){
    return this.http.get<Passenger[]>(this.prefix + "passenger/all");
  }

  getJuicers(){
    return this.http.get<Juicer[]>(this.prefix + "juicer/all");
  }

  get currentUser(): Passenger | Juicer | undefined {
    return this._currentUser;
  }

  set currentUser(value: Passenger | Juicer | undefined) {
    this._currentUser = value;
  }

  checkConnect(){
    if(this.currentUser == undefined)
      this.router.navigate(['login']);
  }
}
