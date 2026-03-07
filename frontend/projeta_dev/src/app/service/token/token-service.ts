import { Injectable } from '@angular/core';
import { jwtDecode } from 'jwt-decode';

export interface JwtPayload {
  sub: string;
  email: string;
  exp: number;
}
@Injectable({
  providedIn: 'root',
})
export class TokenService {

  private decodeToken(token:string):JwtPayload|null{
    try{
      return jwtDecode<JwtPayload>(token);
    }catch(error){
      return null;
    }
  }

  

  private tokenIsExpirated(token:string):boolean{
    const tokenDecoded:JwtPayload|null = this.decodeToken(token);
    if (tokenDecoded===null)
      return false;

    const now = Date.now()/1000;
    return tokenDecoded.exp<now;
  }


  tokenIsValid():boolean{
    const token = localStorage.getItem('token');
    if (!token)
      return false;

    if (this.decodeToken(token) === null)
      return false;

    if (this.tokenIsExpirated(token))
      return false;
    
    return true;
  }

  getUsernameLogged():string|null{

    if (!this.tokenIsValid())
      return null;

    return this.decodeToken(localStorage.getItem('token') as string)?.sub as string;
  }
}
