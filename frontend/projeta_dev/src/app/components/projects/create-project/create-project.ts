import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, Validators, ɵInternalFormsSharedModule, ReactiveFormsModule } from '@angular/forms';
import { Router, RouterLink } from "@angular/router";
import { CreateProjectDTO } from '../../../../types/DTO/create-project-dto';
import { ProjectService } from '../../../service/project/project-service';
import { AuthService } from '../../../service/auth-service';
import { TokenService } from '../../../service/token/token-service';

@Component({
  selector: 'app-create-project',
  imports: [RouterLink, ɵInternalFormsSharedModule, ReactiveFormsModule],
  templateUrl: './create-project.html',
  styleUrl: './create-project.css',
})
export class CreateProject {

  private projectService = inject(ProjectService);
  private router = inject(Router);
  private tokenService = inject(TokenService)

  formCreateProject = new FormGroup({
    title: new FormControl("", Validators.required),
    imgUrl: new FormControl(""),
    description: new FormControl("", Validators.required),
    stack: new FormControl("", Validators.required),
    inputType: new FormControl("", Validators.required),
    paid: new FormControl(false, Validators.required),
    repositoryLink: new FormControl(""),
    principalPosition: new FormControl("", Validators.required)
  })

  createACreateProjectDto():CreateProjectDTO{
    return {
      title: this.formCreateProject.get('title')?.value as string,
      imgUrl: this.formCreateProject.get('imgUrl')?.value as string,
      description: this.formCreateProject.get('description')?.value as string,
      stack: this.formCreateProject.get('stack')?.value as string,
      inputType: this.formCreateProject.get('inputType')?.value as string,
      paid: this.formCreateProject.get('paid')?.value as boolean ,
      repositoryLink: this.formCreateProject.get('repositoryLink')?.value as string,
      principalPosition: this.formCreateProject.get('principalPosition')?.value as string,
    }
  }

  ngSubmit(){
    this.projectService.createProject(this.createACreateProjectDto()).subscribe({
      next:()=>{
        this.router.navigate([this.tokenService.getUsernameLogged(),'projects'])
        
      },
      error:(erro)=>{
        console.log(erro.error);
      }
    })
  }


}
