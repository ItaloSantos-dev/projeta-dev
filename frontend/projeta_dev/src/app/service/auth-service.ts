import { inject, Injectable } from '@angular/core';
import { LoginDTO } from '../../types/DTO/login-dto';
import { BackApi } from '../api/back-api';
import { BehaviorSubject, Observable, Subject } from 'rxjs';
import { RegisterDTO } from '../../types/DTO/register-dto';
import { Router } from '@angular/router';
import { JwtPayload, TokenService } from './token/token-service';
@Injectable({
  providedIn: 'root',
})


export class AuthService {
  
  backApi = inject(BackApi);
  tokenService = inject(TokenService);
  router = inject(Router);


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
    return this.tokenService.tokenIsValid();
  }

  getToken():string|null{
    return this.islogged()? localStorage.getItem('token'):null;
  }


  logout(){
    localStorage.clear();
    this.router.navigate(['/login'])
  }

  saveUsername(){
    const username = this.tokenService.getUsernameLogged();
    console.log("user"+ username);
    
    if (username)
      localStorage.setItem('usernameLogged', username);
  }
  

  private trigger = new Subject<void>();


  trigger$ = this.trigger.asObservable();

  emitTrigger(){
    this.trigger.next();
  }

  
  

  

  

  

}
