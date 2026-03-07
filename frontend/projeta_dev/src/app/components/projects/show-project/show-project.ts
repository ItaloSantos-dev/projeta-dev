import { Component, inject, signal } from '@angular/core';
import { NgClass } from '@angular/common';
import { ActivatedRoute, RouterLink } from "@angular/router";
import { ProjectService } from '../../../service/project/project-service';
import { Project } from '../../../../types/entity/project';
import { AuthService } from '../../../service/auth-service';

@Component({
  selector: 'app-show-project',
  imports: [NgClass, RouterLink],
  templateUrl: './show-project.html',
  styleUrl: './show-project.css',
})
export class ShowProject {
  usersOpen = false;
  private route = inject(ActivatedRoute);

  private projectService = inject(ProjectService);

  private authServie = inject(AuthService);

  project = signal(<Project>{});

  private username = "";
  private slug ="";

  statusColor = new Map<string, string>([
    ["OPEND", "emerald"],
    ["CLOSED", "red"],
    ["FINALED", "gray"]
  ]);

  usernameLogged = signal("");

  constructor(){
    this.route.paramMap.subscribe(params => {
      this.username = params.get("username") as string,
      this.slug = params.get("slug") as string
    });
  };

  ngOnInit(){
    this.usernameLogged.set(this.authServie.getUsernameLogged() as string)
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
