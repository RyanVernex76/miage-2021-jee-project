import { Injectable } from '@angular/core';
import {Passenger} from "../model/Passenger";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Fare} from "../model/Fare";
import {firstValueFrom} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class GreenCabService {

  readonly prefix = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  public getFaresOfPassenger(passengerId: number){
    return this.http.get<Fare[]>(this.prefix + "fare/passenger/" + passengerId);
  }

  public async bookFare(passengerId: number, location: string) {
    let params = new HttpParams();
    params = params.append('location', location);
    return this.http.post<Response>(this.prefix + "fare/passenger/" + passengerId +
      "/newFare?location="+ location,
     []);
  }
}
