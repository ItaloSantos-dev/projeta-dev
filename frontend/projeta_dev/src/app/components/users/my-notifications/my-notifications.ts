import { Component, inject, signal } from '@angular/core';
import { UserService } from '../../../service/user/user-service';
import { ProjectService } from '../../../service/project/project-service';
import { ProjectRequestNotification } from '../../../../types/entity/project-request-notification';
import { PositionSimplified } from '../../../../types/entity/position-simplified';
import { ProjectStatus } from '../../../../types/enums/project-status';
import { PositionForUser } from "../../projects/position-for-user/position-for-user";
import { NgClass } from "@angular/common";
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-my-notifications',
  imports: [PositionForUser, NgClass, RouterLink],
  templateUrl: './my-notifications.html',
  styleUrl: './my-notifications.css',
})
export class MyNotifications {
  private userService = inject(UserService);
  private projectService = inject(ProjectService);
  notifications = signal(<ProjectRequestNotification[]> []);
  positionsOfProject = signal(<PositionSimplified[]>[]);
  showFormPositioForUser = false;

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


  ngOnInit(){
    this.loadNotifications();
  }

  loadNotifications(){
    this.userService.getNotificationsOfUserById().subscribe({
      next:(dado)=>{
        this.notifications.set(dado);
      },
      error:(erro)=>{
        console.log(erro.error);
        
      }
    })
  }

  notificationForForm = signal(<ProjectRequestNotification>({}));

  openFormPositioForUser(notification:ProjectRequestNotification){
    this.notificationForForm.set(notification);
    this.loadPositionsOfProject(notification);
    this.showFormPositioForUser = true;
  }

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

  closeFormPositionForuser(){
    this.showFormPositioForUser = false;
    this.loadNotifications()
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
