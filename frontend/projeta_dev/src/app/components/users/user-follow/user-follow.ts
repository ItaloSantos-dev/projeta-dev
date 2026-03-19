import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { User } from '../../../../types/entity/user';
import { UserService } from '../../../service/user/user-service';
import { UserExperienceLevel } from '../../../../types/enums/user-experience-level';
import { NgClass } from "@angular/common";
import { AuthService } from '../../../service/auth-service';
import { TokenService } from '../../../service/token/token-service';

@Component({
  selector: 'app-user-follow',
  imports: [NgClass],
  templateUrl: './user-follow.html',
  styleUrl: './user-follow.css',
})
export class UserFollow {

  private userService = inject(UserService);
  private tokenService = inject(TokenService);
  private username = "";

  private router = inject(Router);
  private route = inject(ActivatedRoute);
  users = signal(<User[]> []);
  typeUser = 0;

  /*
  experienceLevelForColor(experience:UserExperienceLevel):string{
    
    if (experience === UserExperienceLevel.TRAINEE) {
      console.log(1);
      
      return 'gray';
    }
    else if(experience === UserExperienceLevel.JUNIOR){
      console.log(2);

      return 'blue';
    }
    else if (experience === UserExperienceLevel.PLENO){
      
      return 'orange'
    }
    else{
      return 'green'
    }
  }
  */

  experienceLevelClasses: any = {
    TRAINEE: 'bg-gray-500/20 text-gray-400 border-gray-500/30',
    JUNIOR: 'bg-blue-500/20 text-blue-400 border-blue-500/30',
    PLENO: 'bg-orange-500/20 text-orange-400 border-orange-500/30',
    SENIOR: 'bg-green-500/20 text-green-400 border-green-500/30'
  };

  constructor(){
    this.route.paramMap.subscribe(params => {
      this.username = params.get('username') as string
    })
  }

  ngOnInit(){
    this.loadUsers();
  }

  loadUsers(){
    const url = this.router.url;
    //Exibe seguimores
    if (url.includes("following")) {
      this.userService.getFollowingOfUser(this.username).subscribe({
        next:(dado) => {
          this.users.set(dado);
        },
        error:(erro) => {
          console.log(erro.error);
          
        }
      })
      this.typeUser = 1;
    }
    //Exbibe quem o user segue
    else if(url.includes("followers")){
      this.userService.getFollowersOfUser(this.username).subscribe({
        next:(dado) => {
          this.users.set(dado);
        },
        error:(erro) => {
          console.log(erro.error);
          
        }
      })
      this.typeUser = 2;
    }
  }

  userLoggedFollowThisUser(user:User):boolean{
    return user.followers.includes(localStorage.getItem('usernameLogged') as string);
  }
  userLoggeidIsActualUser(){
    return this.username === this.tokenService.getUsernameLogged();
  }

  unfollowUserById(id:number){
    this.userService.deleteByUserFollowingIdAndUserFollowedId(id).subscribe({
      next:()=>{
        this.loadUsers();
      },
      error:(erro)=>{
        console.log(erro.error);
        
      }
    })
  }
}
