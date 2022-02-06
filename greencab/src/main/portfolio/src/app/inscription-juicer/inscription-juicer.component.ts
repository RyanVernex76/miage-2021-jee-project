import { Component, OnInit } from '@angular/core';
import {FormBuilder, Validators} from "@angular/forms";
import {Router} from "@angular/router";
import {LoginService} from "../login.service";
import {Passenger} from "../../model/Passenger";
import {firstValueFrom, Observable} from "rxjs";
import {Juicer} from "../../model/Juicer";
import {BankAccount} from "../../model/BankAccount";

@Component({
  selector: 'app-inscription-juicer',
  templateUrl: './inscription-juicer.component.html',
  styleUrls: ['./inscription-juicer.component.scss']
})
export class InscriptionJuicerComponent implements OnInit {

  public inscriptionForm = this.fb.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      emailAddress: ['', Validators.required],
      phoneNumber: ['', Validators.required],
      password: ['', Validators.required],
      IBAN: ['', Validators.required],
      BIC: ['', Validators.required]
    }
  )

  constructor(private router:Router, private fb: FormBuilder, private loginService: LoginService) { }


  ngOnInit(): void {
  }

  async onSubmit(input: any) {
    let juicer: Juicer = new Juicer(
      0,
      input.firstName,
      input.lastName,
      input.emailAddress,
      input.phoneNumber,
      input.password,
      new BankAccount(input.IBAN, input.BIC));
    let obs: Observable<any> = await this.loginService.createJuicer(juicer);
    let resp: Response = await firstValueFrom(obs);

    this.router.navigate(["login"]);
  }
}
