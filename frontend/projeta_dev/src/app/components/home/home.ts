import { Component, inject } from '@angular/core';
import { Router, RouterLink } from "@angular/router";
import { AuthService } from '../../service/auth-service';

@Component({
  selector: 'app-home',
  imports: [RouterLink],
  templateUrl: './home.html',
  styleUrl: './home.css',
})
export class Home {
  private authService = inject(AuthService);
  private router = inject(Router);

  ngOnInit(){
    if (this.authService.islogged()) {
      this.router.navigate(['/'])
    }
  }
}
