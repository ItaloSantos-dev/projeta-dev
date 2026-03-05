import { Component, inject, signal } from '@angular/core';
import { NgClass } from '@angular/common';
import { RouterLink } from "@angular/router";
import { ProjectService } from '../../../service/project/project-service';
import { MyProjectsDTO } from '../../../../types/DTO/my-projetcts-DTO';

@Component({
  selector: 'app-show-my-projects',
  imports: [NgClass, RouterLink],
  templateUrl: './show-my-projects.html',
  styleUrl: './show-my-projects.css',
})
export class ShowMyProjects {

  private projectService = inject(ProjectService);

  myProjectsOpen = false;
  iamInProjectsOpen = false;

  myProjectsCount = 0;
  iamInProjectsCount = 0;

  myProjects = signal(<MyProjectsDTO> ({} as MyProjectsDTO));

  ngOnInit(){
    this.projectService.getMyProjects().subscribe({
      next:(dado) => {
        this.myProjects.set(dado);
        this.myProjectsCount = this.myProjects().projectsICreated.length
        this.iamInProjectsCount = this.myProjects().projectsIParticipe.length
      },
      error: (erro)=>{
        console.log(erro.error);
        
      }
    })
  }

}
