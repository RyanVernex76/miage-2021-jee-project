import {Component, Input, OnInit} from '@angular/core';
import {Car} from "../../model/Car";
import {Recharge} from "../../model/Recharge";

@Component({
  selector: 'app-car-in-charge',
  templateUrl: './car-in-charge.component.html',
  styleUrls: ['./car-in-charge.component.scss']
})
export class CarInChargeComponent implements OnInit {

  @Input()
  recharges: Array<Recharge> | undefined;

  constructor() { }

  ngOnInit(): void {
  }

}
