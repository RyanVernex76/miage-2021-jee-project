import {BankAccount} from "./BankAccount";


export class Juicer {
  private _id: number;
  private _firstName: string;
  private _lastName: string;
  private _phone: string;
  private _emailAddress: string;
  private _password: string;
  private _juicer_account: BankAccount;

  constructor(id: number, firstName: string, lastName: string, phone: string, emailAddress: string, password: string, juicer_account: BankAccount) {
    this._id = id;
    this._firstName = firstName;
    this._lastName = lastName;
    this._phone = phone;
    this._emailAddress = emailAddress;
    this._password = password;
    this._juicer_account = juicer_account;
  }

  get id(): number {
    return this._id;
  }

  get firstName(): string {
    return this._firstName;
  }

  get lastName(): string {
    return this._lastName;
  }

  get phone(): string {
    return this._phone;
  }

  get emailAddress(): string {
    return this._emailAddress;
  }

  get password(): string {
    return this._password;
  }

  get juicer_account(): BankAccount {
    return this._juicer_account;
  }
}
