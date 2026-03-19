import { Component, inject } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-user-follow',
  imports: [],
  templateUrl: './user-follow.html',
  styleUrl: './user-follow.css',
})
export class UserFollow {
  private router = inject(Router);

  ngOnInit(){
    const url = this.router.url;
    //Exibe seguimores
    if (url.includes("followers")) {
      
    }
    //Exbibe quem o user segue
    else if(url.includes("following")){

    }
    
  }
}
