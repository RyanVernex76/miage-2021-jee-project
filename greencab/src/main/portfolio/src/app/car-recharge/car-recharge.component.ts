import {Component, Input, OnInit} from '@angular/core';
import {Car} from "../../model/Car";
import {GreenCabService} from "../green-cab.service";
import {firstValueFrom, Observable} from "rxjs";
import {addWarning} from "@angular-devkit/build-angular/src/utils/webpack-diagnostics";

@Component({
  selector: 'app-car-recharge',
  templateUrl: './car-recharge.component.html',
  styleUrls: ['./car-recharge.component.scss']
})
export class CarRechargeComponent implements OnInit {

  @Input()
  cars: Array<Car> | undefined;

  constructor() { }

  ngOnInit() {
  }

}
