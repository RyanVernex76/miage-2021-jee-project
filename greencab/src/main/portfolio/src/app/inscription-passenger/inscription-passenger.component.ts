import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {firstValueFrom, Observable} from "rxjs";
import {LoginService} from "../login.service";
import {Passenger} from "../../model/Passenger";
import {Router} from "@angular/router";

@Component({
  selector: 'app-inscription-passenger',
  templateUrl: './inscription-passenger.component.html',
  styleUrls: ['./inscription-passenger.component.scss']
})
export class InscriptionPassengerComponent implements OnInit {

  public inscriptionForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
    emailAddress: ['', Validators.required],
    phoneNumber: ['', Validators.required],
    password: ['', Validators.required],
    }
  )

  constructor(private router:Router, private fb: FormBuilder, private loginService: LoginService) { }

  ngOnInit(): void {
  }

  async onSubmit(input:any) {
    let pass: Passenger = new Passenger(0, input.firstName, input.lastName, input.emailAddress, input.phoneNumber, input.password);
    let obs: Observable<any> = await this.loginService.createPassenger(pass);
    let resp:Response = await firstValueFrom(obs);

    this.router.navigate(["login"]);
  }
}
