import { Component, EventEmitter, inject, Output, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../../../service/project/project-service';
import { ProjectRequestNotification } from '../../../../types/entity/project-request-notification';
import { PositionForUser } from "../position-for-user/position-for-user";
import { NgClass } from "@angular/common";

@Component({
  selector: 'app-requests-project',
  imports: [PositionForUser, NgClass],
  templateUrl: './requests-project.html',
  styleUrl: './requests-project.css',
})
export class RequestsProject {
  private route = inject(ActivatedRoute);
  private projectService = inject(ProjectService);

  private projectSlug:string|null = "";

  showFormPositioForUser = false;

  notificationsOfProject = signal(<ProjectRequestNotification[]>[]);

  constructor(){
    this.route.paramMap.subscribe(params=>{
      this.projectSlug = params.get('slug');
    })
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

  ngOnInit(){
    this.loadNotifications()
  }

  

}
