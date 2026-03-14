import { Component, EventEmitter, Input, Output, signal } from '@angular/core';
import { ProjectRequestNotification } from '../../../../types/entity/project-request-notification';

@Component({
  selector: 'app-position-for-user',
  imports: [],
  templateUrl: './position-for-user.html',
  styleUrl: './position-for-user.css',
})
export class PositionForUser {

  @Input() notification = signal(<ProjectRequestNotification>({}));

  @Output() closeForm = new EventEmitter<boolean>();
  closingForm(){
    this.closeForm.emit(false);
  }

  ngOnInit(){
    console.log(this.notification());
  }
  
}
