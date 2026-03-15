import { Component, EventEmitter, inject, Output, signal } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from '@angular/router';
import { ProjectService } from '../../../service/project/project-service';
import { ProjectRequestNotification } from '../../../../types/entity/project-request-notification';
import { PositionForUser } from "../position-for-user/position-for-user";
import { NgClass } from "@angular/common";
import { PositionSimplified } from '../../../../types/entity/position-simplified';
import { ProjectStatus } from '../../../../types/enums/project-status';

@Component({
  selector: 'app-requests-project',
  imports: [PositionForUser, NgClass, RouterLink],
  templateUrl: './requests-project.html',
  styleUrl: './requests-project.css',
})
export class RequestsProject {
  private route = inject(ActivatedRoute);
  private projectService = inject(ProjectService);

  private projectSlug:string|null = "";

  showFormPositioForUser = false;

  notificationsOfProject = signal(<ProjectRequestNotification[]>[]);
  notificationForForm = signal(<ProjectRequestNotification>({}));

  constructor(){
    this.route.paramMap.subscribe(params=>{
      this.projectSlug = params.get('slug');
    })
  }

  statusProjectForWord(status:ProjectStatus):string{
    if (status===ProjectStatus.ACCEPTED)
      return "aceitou"
    else
      return "recusou"
  }

  statusProjectForColor(status:ProjectStatus):string{
    if (status===ProjectStatus.ACCEPTED)
      return "emerald"
    else
      return "red"
  }

  closeFormPositionForuser(){
    this.showFormPositioForUser = false;
    this.loadNotifications()
  }

  loadNotifications(){
    this.projectService.getNotificationOfProjectBySlug(this.projectSlug as string).subscribe({
      next:(dado)=>{
        this.notificationsOfProject.set(dado);
      },
      error:(erro)=>{
        console.log(erro.error);
        
      }
    })
  }

  positionsOfProject = signal(<PositionSimplified[]>[]);

  loadPositionsOfProject(notification:ProjectRequestNotification){
    
    this.projectService.getPositionsOfProjectBySlug(notification.projectSlug).subscribe({
      next:(dado)=>{
        this.positionsOfProject.set(dado);
      
      },
      error:(erro)=>{
        console.log("ERRO: " +erro.error);
        
      }
    })
  }

  ngOnInit(){
    this.loadNotifications()
  }

  openFormPositioForUser(notification:ProjectRequestNotification){
    this.notificationForForm.set(notification);
    this.loadPositionsOfProject(notification);
    this.showFormPositioForUser = true;
  }

  ngOnSubmit(notificationId:number){
    this.projectService.updateNotificationAndRequest(notificationId, {newStatus:ProjectStatus.REJECTED, positionId:null}).subscribe({
      next:()=>{
        this.loadNotifications()
      },
      error:(erro)=>{
        console.log(erro.error);
        
      }
    })
  }

  

}
