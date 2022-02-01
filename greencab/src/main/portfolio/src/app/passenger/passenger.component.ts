import { Component, OnInit } from '@angular/core';
import {LoginService} from "../login.service";
import {Passenger} from "../../model/Passenger";
import {ActivatedRoute} from "@angular/router";
import {firstValueFrom, Observable} from "rxjs";

@Component({
  selector: 'app-passenger',
  templateUrl: './passenger.component.html',
  styleUrls: ['./passenger.component.scss']
})
export class PassengerComponent implements OnInit {

  passenger: Passenger | undefined;

  constructor(private loginService: LoginService, private route: ActivatedRoute) { }

  async ngOnInit(){
    this.loginService.checkConnect();
    this.passenger = await this.getPassenger();
    console.log(this.passenger)
  }

  private async getPassenger() {
    let id = this.route.snapshot.paramMap.get('id');
    let obs: Observable<Passenger> = this.loginService.getPassenger(Number(id));
    return await firstValueFrom(obs);
  }

}
