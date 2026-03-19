import { Component, inject, signal } from '@angular/core';
import { Project } from '../../../../types/entity/project';
import { ActivatedRoute, RouterLink } from "@angular/router";
import { ProjectService } from '../../../service/project/project-service';
import { User } from '../../../../types/entity/user';
import { UserService } from '../../../service/user/user-service';
import { UserWithProjects } from '../../../../types/entity/user-with-projects';

@Component({
  selector: 'app-show-projects-of-user',
  imports: [RouterLink],
  templateUrl: './show-projects-of-user.html',
  styleUrl: './show-projects-of-user.css',
})
export class ShowProjectsOfUser {
  private route = inject(ActivatedRoute);

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
}
