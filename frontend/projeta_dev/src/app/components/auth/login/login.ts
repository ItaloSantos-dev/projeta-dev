import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule} from '@angular/forms';
import { Router, RouterLink } from "@angular/router";
import { AuthService } from '../../../service/auth-service';
import { LoginDTO } from '../../../../types/DTO/login-dto';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  authService = inject(AuthService);
  router = inject(Router)

  formLogin = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required])
  });

  private createLoginDTO():LoginDTO{
    return {
      email: this.formLogin.get('email')?.value as string,
      password: this.formLogin.get('password')?.value as string
    }
  }

  ngSubmit(){
    this.authService.backApi.login(this.createLoginDTO()).subscribe({
      next:(dado) => {
        this.authService.saveToken(dado);
        this.router.navigate(['/dashboard']);
      },
      error:(erro)=>{
        console.log("Erro: " +erro.message);
        
      }
    })
  }
}
