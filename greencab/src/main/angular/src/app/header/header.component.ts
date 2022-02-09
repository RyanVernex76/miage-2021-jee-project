import { Component, OnInit } from '@angular/core';
import {LoginService} from "../login.service";

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {

  constructor(private login:LoginService) { }

  ngOnInit(): void {
  }

  isLogged() {
    return this.login.currentUser !== undefined;
  }
}
