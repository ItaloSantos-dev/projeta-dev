import { Component, inject, signal } from '@angular/core';
import { ContentService } from '../../service/content/content-service';
import { Content } from '../../../types/entity/content';
import { ActivatedRoute, Router, RouterLink } from "@angular/router";
import { AuthService } from '../../service/auth-service';

@Component({
  selector: 'app-dashboard',
  imports: [RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {
  private contentService = inject(ContentService);

  private authService = inject(AuthService);

  private route = inject(ActivatedRoute);

  private router = inject(Router)

  mostPopularContents = signal(<Content[]> ([]) );

  page:number = 1;

  ngOnInit(){
    if (!this.authService.islogged()) {
      this.router.navigate(['/home'])
    }

    this.route.paramMap.subscribe( params => {
      this.page = Number(params.get('page')) ===0? 1: Number(params.get('page'));
      this.loadPage()
    })
  }

  loadPage(){
    console.log("Recarregando");
    
    this.contentService.getMostPopularContents(this.page, "RELEVANT").subscribe({
      next:(dados) => {
        this.mostPopularContents.set(dados);
      },
      error:(erro) => {
        console.log(erro.error.message);
        
      }
    })
  }
}
