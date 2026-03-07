import { inject, Injectable } from '@angular/core';
import { LoginDTO } from '../../types/DTO/login-dto';
import { BackApi } from '../api/back-api';
import { Observable } from 'rxjs';
import { RegisterDTO } from '../../types/DTO/register-dto';
import { Router } from '@angular/router';
import { jwtDecode } from 'jwt-decode';

interface JwtPayload {
  sub: string;
  email: string;
  exp: number;
}

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

  tokenIsExpirated(){
    const token = localStorage.getItem('token') as string;
    if (!token) {
      this.router.navigate(['/login']);
    }

    const tokenDecoded = jwtDecode<JwtPayload>(token);
    const now = Date.now()/1000;
    return tokenDecoded.exp<now;
  
  }

  getUsernameLogged():string{
    const token = localStorage.getItem('token') as string;
    if (!token) {
      this.router.navigate(['/login']);
    }

    const tokenDecoded = jwtDecode<JwtPayload>(token);
    console.log("sub: "+tokenDecoded.sub);
    
    return tokenDecoded.sub;
  }

  saveToken(token:string){
    localStorage.setItem('token', token);
  }

  islogged():boolean{
    const token = localStorage.getItem('token');
    if (!token){
      localStorage.clear();
      return false;
    }

    if (this.tokenIsExpirated()) {
      localStorage.clear();
      this.router.navigate(['/login']);
    }

    return true;
  }

  getToken():string|null{
    return this.islogged()? localStorage.getItem('token'):null;
  }

  logout(){
    localStorage.clear();
    this.router.navigate(['/login'])
  }

}
