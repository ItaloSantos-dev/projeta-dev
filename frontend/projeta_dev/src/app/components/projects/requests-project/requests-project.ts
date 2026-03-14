import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ProjectService } from '../../../service/project/project-service';
import { ProjectRequestNotification } from '../../../../types/entity/project-request-notification';

@Component({
  selector: 'app-requests-project',
  imports: [],
  templateUrl: './requests-project.html',
  styleUrl: './requests-project.css',
})
export class RequestsProject {
  private route = inject(ActivatedRoute);
  private projectService = inject(ProjectService);

  private projectSlug:string|null = "";

  notificationsOfProject = signal(<ProjectRequestNotification[]>[]);

  constructor(){
    this.route.paramMap.subscribe(params=>{
      this.projectSlug = params.get('slug');
    })
  }

  ngOnInit(){
    this.projectService.getNotificationOfProjectBySlug(this.projectSlug as string).subscribe({
      next:(dado)=>{
        this.notificationsOfProject.set(dado);
      },
      error:(erro)=>{
        console.log(erro.error);
        
      }
    })
  }

}
