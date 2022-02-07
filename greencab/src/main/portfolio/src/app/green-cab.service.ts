import { Injectable } from '@angular/core';
import {Passenger} from "../model/Passenger";
import {HttpClient, HttpParams} from "@angular/common/http";
import {Fare} from "../model/Fare";
import {firstValueFrom, Observable} from "rxjs";
import {Car} from "../model/Car";
import {Recharge} from "../model/Recharge";

@Injectable({
  providedIn: 'root'
})
export class GreenCabService {

  readonly prefix = "http://localhost:8080/";

  constructor(private http: HttpClient) { }

  public getFaresOfPassenger(passengerId: number){
    return this.http.get<Fare[]>(this.prefix + "fare/passenger/" + passengerId);
  }

  public getCarsNeedRecharge(){
    return this.http.get<Car[]>(this.prefix + "car/recharge");
  }

  public async bookFare(passengerId: number, location: string) {
    let params = new HttpParams();
    params = params.append('location', location);
    return this.http.post<Response>(this.prefix + "fare/passenger/" + passengerId +
      "/newFare?location="+ location,
     []);
  }

  public async getElementPosition(id: number){
    let opt = { responseType: 'text' as 'text' };
      return this.http.get(this.prefix + "position/" + id, opt);
  }

  public initRecharge(carId: number, juicerId: number){
    return this.http.post(this.prefix + "juicer/recharge/add", {
      "car": {
        "id" : carId
      },
      "juicer": {
        "id" : juicerId
      }
    });
  }

}
