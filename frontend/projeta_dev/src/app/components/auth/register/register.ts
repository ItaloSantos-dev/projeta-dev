import { Component, inject } from '@angular/core';
import { RouterLink } from "@angular/router";
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

  authService = inject(AuthService)

  formRegister = new FormGroup({
    name: new FormControl('', Validators.required),
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(8)]),
    telephoneNumber: new FormControl('', Validators.required),
    experienceLevel: new FormControl('', [Validators.required]),
    principalStack: new FormControl('', Validators.required)
  })

  

}
