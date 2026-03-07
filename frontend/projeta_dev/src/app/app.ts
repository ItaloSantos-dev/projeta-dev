import { Component, inject, signal } from '@angular/core';
import { RouterOutlet, RouterLinkWithHref, Router } from '@angular/router';
import { AuthService } from './service/auth-service';
import { TokenService } from './service/token/token-service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLinkWithHref],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('projeta_dev');

  private router = inject(Router);

  tokenService = inject(TokenService);
  authService = inject(AuthService);

  usernameLogged = signal("");

  ngOnInit(){
    
    const username = this.tokenService.getUsernameLogged();
    
    if (username===null){
      console.log("è nulo");
      
      localStorage.clear()
      this.router.navigate(['/login'])
    }else{
      this.usernameLogged.set(username as string);
    }

    
  }
  

}
