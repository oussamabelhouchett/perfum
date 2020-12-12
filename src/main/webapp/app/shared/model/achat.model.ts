import { Moment } from 'moment';

export interface IAchat {
  id?: number;
  quanttiy?: string;
  price?: string;
  dateachat?: Moment;
  time?: Moment;
  productId?: number;
  productName?: string;
  productPrix?: number;
}

export class Achat implements IAchat {
  constructor(
    public id?: number,
    public quanttiy?: string,
    public price?: string,
    public dateachat?: Moment,
    public time?: Moment,
    public productId?: number
  ) {}
}
