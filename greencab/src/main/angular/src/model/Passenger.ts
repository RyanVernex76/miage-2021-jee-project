

export class Passenger {
  private _id: number;
  private _firstName: string;
  private _lastName: string;
  private _phone: string;
  private _emailAddress: string;
  private _password: string;

  constructor(id: number, first_name: string, last_name: string, phone: string, email: string, password: string) {
    this._id = id;
    this._firstName = first_name;
    this._lastName = last_name;
    this._phone = phone;
    this._emailAddress = email;
    this._password = password;
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
}
