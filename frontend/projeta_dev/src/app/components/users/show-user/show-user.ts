import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from "@angular/router";
import { NgClass } from '@angular/common';
import { UserService } from '../../../service/user/user-service';
import { User } from '../../../../types/entity/user';
import { TokenService } from '../../../service/token/token-service';
import { CreateHability } from "../create-hability/create-hability";

@Component({
  selector: 'app-show-user',
  imports: [RouterLink, NgClass, CreateHability],
  templateUrl: './show-user.html',
  styleUrl: './show-user.css',
})
export class ShowUser {
  projetosOpen = false;
  private route = inject(ActivatedRoute);
  private router = inject(Router)
  private userService = inject(UserService);
  private tokenService = inject(TokenService);

  shorFormCreateHability = false;

  user = signal(<User> ({} as User));

  listLinks:string[] = [];

  userLoggedIsCreator():boolean{
    const userOfToken = this.tokenService.getUsernameLogged();
    if (userOfToken===null){
      this.router.navigate(['/login']);
      return false;
    }
    else{
      return userOfToken===this.username;
    }
    
  }

  private username = "";

  constructor(){
    this.route.paramMap.subscribe(params =>{
      this.username = params.get('username') as string
    })
  }

  completeListLinks(){
    if (this.user().link1) {
      this.listLinks.push(this.user().link1);
    }

    if (this.user().link2) {
      this.listLinks.push(this.user().link2);
    }

    if (this.user().link3) {
      this.listLinks.push(this.user().link3);
    }

    if (this.user().link4) {
      this.listLinks.push(this.user().link4);
    }

    if (this.user().link5) {
      this.listLinks.push(this.user().link5);
    }
  }

  ngOnInit(){
    this.userService.getUserByUsername(this.username).subscribe({
      next:(dado) =>{
        this.user.set(dado);
        console.log(this.user().myProjects[0]);
        this.completeListLinks()
        
      },
      error:(erro) => {
        console.log(erro.error);
        
      }
    })
  }
}
