import { Component, EventEmitter, inject, Output, signal } from '@angular/core';
import { NgClass } from "@angular/common";
import { FormControl, FormGroup, ReactiveFormsModule, Validators, ɵInternalFormsSharedModule } from '@angular/forms';
import { IconApi } from '../../../api/icon-api';
import { IconApiService } from '../../../service/icons/icon-api-service';
import { CreateHabilityDTO } from '../../../../types/DTO/create-hability-dto';

@Component({
  selector: 'app-create-hability',
  imports: [NgClass, ɵInternalFormsSharedModule, ReactiveFormsModule],
  templateUrl: './create-hability.html',
  styleUrl: './create-hability.css',
})
export class CreateHability {

  

  iconApiService = inject(IconApiService);

  formCreateHability = new FormGroup({
    title: new FormControl("", [Validators.required, Validators.minLength(3)]),
    hasIcon: new FormControl(false, [Validators.required]),
    iconLink: new FormControl("")
  })

  hasIcon = false;

  showIconsZoom=false;

  iconSearch = signal("");

  listLinks = signal<string[]>([]);

  linkSelected = signal("");

  linkIdSelected = signal(<number|null>(null));

  selecLink(index:number){
    this.linkSelected.set(this.listLinks()[index]);
    this.linkIdSelected.set(index)
    this.showIconsZoom = false
    this.formCreateHability.get('iconLink')?.setValue(this.linkSelected());
  }

  searchIcon(){
    this.linkIdSelected.set(null)
    this.linkSelected.set("");
    this.iconApiService.getIconsBySearch(this.iconSearch()).subscribe(icons =>{
      this.listLinks.set(icons)
    });
  }

  private createRequestHabilityDTO():CreateHabilityDTO{
    return {
      title:this.formCreateHability.get('title')?.value as string,
      hasIcon:this.formCreateHability.get('hasIcon')?.value as boolean,
      iconLink:this.formCreateHability.get('iconLink')?.value as string
    }
    
  }

  ngOnSubmit(){
    
    
  }

  @Output() closeForm = new EventEmitter<boolean>();
  closingForm(){
    console.log("emitindo");
    
    this.closeForm.emit(false);
  }
}
