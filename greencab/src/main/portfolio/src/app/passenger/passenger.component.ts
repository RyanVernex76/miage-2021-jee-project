import { Component, OnInit } from '@angular/core';
import {LoginService} from "../login.service";
import {Passenger} from "../../model/Passenger";
import {ActivatedRoute} from "@angular/router";
import {firstValueFrom, Observable} from "rxjs";
import {Fare} from "../../model/Fare";
import {GreenCabService} from "../green-cab.service";

@Component({
  selector: 'app-passenger',
  templateUrl: './passenger.component.html',
  styleUrls: ['./passenger.component.scss']
})
export class PassengerComponent implements OnInit {

  passenger: Passenger | undefined;
  passengerFares: Fare[] = [];

  constructor(private loginService: LoginService, private greenCabService: GreenCabService, private route: ActivatedRoute) { }

  async ngOnInit(){
    this.loginService.checkConnect();
    this.passenger = await this.getPassenger();
    console.log(this.passenger);
    this.getFares(this.passenger.id);
  }

  private async getPassenger() {
    let id = this.route.snapshot.paramMap.get('id');
    let obs: Observable<Passenger> = this.loginService.getPassenger(Number(id));
    return await firstValueFrom(obs);
  }

  public async getFares(id: number){
    let obs: Observable<Fare[]> = this.greenCabService.getFaresOfPassenger(id);
    this.passengerFares = await firstValueFrom(obs);
    console.log(this.passengerFares);
  }

}
