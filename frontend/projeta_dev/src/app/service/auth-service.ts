import { inject, Injectable } from '@angular/core';
import { LoginDTO } from '../../types/DTO/login-dto';
import { BackApi } from '../api/back-api';
import { Observable } from 'rxjs';
import { RegisterDTO } from '../../types/DTO/register-dto';
import { Router } from '@angular/router';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  
  backApi = inject(BackApi)
  router = inject(Router)

  login(data:LoginDTO):Observable<string>{
    return this.backApi.login(data);
  }

  register(data:RegisterDTO):Observable<any>{
    return this.backApi.register(data);
  }

  saveToken(token:string){
    localStorage.setItem('token', token);
  }

  islogged():boolean{
    const token = localStorage.getItem('token');
    return token!=null?true:false; 
  }

  getToken():string|null{
    return this.islogged()? localStorage.getItem('token'):null;
  }

  logout(){
    localStorage.clear();
    this.router.navigate(['/login'])
  }

}
