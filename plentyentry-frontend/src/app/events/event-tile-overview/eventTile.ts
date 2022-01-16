export class EventTile {
  private _village: string;
  private _date: string;
  private _imageUrl: string;
  private _description: string;
  private _eventName: string;

  constructor(village: string, date: string, eventName: string) {
    this._village = village;
    this._date = date;
    this._eventName = eventName;
    this._imageUrl = 'https://material.angular.io/assets/img/examples/shiba2.jpg';
    this._description = 'Test for -> ';
  }

  public get village(): string {
    return this._village;
  }

  set village(value: string) {
    this._village = value;
  }

  public get date(): string {
    return this._date;
  }

  set date(value: string) {
    this._date = value;
  }

  public get imageUrl(): string {
    return this._imageUrl;
  }

  set imageUrl(value: string) {
    this._imageUrl = value;
  }

  public get description(): string {
    return this._description;
  }

  set description(value: string) {
    this._description = value;
  }

  get eventName(): string {
    return this._eventName;
  }

  set eventName(value: string) {
    this._eventName = value;
  }
}
