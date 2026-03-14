import { Component, EventEmitter, Output } from '@angular/core';

@Component({
  selector: 'app-position-for-user',
  imports: [],
  templateUrl: './position-for-user.html',
  styleUrl: './position-for-user.css',
})
export class PositionForUser {
  @Output() closeForm = new EventEmitter<boolean>();
  closingForm(){
    this.closeForm.emit(false);
  }
}
