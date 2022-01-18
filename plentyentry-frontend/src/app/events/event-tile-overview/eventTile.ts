export class EventTile {
  private _village: string;
  private _date: string;
  private _imageUrl: string;
  private _description: string;
  private _eventName: string;
  private _colspan: number;
  private _rowspan: number;

  constructor(village: string, date: string, eventName: string, colspan: number = 2, rowspan: number = 5) {
    this._village = village;
    this._date = date;
    this._eventName = eventName;
    this._imageUrl = 'https://material.angular.io/assets/img/examples/shiba2.jpg';
    this._description = 'Test for -> ';
    this._colspan = colspan;
    this._rowspan = rowspan;
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


  get colspan(): number {
    return this._colspan;
  }

  set colspan(value: number) {
    this._colspan = value;
  }

  get rowspan(): number {
    return this._rowspan;
  }

  set rowspan(value: number) {
    this._rowspan = value;
  }
}
