import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import { Passenger } from 'src/model/Passenger';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  readonly prefix = "http://localhost:8080/";


  constructor(private http: HttpClient) { }

  getUser(id: number) {
    return this.http.get<Passenger>(this.prefix + "passenger/" + id);
  }

  getPassengers(){
    return this.http.get<Passenger[]>(this.prefix + "passenger/all");
  }
}
