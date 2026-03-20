import { Component, inject, signal } from '@angular/core';
import { Project } from '../../../../types/entity/project';
import { ActivatedRoute, Router, RouterLink } from "@angular/router";
import { ProjectService } from '../../../service/project/project-service';
import { User } from '../../../../types/entity/user';
import { UserService } from '../../../service/user/user-service';
import { UserWithProjects } from '../../../../types/entity/user-with-projects';
import { TokenService } from '../../../service/token/token-service';

@Component({
  selector: 'app-show-projects-of-user',
  imports: [RouterLink],
  templateUrl: './show-projects-of-user.html',
  styleUrl: './show-projects-of-user.css',
})
export class ShowProjectsOfUser {
  private route = inject(ActivatedRoute);
  private router = inject(Router);
  private tokenService = inject(TokenService);
  private projectService = inject(ProjectService);

  projectsCount=0;

  statusColor = new Map<string, string>([
    ["OPEND", "emerald"],
    ["CLOSED", "red"],
    ["FINALED", "gray"]
  ])

  user = signal(<UserWithProjects>{})

  username = "";

  private userService = inject(UserService);

  constructor(){
    this.route.paramMap.subscribe(params => {
      this.username = params.get('username') as string;
    })
  }

  ngOnInit(){
    this.loadUserWithProjects();
  }

  loadUserWithProjects(){
    this.userService.getUserWithProjects(this.username).subscribe({
      next:(dado)=>{
        this.user.set(dado);
        this.projectsCount = this.user().myProjects.length
      },
      error: (erro)=>{
        console.log(erro.error);
        
      }
    })
  }

  fixedProjecById(id:number){
    this.projectService.fixedProjectById(id).subscribe({
      next:()=>{
        this.router.navigate([this.user().username])
      },
      error: (erro)=>{
        console.log(erro.error);
        
      }
    })
  }

  thisUserIsUserLogged(user:UserWithProjects){
    return user.username === this.tokenService.getUsernameLogged();
  }
}
