import {Component, Input, OnInit} from '@angular/core';
import {Car} from "../../model/Car";
import {GreenCabService} from "../green-cab.service";
import {firstValueFrom, Observable} from "rxjs";
import {LoginService} from "../login.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-car-recharge',
  templateUrl: './car-recharge.component.html',
  styleUrls: ['./car-recharge.component.scss']
})
export class CarRechargeComponent implements OnInit {

  @Input()
  cars: Array<Car> | undefined;

  constructor(private router: Router, private greenCabService: GreenCabService, private loginService: LoginService) { }

  ngOnInit() {
  }

  async TakeCar(carId: number) {
    if (this.loginService.currentUser != undefined) {
      await firstValueFrom(this.greenCabService.initRecharge(carId,
        this.loginService.currentUser.id));
      this.reloadComponent();
    }
  }

  reloadComponent() {
    let currentUrl = this.router.url;
    this.router.routeReuseStrategy.shouldReuseRoute = () => false;
    this.router.onSameUrlNavigation = 'reload';
    this.router.navigate([currentUrl]);
  }
}
