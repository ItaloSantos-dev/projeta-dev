import { Component, EventEmitter, Output } from '@angular/core';
import { NgClass } from "@angular/common";

@Component({
  selector: 'app-create-hability',
  imports: [NgClass],
  templateUrl: './create-hability.html',
  styleUrl: './create-hability.css',
})
export class CreateHability {
  hasIcon = false;

  @Output() closeForm = new EventEmitter<boolean>();

  closingForm(){
    console.log("emitindo");
    
    this.closeForm.emit(false);
  }
}
