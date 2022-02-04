import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../login.service';
import {Passenger} from "../../model/Passenger";
import {firstValueFrom, Observable} from 'rxjs';
import {addWarning} from "@angular-devkit/build-angular/src/utils/webpack-diagnostics";
import {Router} from "@angular/router";

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

  constructor(private fb: FormBuilder, private loginService: LoginService, private router: Router) { }

  public async onSubmit(input: any) {
    let pass:Passenger | null = this.checkCredentials(input.login, input.password);

    if(pass == null){
      //show error msg
    }
    else{
      this.loginService.currentPassenger = pass;
      this.router.navigate(['passenger/', pass.id]);
    }
  }

  private checkCredentials(login: string, password: string){
    for(let passenger of this.passengers) {
      if(passenger.emailAddress == login && passenger.password == password){
        return passenger;
      }
    }

    return null;
  }

  async ngOnInit(): Promise<void> {
    if(this.loginService.currentPassenger !== undefined)
      this.router.navigate(['passenger/', this.loginService.currentPassenger.id]);

    let obs$: Observable<Passenger[]> = this.loginService.getPassengers();
    this.passengers = await firstValueFrom(obs$);
  }

}
