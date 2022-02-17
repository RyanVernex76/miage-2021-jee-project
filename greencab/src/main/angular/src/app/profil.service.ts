import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Router } from '@angular/router';
import { faThumbsDown } from '@fortawesome/free-solid-svg-icons';
import { Passenger } from 'src/model/Passenger';
import {Juicer} from "../model/Juicer";

@Injectable({
  providedIn: 'root'
})
export class ProfilService {

  readonly prefix = "http://localhost:8080/";

  constructor(private http: HttpClient, private router: Router) { }

  delete(id:number, userType:string) {
	return this.http.delete(this.prefix + userType + "/" + id)
  }

  changePassword(id: number, newPassword : string, userType :string) {
	const config = { headers: {'Content-Type': 'text/plain'} };
	return this.http.put(this.prefix + userType + "/password/" + id, newPassword, config);
  }
}
