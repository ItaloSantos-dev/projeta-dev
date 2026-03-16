import { Component, inject, signal } from '@angular/core';
import { NgClass } from '@angular/common';
import { ActivatedRoute, Router, RouterLink } from "@angular/router";
import { ProjectService } from '../../../service/project/project-service';
import { Project } from '../../../../types/entity/project';
import { AuthService } from '../../../service/auth-service';
import { TokenService } from '../../../service/token/token-service';
import { InputType } from '../../../../types/enums/input-type';

@Component({
  selector: 'app-show-project',
  imports: [ RouterLink],
  templateUrl: './show-project.html',
  styleUrl: './show-project.css',
})
export class ShowProject {
  usersOpen = false;
  private route = inject(ActivatedRoute);

  private router = inject(Router);

  private projectService = inject(ProjectService);

  tokenService = inject(TokenService);

  project = signal(<Project>{});

  private username = "";
  private slug ="";

  statusColor = new Map<string, string>([
    ["OPEND", "emerald"],
    ["CLOSED", "red"],
    ["FINALED", "gray"]
  ]);

  inputType = InputType;


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

  userLoggedIsContributorOfProject():boolean{
    const userOfToken = this.tokenService.getUsernameLogged();
    if (userOfToken===null){
      this.router.navigate(['/login']);
      return false;
    }
    else{
      return this.project().contibutors.includes(userOfToken);
    }
    
  }


  createProjectRequest(){
    this.projectService.createProjectRequest(this.project().id).subscribe({
      next:(dado)=>{
        console.log(dado.status);
        
      },
      error:(erro)=>{
        console.log(erro.error);
        
      }
    })
  }


  constructor(){
    this.route.paramMap.subscribe(params => {
      this.username = params.get("username") as string,
      this.slug = params.get("slug") as string
    });
  };

  ngOnInit(){
    this.projectService.getProjectOfUserByUsernameAndSlug(this.username, this.slug).subscribe({
      next:(dado) =>{
        this.project.set(dado);
        console.log(this.project().contibutors);
        
      },
      error:(erro)=>{
        console.log(erro.error);
        
      }
    })
  }


}
