import { Component, OnInit, OnDestroy } from '@angular/core';
import { DatePipe } from '@angular/common';
import { IAchat } from 'app/shared/model/achat.model';
import { Subscription } from 'rxjs';
import { AchatService } from 'app/entities/achat/achat.service';
import { JhiEventManager, JhiParseLinks } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';
import { ActivatedRoute } from '@angular/router';
import { ITEMS_PER_PAGE } from 'app/shared/constants/pagination.constants';
import { HttpHeaders, HttpResponse } from '@angular/common/http';
import { AchatDeleteDialogComponent } from 'app/entities/achat/achat-delete-dialog.component';

@Component({
  selector: 'jhi-hostorique-achat',
  templateUrl: './hostorique-achat.component.html',
  styleUrls: ['hostorique-achat.scss'],
})
export class HostoriqueAchatComponent implements OnInit, OnDestroy {
  private dateFormat = 'yyyy-MM-dd';
  fromDate = '';
  toDate = '';
  achats: IAchat[];
  eventSubscriber?: Subscription;
  itemsPerPage: number;
  links: any;
  page: number;
  predicate: string;
  ascending: boolean;
  currentSearch: string;
  constructor(
    private datePipe: DatePipe,
    protected achatService: AchatService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal,
    protected parseLinks: JhiParseLinks,
    protected activatedRoute: ActivatedRoute
  ) {
    this.achats = [];
    this.itemsPerPage = ITEMS_PER_PAGE;
    this.page = 0;
    this.links = {
      last: 0,
    };
    this.predicate = 'id';
    this.ascending = true;
    this.currentSearch =
      this.activatedRoute.snapshot && this.activatedRoute.snapshot.queryParams['search']
        ? this.activatedRoute.snapshot.queryParams['search']
        : '';
  }

  ngOnInit(): void {
    this.toDate = this.today();
    this.fromDate = this.previousMonth();
    this.loadAll();
  }

  loadAll(): void {
    if (this.currentSearch) {
      this.achatService
        .search({
          query: this.currentSearch,
          page: this.page,
          size: this.itemsPerPage,
          sort: this.sort(),
        })
        .subscribe((res: HttpResponse<IAchat[]>) => this.paginateAchats(res.body, res.headers));
      return;
    }
    if (this.fromDate && this.toDate) {
      this.achatService
        .query({
          'dateachat.greaterThanOrEqual': this.fromDate,
          'dateachat.lessThanOrEqual': this.toDate,
          page: this.page,
          size: this.itemsPerPage,
          sort: this.sort(),
        })
        .subscribe((res: HttpResponse<IAchat[]>) => this.paginateAchats(res.body, res.headers));
    }
  }

  reset(): void {
    this.page = 0;
    this.achats = [];
    this.loadAll();
  }

  loadPage(page: number): void {
    this.page = page;
    this.loadAll();
  }

  search(query: string): void {
    this.achats = [];
    this.links = {
      last: 0,
    };
    this.page = 0;
    if (query) {
      this.predicate = '_score';
      this.ascending = false;
    } else {
      this.predicate = 'id';
      this.ascending = true;
    }
    this.currentSearch = query;
    this.loadAll();
  }

  trackId(index: number, item: IAchat): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInAchats(): void {
    this.eventSubscriber = this.eventManager.subscribe('achatListModification', () => this.reset());
  }

  delete(achat: IAchat): void {
    const modalRef = this.modalService.open(AchatDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.achat = achat;
  }

  sort(): string[] {
    const result = [this.predicate + ',' + (this.ascending ? 'asc' : 'desc')];
    if (this.predicate !== 'id') {
      result.push('id');
    }
    return result;
  }

  protected paginateAchats(data: IAchat[] | null, headers: HttpHeaders): void {
    const headersLink = headers.get('link');
    this.links = this.parseLinks.parse(headersLink ? headersLink : '');
    if (data) {
      for (let i = 0; i < data.length; i++) {
        this.achats.push(data[i]);
      }
    }
  }
  private previousMonth(): string {
    let date = new Date();
    if (date.getMonth() === 0) {
      date = new Date(date.getFullYear() - 1, 11, date.getDate());
    } else {
      date = new Date(date.getFullYear(), date.getMonth() - 1, date.getDate());
    }
    return this.datePipe.transform(date, this.dateFormat)!;
  }

  private today(): string {
    // Today + 1 day - needed if the current day must be included
    const date = new Date();
    date.setDate(date.getDate() + 1);
    return this.datePipe.transform(date, this.dateFormat)!;
  }
  ngOnDestroy(): void {}
}
