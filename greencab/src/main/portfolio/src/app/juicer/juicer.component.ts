import { Component, OnInit } from '@angular/core';
import {Juicer} from "../../model/Juicer";
import {ActivatedRoute, Router} from "@angular/router";
import {LoginService} from "../login.service";
import {GreenCabService} from "../green-cab.service";
import {firstValueFrom, Observable} from "rxjs";

@Component({
  selector: 'app-juicer',
  templateUrl: './juicer.component.html',
  styleUrls: ['./juicer.component.scss']
})
export class JuicerComponent implements OnInit {

  juicer: Juicer | undefined;

  constructor(private router: Router, private loginService: LoginService, private greenCabService: GreenCabService, private route: ActivatedRoute) { }

  async ngOnInit(){
    this.loginService.checkConnect();
    this.juicer = await this.getJuicer();
  }

  private async getJuicer() {
    let id = this.route.snapshot.paramMap.get('id');
    let obs: Observable<Juicer> = this.loginService.getJuicer(Number(id));
    return await firstValueFrom(obs);
  }

}
