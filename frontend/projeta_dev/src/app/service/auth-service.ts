import { inject, Injectable } from '@angular/core';
import { LoginDTO } from '../../types/DTO/login-dto';
import { BackApi } from '../api/back-api';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  
  backApi = inject(BackApi)

  login(data:LoginDTO):Observable<string>{
    return this.backApi.login(data);
  }

  saveToken(token:string){
    localStorage.setItem('token', token);
  }

}
