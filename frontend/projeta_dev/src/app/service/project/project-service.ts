import { inject, Injectable } from '@angular/core';
import { BackApi } from '../../api/back-api';
import { Observable } from 'rxjs';
import { CreateProjectDTO } from '../../../types/DTO/create-project-dto';

@Injectable({
  providedIn: 'root',
})
export class ProjectService {
  private backApi = inject(BackApi)

  createProject(data:CreateProjectDTO):Observable<any>{
    return this.backApi.createProject(data);
  }
}
