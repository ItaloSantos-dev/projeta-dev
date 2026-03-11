import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute, Router, RouterLink } from "@angular/router";
import { NgClass } from '@angular/common';
import { UserService } from '../../../service/user/user-service';
import { User } from '../../../../types/entity/user';
import { TokenService } from '../../../service/token/token-service';
import { CreateHability } from "../create-hability/create-hability";
import { HabilityService } from '../../../service/hability/hability-service';

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
  private habilityService = inject(HabilityService);

  shorFormCreateHability = signal(false);

  showFormCreateHability(){
    console.log("abbb");
    
    this.shorFormCreateHability.set(true);
  }

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

  ngOnDelete(id:number){
    this.habilityService.deleteHability(id).subscribe({
      next:()=>{
        this.loadUser()
      },
      error:(erro)=>{
        console.log(erro.error);
        
      }
    })
  }

  closeFormCreateHability(){
    console.log("fechando");
    
    this.shorFormCreateHability.set(false);
    this.loadUser();
  }

  loadUser(){
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

  private username = "";

  constructor(){
    this.route.paramMap.subscribe(params =>{
      this.username = params.get('username') as string
    })
  }

  completeListLinks(){
    this.listLinks.splice(0, this.listLinks.length)
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
    this.loadUser();
  }
}
