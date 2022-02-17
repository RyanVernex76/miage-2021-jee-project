import { INFERRED_TYPE } from '@angular/compiler/src/output/output_ast';
import {Component, OnInit, Input, ViewChild, ElementRef} from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { firstValueFrom, Observable } from 'rxjs';
import { Juicer } from 'src/model/Juicer';
import { Passenger } from 'src/model/Passenger';
import { LoginService } from '../login.service';
import { ProfilService } from '../profil.service';

@Component({
  selector: 'app-user-option',
  templateUrl: './user-option.component.html',
  styleUrls: ['./user-option.component.scss']
})
export class UserOptionComponent implements OnInit {

	@Input () user !: Passenger | Juicer | undefined;
	@Input () userType !: string;
	public passwordConfInputUnfocus : boolean = false;
	public displayDeleteAccountConfirmation : boolean = false;
	public oldPasswordOk : boolean = true;

  constructor(private fb: FormBuilder, private profilService:ProfilService, private router: Router, private loginService:LoginService) { }

  public passwordForm !: FormGroup;
  public oldPassword !: string;
  public newPassword !: string;
  public newPasswordConfirmation : string = '';

  @ViewChild('toggleBtn') toggleBtn!: ElementRef;

  public hide: boolean = true;

  ngOnInit(): void {
	  this.init_form();
  }

  public init_form(){
	  this.passwordForm = this.fb.group({
		oldPassword: ['', Validators.required],
		newPassword: ['', Validators.required],
		newPasswordConfirmation: ['', [Validators.required]]
	  })
  }

  async onSubmit(input : any){
	if (this.passwordForm.controls['oldPassword'].value != this.user?.password){
		this.oldPasswordOk = false;
		return;
	}
	this.oldPasswordOk = true;
	if (this.user != undefined){
	this.profilService.changePassword(
			this.user.id,
			input.newPassword,
			this.userType
		).subscribe();
	this.passwordConfInputUnfocus = false;
	this.passwordForm.reset();
	}
  }

   deleteAccount(){
	  if (this.user != undefined) {
	  this.profilService.delete(this.user.id, this.userType).subscribe();
		this.loginService.currentUser = undefined;
		this.router.navigate(['login']);
	  }
  }

  public checkPasswordConfirmation() : boolean {
		let newPassword = this.passwordForm.controls['newPassword'].value;
	  let newPasswordConfirmation = this.passwordForm.controls['newPasswordConfirmation'].value;
	  if (newPassword && newPasswordConfirmation){
		return newPassword === newPasswordConfirmation;
	  }
	  return false;
  }

  public toggle(){
    this.hide = !this.hide;
  }

  public logOut(){
    this.loginService.currentUser = undefined;
  }

}

