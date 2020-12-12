import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IAchat } from 'app/shared/model/achat.model';
import { AchatService } from './achat.service';

@Component({
  templateUrl: './achat-delete-dialog.component.html',
})
export class AchatDeleteDialogComponent {
  achat?: IAchat;

  constructor(protected achatService: AchatService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.achatService.delete(id).subscribe(() => {
      this.eventManager.broadcast('achatListModification');
      this.activeModal.close();
    });
  }
}
