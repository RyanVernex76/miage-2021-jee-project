import { Component, OnInit } from '@angular/core';
import {Juicer} from "../../model/Juicer";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../login.service";
import {GreenCabService} from "../green-cab.service";
import {firstValueFrom, Observable} from "rxjs";
import {Car} from "../../model/Car";
import {Recharge} from "../../model/Recharge";

@Component({
  selector: 'app-juicer',
  templateUrl: './juicer.component.html',
  styleUrls: ['./juicer.component.scss']
})
export class JuicerComponent implements OnInit {

  juicer: Juicer | undefined;
  carsRecharge: Array<Car> | undefined;
  recharges: Array<Recharge> | undefined;

  constructor(private router: Router, private loginService: LoginService, private greenCabService: GreenCabService, private route: ActivatedRoute) { }

  async ngOnInit(){
    this.loginService.checkConnect();
    this.juicer = await this.getJuicer();
    await this.getCarsRecharge();
    await this.getRecharges();
  }

  private async getJuicer() {
    let id = this.route.snapshot.paramMap.get('id');
    let obs: Observable<Juicer> = this.loginService.getJuicer(Number(id));
    return await firstValueFrom(obs);
  }

  public async getCarsRecharge(){
    let obs: Observable<Car[]> = this.greenCabService.getCarsNeedRecharge();
    this.carsRecharge = await firstValueFrom(obs);

    for (let car of this.carsRecharge) {
      car.position = await this.getCarPosition(car.id);
    }
  }

  public async getRecharges(){
    if(this.juicer !== undefined){
      let obs: Observable<Recharge[]> = this.greenCabService.getRechargeJuicer(this.juicer.id);
      this.recharges = await firstValueFrom(obs);
    }
  }

  async getCarPosition(id: number) {
  let obs: Promise<Observable<string>> = this.greenCabService.getElementPosition(id);
  let result: string = await firstValueFrom(await obs);
  return result;
  }



}
