import { Component, inject, signal } from '@angular/core';
import { ContentService } from '../../service/content/content-service';
import { Content } from '../../../types/entity/content';
import { ActivatedRoute, RouterLink } from "@angular/router";

@Component({
  selector: 'app-dashboard',
  imports: [RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {
  private contentService = inject(ContentService);

  private route = inject(ActivatedRoute);

  mostPopularContents = signal(<Content[]> ([]) );

  page:number = 1;

  ngOnInit(){
    this.route.paramMap.subscribe( params => {
      this.page = Number(params.get('page'));
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
