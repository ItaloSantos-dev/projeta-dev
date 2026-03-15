import { Component, EventEmitter, inject, Input, Output, signal } from '@angular/core';
import { ProjectRequestNotification } from '../../../../types/entity/project-request-notification';
import { ProjectService } from '../../../service/project/project-service';
import { PositionSimplified } from '../../../../types/entity/position-simplified';
import { FormControl, FormGroup, Validators, ɵInternalFormsSharedModule } from '@angular/forms';
import { ProjectStatus } from '../../../../types/enums/project-status';

@Component({
  selector: 'app-position-for-user',
  imports: [ɵInternalFormsSharedModule],
  templateUrl: './position-for-user.html',
  styleUrl: './position-for-user.css',
})
export class PositionForUser {

  @Input() notification = signal(<ProjectRequestNotification>({}));

  @Output() closeForm = new EventEmitter<boolean>();
  
  @Input() positionsOfProject = signal(<PositionSimplified[]>[]);

  positionSelected:number|null = null


  private projectService = inject(ProjectService);

  createUpdateProjectRequestRequestDTO(newStatus:ProjectStatus, positionId:number=0){
    return {
      newStatus:newStatus,
      positionId:positionId
    }
  }

  ngOnSubmit(){
    if (this.positionSelected === null) {
      console.log("erro");
      
    }

    this.projectService.updateNotificationAndRequest(this.notification().id, this.createUpdateProjectRequestRequestDTO
    (ProjectStatus.ACCEPTED, this.positionSelected as number)).subscribe({
      next:()=>{
        this.closingForm()
      },
      error:(erro)=>{
        console.log(erro.error);
        
      }
    })
  }

  closingForm(){
    this.closeForm.emit(false);
  }
  
}
