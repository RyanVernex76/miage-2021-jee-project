
export class Car{
  private _id: number;
  private _year: string;
  private _brand: string;
  private _licensePlate: string;

  constructor(id: number, year: string, brand: string, licensePlate: string) {
    this._id = id;
    this._year = year;
    this._brand = brand;
    this._licensePlate = licensePlate;
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
