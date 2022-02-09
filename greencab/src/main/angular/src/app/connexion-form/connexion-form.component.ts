import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { LoginService } from '../login.service';
import {Passenger} from "../../model/Passenger";
import {firstValueFrom, Observable} from 'rxjs';
import {Router} from "@angular/router";
import {Juicer} from "../../model/Juicer";

@Component({
  selector: 'app-connexion-form',
  templateUrl: './connexion-form.component.html',
  styleUrls: ['./connexion-form.component.scss']
})
export class ConnexionFormComponent implements OnInit {

  passengers: Passenger[] = [];
  juicers: Juicer[] = [];

  public loginForm = this.fb.group({
      login: ['', Validators.required],
      password: ['', Validators.required],
      typeUser: ['', Validators.required],
    }
  )

  constructor(private fb: FormBuilder, private loginService: LoginService, private router: Router) { }

  public async onSubmit(input: any) {
    let tab: Array<Passenger> | Array<Juicer> = [];
    if(input.typeUser == "passenger") {
      tab = this.passengers;
    }
    else if(input.typeUser == "juicer"){
        tab = this.juicers;
    }
    let user:Passenger | Juicer | null = this.checkCredentials(input.login, input.password, tab);
    if(user == null){
      //show error msg
    }
    else{
      this.loginService.currentUser = user;
      if(input.typeUser == "passenger")
        this.router.navigate(["passenger/", user.id]);
      else if(input.typeUser == "juicer"){
        this.router.navigate(["juicer/", user.id]);
      }
    }
  }

  private checkCredentials(login: string, password: string, tab:Array<Passenger> | Array<Juicer>){
    for(let user of tab) {
      if(user.emailAddress == login && user.password == password){
        return user;
      }
    }

    return null;
  }

  async ngOnInit(): Promise<void> {
    if(this.loginService.currentUser !== undefined)
      this.router.navigate(['passenger/', this.loginService.currentUser.id]);

    let obs$: Observable<Passenger[]> = this.loginService.getPassengers();
    this.passengers = await firstValueFrom(obs$);

    let obs2$: Observable<Juicer[]> = this.loginService.getJuicers();
    this.juicers = await firstValueFrom(obs2$);
  }

}
