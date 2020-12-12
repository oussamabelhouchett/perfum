import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import * as moment from 'moment';

import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption, SearchWithPagination } from 'app/shared/util/request-util';
import { IVente } from 'app/shared/model/vente.model';

type EntityResponseType = HttpResponse<IVente>;
type EntityArrayResponseType = HttpResponse<IVente[]>;

@Injectable({ providedIn: 'root' })
export class VenteService {
  public resourceUrl = SERVER_API_URL + 'api/ventes';
  public resourceSearchUrl = SERVER_API_URL + 'api/_search/ventes';

  constructor(protected http: HttpClient) {}

  create(vente: IVente): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vente);
    return this.http.post<IVente>(this.resourceUrl, copy, { observe: 'response' });
  }

  update(vente: IVente): Observable<EntityResponseType> {
    const copy = this.convertDateFromClient(vente);
    return this.http
      .put<IVente>(this.resourceUrl, copy, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http
      .get<IVente>(`${this.resourceUrl}/${id}`, { observe: 'response' })
      .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVente[]>(this.resourceUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  search(req: SearchWithPagination): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http
      .get<IVente[]>(this.resourceSearchUrl, { params: options, observe: 'response' })
      .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
  }

  protected convertDateFromClient(vente: IVente): IVente {
    const copy: IVente = Object.assign({}, vente, {
      datevente: vente.datevente && vente.datevente.isValid() ? vente.datevente.format(DATE_FORMAT) : undefined,
      time: vente.time && vente.time.isValid() ? vente.time.toJSON() : undefined,
    });
    return copy;
  }

  protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
    if (res.body) {
      res.body.datevente = res.body.datevente ? moment(res.body.datevente) : undefined;
      // eslint-disable-next-line no-console
      console.log('**********************', res.body.datevente);
      res.body.time = res.body.time ? moment(res.body.time) : undefined;
    }
    return res;
  }

  protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
    if (res.body) {
      res.body.forEach((vente: IVente) => {
        vente.datevente = vente.datevente ? moment(vente.datevente) : undefined;
        vente.time = vente.time ? moment(vente.time) : undefined;
      });
    }
    return res;
  }
}
