import {Car} from "./Car";
import {Juicer} from "./Juicer";
import {ChargingPoint} from "./ChargingPoint";

export class Recharge{
  private _id: number;
  private _car: Car;
  private _juicer: Juicer;
  private _chargingPoint: ChargingPoint | undefined;
  private _cost: number;

  constructor(id: number, car: Car, juicer: Juicer, cost: number) {
    this._id = id;
    this._car = car;
    this._juicer = juicer;
    this._cost = cost;
  }



  get id(): number {
    return this._id;
  }

  set id(value: number) {
    this._id = value;
  }

  get car(): Car {
    return this._car;
  }

  set car(value: Car) {
    this._car = value;
  }

  get juicer(): Juicer {
    return this._juicer;
  }

  set juicer(value: Juicer) {
    this._juicer = value;
  }

  get cost(): number {
    return this._cost;
  }

  set cost(value: number) {
    this._cost = value;
  }

  get chargingPoint(): ChargingPoint | undefined{
    return this._chargingPoint;
  }

  set chargingPoint(value: ChargingPoint | undefined) {
    this._chargingPoint = value;
  }
}
