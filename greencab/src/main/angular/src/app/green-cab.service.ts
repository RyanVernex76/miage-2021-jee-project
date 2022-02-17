import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Fare} from "../model/Fare";
import {Car} from "../model/Car";
import {Recharge} from "../model/Recharge";
import {Observable} from "rxjs";

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

  public getRechargeJuicer(juicerId: number){
    return this.http.get<Recharge[]>(this.prefix + "juicer/" + juicerId + "/recharges");
  }

  public bookFare(passengerId: number, location: string): Observable<Fare> {
    return this.http.post<Fare>(this.prefix + "fare/passenger/" + passengerId +
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
      },
      "state": "waiting"
    });
  }

  public sendEmail(id: number){
	  return this.http.get(this.prefix + "mail/passenger/" + id);
  }

}
