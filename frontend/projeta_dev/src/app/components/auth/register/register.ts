import { Component, inject } from '@angular/core';
import { Router, RouterLink } from "@angular/router";
import { FormControl, FormGroup, ReactiveFormsModule, Validators, ɵInternalFormsSharedModule } from '@angular/forms';
import { AuthService } from '../../../service/auth-service';
import { UserExperienceLevel } from '../../../../types/enums/user-experience-level';

@Component({
  selector: 'app-register',
  imports: [RouterLink, ɵInternalFormsSharedModule, ReactiveFormsModule],
  templateUrl: './register.html',
  styleUrl: './register.css',
})
export class Register {

  authService = inject(AuthService);
  router = inject(Router);

  formRegister = new FormGroup({
    name: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    username: new FormControl('', Validators.required),
    password: new FormControl('', [Validators.required, Validators.minLength(8)]),
    telephoneNumber: new FormControl('', Validators.required),
    experienceLevel: new FormControl('', [Validators.required]),
    principalStack: new FormControl('', Validators.required)
  });



  private createRegisterDTO(){
    return {
      name:this.formRegister.get('name')?.value as string,
      email:this.formRegister.get('email')?.value as string,
      username:this.formRegister.get('username')?.value as string,
      password:this.formRegister.get('password')?.value as string,
      telephoneNumber:this.formRegister.get('telephoneNumber')?.value as string,
      principalStack:this.formRegister.get('principalStack')?.value as string,
      experienceLevel:this.formRegister.get('experienceLevel')?.value as UserExperienceLevel
    }
  }

  ngSubmit(){
    this.authService.register(this.createRegisterDTO()).subscribe({
      next:() =>{
        this.router.navigate(['login']);
      },
      error:(erro)=>{
        console.log(erro.error.message);
        
      }
    })
  }

  

}
