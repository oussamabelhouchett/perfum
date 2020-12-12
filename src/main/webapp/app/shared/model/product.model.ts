import { IVente } from 'app/shared/model/vente.model';
import { IAchat } from 'app/shared/model/achat.model';

export interface IProduct {
  id?: number;
  name?: string;
  code?: string;
  quantity?: number;
  price?: number;
  ventes?: IVente[];
  achats?: IAchat[];
  categorieId?: number;
}

export class Product implements IProduct {
  constructor(
    public id?: number,
    public name?: string,
    public code?: string,
    public quantity?: number,
    public price?: number,
    public ventes?: IVente[],
    public achats?: IAchat[],
    public categorieId?: number
  ) {}
}
