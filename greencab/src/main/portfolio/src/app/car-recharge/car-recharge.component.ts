import {Component, Input, OnInit} from '@angular/core';
import {Car} from "../../model/Car";
import {GreenCabService} from "../green-cab.service";
import {firstValueFrom, Observable} from "rxjs";
import {addWarning} from "@angular-devkit/build-angular/src/utils/webpack-diagnostics";
import {Recharge} from "../../model/Recharge";
import {LoginService} from "../login.service";
import {Juicer} from "../../model/Juicer";

@Component({
  selector: 'app-car-recharge',
  templateUrl: './car-recharge.component.html',
  styleUrls: ['./car-recharge.component.scss']
})
export class CarRechargeComponent implements OnInit {

  @Input()
  cars: Array<Car> | undefined;

  constructor(private greenCabService: GreenCabService, private loginService: LoginService) { }

  ngOnInit() {
  }

  async TakeCar(carId: number) {
    if (this.loginService.currentUser != undefined) {
      await firstValueFrom(this.greenCabService.initRecharge(carId,
        this.loginService.currentUser.id));
    }

  }
}
