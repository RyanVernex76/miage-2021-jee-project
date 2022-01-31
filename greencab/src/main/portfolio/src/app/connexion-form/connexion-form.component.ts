import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../login.service';
import {Passenger} from "../../model/Passenger";
import {firstValueFrom, Observable} from 'rxjs';
import {addWarning} from "@angular-devkit/build-angular/src/utils/webpack-diagnostics";

@Component({
  selector: 'app-connexion-form',
  templateUrl: './connexion-form.component.html',
  styleUrls: ['./connexion-form.component.scss']
})
export class ConnexionFormComponent implements OnInit {

  passengers: Passenger[] = [];

  public loginForm = this.fb.group({
      login: ['', Validators.required],
      password: ['', Validators.required]
    }
  )

  constructor(private fb: FormBuilder, private loginService: LoginService) { }

  public async onSubmit(input: any) {

  }

  async ngOnInit(): Promise<void> {
    let obs$: Observable<Passenger[]> = this.loginService.getPassengers();
    this.passengers = await firstValueFrom(obs$);

    console.log(this.passengers);
  }

}
