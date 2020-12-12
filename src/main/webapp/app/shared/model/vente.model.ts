import { Moment } from 'moment';

export interface IVente {
  id?: number;
  quantite?: number;
  price?: number;
  datevente?: Moment;
  time?: Moment;
  productId?: number;
  productName?: string;
  productPrix?: number;
}

export class Vente implements IVente {
  constructor(
    public id?: number,
    public quantite?: number,
    public price?: number,
    public datevente?: Moment,
    public time?: Moment,
    public productId?: number
  ) {}
}
