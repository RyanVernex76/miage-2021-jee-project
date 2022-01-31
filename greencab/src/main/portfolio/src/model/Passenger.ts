

export class Passenger {
  private _first_name: string;
  private _last_name: string;
  private _phone: string;
  private _email: string;
  private _password: string;

  constructor(first_name: string, last_name: string, phone: string, email: string, password: string) {
    this._first_name = first_name;
    this._last_name = last_name;
    this._phone = phone;
    this._email = email;
    this._password = password;
  }

  get first_name(): string {
    return this._first_name;
  }

  get last_name(): string {
    return this.last_name;
  }

  get phone(): string {
    return this._phone;
  }

  get email(): string {
    return this._phone;
  }

  get password(): string {
    return this._phone;
  }
}
