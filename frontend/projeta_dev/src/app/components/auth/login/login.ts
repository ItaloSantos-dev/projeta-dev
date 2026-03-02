import { Component, inject } from '@angular/core';
import { FormControl, FormGroup, Validators, ReactiveFormsModule} from '@angular/forms';
import { RouterLink } from "@angular/router";
import { AuthService } from '../../../service/auth-service';
import { LoginDTO } from '../../../../types/DTO/login-dto';

@Component({
  selector: 'app-login',
  imports: [ReactiveFormsModule, RouterLink],
  templateUrl: './login.html',
  styleUrl: './login.css',
})
export class Login {

  authService = inject(AuthService)

  formLogin = new FormGroup({
    email: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.minLength(8)])
  });

  private createLoginDTO(){
    const data:LoginDTO = {
      email: this.formLogin.get('email')?.value,
      
    }
  }

  ngSubmit(){
    this.authService.backApi.login().subscribe({
      next:(dado) => {
        console.log("Passou. TOKEN: " + dado);
      },
      error:(erro)=>{
        console.log("Erro");
        
      }
    })
  }
}
