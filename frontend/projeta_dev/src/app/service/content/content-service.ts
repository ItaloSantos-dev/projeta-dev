import { inject, Injectable } from '@angular/core';
import { RegisterDTO } from '../../../types/DTO/register-dto';
import { BackApi } from '../../api/back-api';
import { Observable } from 'rxjs';
import { Content } from '../../../types/DTO/content';
import { ContentRequestDTO } from '../../../types/DTO/content-request-dto';

@Injectable({
  providedIn: 'root',
})
export class ContentService {
  private backApi = inject(BackApi);

  getMostPopularContents():Observable<Content[]>{
    return this.backApi.getMostPopularContents();
  }

  getContent(data:ContentRequestDTO):Observable<Content>{
    return this.backApi.getContent(data);
  }

}
