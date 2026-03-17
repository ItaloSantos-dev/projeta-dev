import { Component, inject, signal } from '@angular/core';
import { UserService } from '../../../service/user/user-service';
import { ProjectService } from '../../../service/project/project-service';
import { ProjectRequestNotification } from '../../../../types/entity/project-request-notification';
import { PositionSimplified } from '../../../../types/entity/position-simplified';
import { ProjectStatus } from '../../../../types/enums/project-status';
import { PositionForUser } from "../../projects/position-for-user/position-for-user";
import { NgClass } from "@angular/common";
import { Router, RouterLink } from "@angular/router";
import { TokenService } from '../../../service/token/token-service';

@Component({
  selector: 'app-my-notifications',
  imports: [PositionForUser, NgClass, RouterLink],
  templateUrl: './my-notifications.html',
  styleUrl: './my-notifications.css',
})
export class MyNotifications {
  private userService = inject(UserService);
  private projectService = inject(ProjectService);
 
  positionsOfProject = signal(<PositionSimplified[]>[]);
  showFormPositioForUser = false;
  router = inject(Router);

  tokenService = inject(TokenService);

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

   notificationsResponse = signal(<ProjectRequestNotification[]> []);
   notificationsRequest = signal(<ProjectRequestNotification[]> []);

  loadNotifications(){
    this.userService.getNotificationsOfUserById().subscribe({
      next:(dado)=>{
        this.notificationsResponse.set(dado.filter(n => n.projectRequest.project.creator!==this.tokenService.getUsernameLogged()));
        this.notificationsRequest.set(dado.filter(n => n.projectRequest.project.creator===this.tokenService.getUsernameLogged()));
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

  notificationIsResponseOfMyRequest(notification:ProjectRequestNotification){
     const userOfToken = this.tokenService.getUsernameLogged();
      if (userOfToken===null){
        this.router.navigate(['/login']);
        return false;
      }
      return notification.projectRequest.project.creator !== userOfToken;
    
  }

  setNotificationRedById(id:number){
    this.userService.setNotificationReadById(id).subscribe({
      next:() =>{
        this.loadNotifications();
      },
      error:(erro)=>{
        console.log(erro.error);
        
      }
    })
  }
}
