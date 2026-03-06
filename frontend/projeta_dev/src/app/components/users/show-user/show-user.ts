import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from "@angular/router";
import { NgClass } from '@angular/common';
import { UserService } from '../../../service/user/user-service';
import { User } from '../../../../types/entity/user';

@Component({
  selector: 'app-show-user',
  imports: [RouterLink, NgClass],
  templateUrl: './show-user.html',
  styleUrl: './show-user.css',
})
export class ShowUser {
  projetosOpen = false;
  private route = inject(ActivatedRoute);
  private userService = inject(UserService);

  user = signal(<User> ({} as User));

  private username = "";

  constructor(){
    this.route.paramMap.subscribe(params =>{
      this.username = params.get('username') as string
    })
  }

  ngOnInit(){
    this.userService.getUserByUsername(this.username).subscribe({
      next:(dado) =>{
        this.user.set(dado);
        console.log(this.user().myProjects[0]);
        
      },
      error:(erro) => {
        console.log(erro.error);
        
      }
    })
  }
}
