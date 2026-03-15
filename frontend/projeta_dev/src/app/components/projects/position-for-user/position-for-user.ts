import { Component, EventEmitter, inject, Input, Output, signal } from '@angular/core';
import { ProjectRequestNotification } from '../../../../types/entity/project-request-notification';
import { ProjectService } from '../../../service/project/project-service';
import { PositionSimplified } from '../../../../types/entity/position-simplified';

@Component({
  selector: 'app-position-for-user',
  imports: [],
  templateUrl: './position-for-user.html',
  styleUrl: './position-for-user.css',
})
export class PositionForUser {

  @Input() notification = signal(<ProjectRequestNotification>({}));

  @Output() closeForm = new EventEmitter<boolean>();
  
  @Input() positionsOfProject = signal(<PositionSimplified[]>[]);

  private projectService = inject(ProjectService);

  closingForm(){
    this.closeForm.emit(false);
  }
  
}
