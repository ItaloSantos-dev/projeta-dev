import { inject, Injectable } from '@angular/core';
import { RegisterDTO } from '../../../types/DTO/register-dto';
import { BackApi } from '../../api/back-api';
import { Observable } from 'rxjs';
import { Content } from '../../../types/DTO/content';
import { ContentRequestDTO } from '../../../types/DTO/content-request-dto';
import { HttpParams } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ContentService {
  private backApi = inject(BackApi);

  getMostPopularContents(page:number, strategy:string):Observable<Content[]>{
    //404 not found
    if (page<1) {
      console.log("Erro");
    }
    
    return this.backApi.getMostPopularContents(page, strategy);
  }

  getContent(data:ContentRequestDTO):Observable<Content>{
    return this.backApi.getContent(data);
  }

}
