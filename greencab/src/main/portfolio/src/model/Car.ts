
export class Car{
  get needRecharge(): boolean {
    return this._needRecharge;
  }

  set needRecharge(value: boolean) {
    this._needRecharge = value;
  }

  get position(): string {
    return this._position;
  }

  set position(value: string) {
    this._position = value;
  }
  private _id: number;
  private _year: string;
  private _brand: string;
  private _licensePlate: string;
  private _needRecharge: boolean;
  private _position: string;

  constructor(id: number, year: string, brand: string, licensePlate: string, recharge: boolean) {
    this._id = id;
    this._year = year;
    this._brand = brand;
    this._licensePlate = licensePlate;
    this._needRecharge = recharge;
    this._position = "";
  }

  get id(): number {
    return this._id;
  }

  get year(): string {
    return this._year;
  }

  get brand(): string {
    return this._brand;
  }

  get licensePlate(): string {
    return this._licensePlate;
  }
}
