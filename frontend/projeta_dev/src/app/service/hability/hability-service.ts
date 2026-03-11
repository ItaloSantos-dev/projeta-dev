import { inject, Injectable } from '@angular/core';
import { BackApi } from '../../api/back-api';
import { CreateHabilityDTO } from '../../../types/DTO/create-hability-dto';
import { Observable } from 'rxjs';
import { Hability } from '../../../types/entity/hability';

@Injectable({
  providedIn: 'root',
})
export class HabilityService {
  backApi = inject(BackApi);

  createHability(dado:CreateHabilityDTO):Observable<Hability>{
    console.log("dADO");
    console.log(dado);
    
    
    return this.backApi.createHability(dado);
  }
}
