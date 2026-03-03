import { Component, inject, signal } from '@angular/core';
import { ContentService } from '../../service/content/content-service';
import { Content } from '../../../types/DTO/content';
import { RouterLink } from "@angular/router";

@Component({
  selector: 'app-dashboard',
  imports: [RouterLink],
  templateUrl: './dashboard.html',
  styleUrl: './dashboard.css',
})
export class Dashboard {
  private contentService = inject(ContentService);

   mostPopularContents = signal(<Content[]> ([]) );

  ngOnInit(){
    this.contentService.getMostPopularContents().subscribe({
      next:(dados) => {
        this.mostPopularContents.set(dados);
      },
      error:(erro) => {
        console.log(erro.error.message);
        
      }
    })
  }
}
