import { Component, OnInit } from '@angular/core';
import {LoginService} from "../login.service";

@Component({
  selector: 'app-passenger',
  templateUrl: './passenger.component.html',
  styleUrls: ['./passenger.component.scss']
})
export class PassengerComponent implements OnInit {

  constructor( private loginService: LoginService) { }

  ngOnInit(): void {
    this.loginService.checkConnect();
  }

}
