

export class Passenger {
  private _id: number;
  private _first_name: string;
  private _last_name: string;
  private _phone: string;
  private _emailAddress: string;
  private _password: string;

  constructor(id: number, first_name: string, last_name: string, phone: string, email: string, password: string) {
    this._id = id;
    this._first_name = first_name;
    this._last_name = last_name;
    this._phone = phone;
    this._emailAddress = email;
    this._password = password;
  }

  get id(): number {
    return this._id;
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

  get emailAddress(): string {
    return this._emailAddress;
  }

  get password(): string {
    return this._password;
  }
}
