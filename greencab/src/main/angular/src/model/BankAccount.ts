
export class BankAccount{
  private _IBAN: string;
  private _BIC: string;

  constructor(IBAN: string, BIC: string) {
    this._IBAN = IBAN;
    this._BIC = BIC;
  }

  get IBAN(): string {
    return this._IBAN;
  }

  set IBAN(value: string) {
    this._IBAN = value;
  }

  get BIC(): string {
    return this._BIC;
  }

  set BIC(value: string) {
    this._BIC = value;
  }
}
