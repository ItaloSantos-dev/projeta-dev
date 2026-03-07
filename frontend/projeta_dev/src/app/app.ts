import { Component, inject, signal } from '@angular/core';
import { RouterOutlet, RouterLinkWithHref, Router } from '@angular/router';
import { AuthService } from './service/auth-service';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet, RouterLinkWithHref],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App {
  protected readonly title = signal('projeta_dev');

  private router = inject(Router);

  authService = inject(AuthService);

  usernameLogged = signal("");

  ngOnInit(){
    console.log(this.usernameLogged);
    
    this.usernameLogged.set(this.authService.getUsernameLogged() as string)
    if (this.usernameLogged()===null||this.usernameLogged()==="") {
      this.router.navigate(['/login'])
    }
  }
  

}
