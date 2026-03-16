import { inject, Injectable } from '@angular/core';
import { BackApi } from '../../api/back-api';
import { Observable } from 'rxjs';
import { CreateProjectDTO } from '../../../types/DTO/create-project-dto';
import { MyProjectsDTO } from '../../../types/DTO/my-projetcts-DTO';
import { Project } from '../../../types/entity/project';
import { ProjectRequest } from '../../../types/entity/project-request';
import { ProjectRequestNotification } from '../../../types/entity/project-request-notification';
import { PositionSimplified } from '../../../types/entity/position-simplified';
import { UpdateProjectRequestRequestDTO } from '../../../types/DTO/update-project-request-request-dto';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  private backApi = inject(BackApi)

  createProject(data:CreateProjectDTO):Observable<any>{
    return this.backApi.createProject(data);
  }

  getMyProjects():Observable<MyProjectsDTO>{
    return this.backApi.getMyProjects();
  }

  getProjectOfUserByUsernameAndSlug(username:string,slug:string):Observable<Project>{
    return this.backApi.getProjectOfUserByUsernameAndSlug(username, slug);
  }

  createProjectRequest(projectId:number):Observable<ProjectRequest>{
    return this.backApi.createProjectRequest(projectId);
  }

  getRequestsOfProjectBySlug(slug:string):Observable<ProjectRequestNotification[]>{
    return this.backApi.getRequestsOfProjectBySlug(slug);
  }

  getPositionsOfProjectBySlug(slug:string):Observable<PositionSimplified[]>{
    return this.backApi.getPositionsOfProjectBySlug(slug);
  }

  updateNotificationAndRequest(notificationId:number,data:UpdateProjectRequestRequestDTO):Observable<void>{
    return this.backApi.updateNotificationAndRequest(notificationId,data);
  }
}
