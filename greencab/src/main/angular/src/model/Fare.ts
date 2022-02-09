import {Car} from "./Car";
import {Passenger} from "./Passenger";

export class Fare{
  private _id: number;
  private _car: Car;
  private _passenger: Passenger;
  private _price: number;
  private _date: Date;

  constructor(id: number, car: Car, passenger: Passenger, price: number, date: Date) {
    this._id = id;
    this._car = car;
    this._passenger = passenger;
    this._price = price;
    this._date = date;
  }

  get id(): number {
    return this._id;
  }

  get car(): Car {
    return this._car;
  }

  get passenger(): Passenger {
    return this._passenger;
  }

  get price(): number {
    return this._price;
  }

  get date(): Date {
    return this._date;
  }

}
