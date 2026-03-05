import { Component, inject, signal } from '@angular/core';
import { ActivatedRoute, RouterLink } from "@angular/router";
import { ContentService } from '../../../service/content/content-service';
import { Content } from '../../../../types/entity/content';
import { ContentRequestDTO } from '../../../../types/DTO/content-request-dto';
import { MarkdownModule } from 'ngx-markdown';

@Component({
  selector: 'app-show-content',
  imports: [RouterLink, MarkdownModule],
  templateUrl: './show-content.html',
  styleUrl: './show-content.css',
})
export class ShowContent {

  private route = inject(ActivatedRoute)

  private contentService = inject(ContentService)

  content = signal(<Content>({} as Content))

  private contentRequest:ContentRequestDTO;


  constructor(){
    this.contentRequest = {
      slug:this.route.snapshot.paramMap.get('slug') as string,
      ownerName:this.route.snapshot.paramMap.get('owner') as string
    }
    
    console.log(this.contentRequest.ownerName);
    console.log(this.contentRequest.slug);
  }

  ngOnInit(){
    this.contentService.getContent(this.contentRequest).subscribe({
      next:(dado)=> {
        this.content.set(dado);
      },
      error:(erro)=>{
        console.log(erro.error.message);
        
      }
    })
  }
}
