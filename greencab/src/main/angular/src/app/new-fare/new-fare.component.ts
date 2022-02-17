import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {GreenCabService} from "../green-cab.service";
import {LoginService} from "../login.service";
import {firstValueFrom, Observable} from "rxjs";
import {Fare} from "../../model/Fare";

@Component({
  selector: 'app-new-fare',
  templateUrl: './new-fare.component.html',
  styleUrls: ['./new-fare.component.scss']
})
export class NewFareComponent implements OnInit {

  public response?: Fare;
  public response2?: any;

  public fareForm = this.fb.group({
      location: ['', Validators.required],
    }
  )

  constructor(private fb: FormBuilder, private greenCabService: GreenCabService, private loginService: LoginService) { }

  ngOnInit(): void {
  }

  public async onSubmit(input: any) {
    if (this.loginService.currentUser !== undefined){
      let obs: Observable<Fare> = this.greenCabService.bookFare(this.loginService.currentUser.id, input.location);
      this.response = await firstValueFrom(obs);
	  let obs2: Observable<Object> = this.greenCabService.sendEmail(this.loginService.currentUser.id);
      this.response2 = await firstValueFrom(obs2);
	}
  }

}
