import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Passenger } from 'src/model/Passenger';
import {Router} from "@angular/router";

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  readonly prefix = "http://localhost:8080/";

  private _currentPassenger: Passenger | undefined = new Passenger(
    1, "Ryan","Vernex", "118 218", "ryanvernex@yahoo.fr", "password12"
  );

  constructor(private http: HttpClient, private router: Router) { }

  getPassenger(id: number) {
    return this.http.get<Passenger>(this.prefix + "passenger/" + id);
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

  getPassengers(){
    return this.http.get<Passenger[]>(this.prefix + "passenger/all");
  }

  get currentPassenger(): Passenger | undefined {
    return this._currentPassenger;
  }

  set currentPassenger(value: Passenger | undefined) {
    this._currentPassenger = value;
  }

  checkConnect(){
    if(this.currentPassenger == undefined)
      this.router.navigate(['login']);
  }
}
