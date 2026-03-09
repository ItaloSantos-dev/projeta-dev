import { inject, Injectable } from '@angular/core';
import { IconApi } from '../../api/icon-api';
import { Icon } from '../../../types/entity/icon';
import { map, Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class IconApiService {
  iconApi = inject(IconApi);

  createIconLinkList(icon:Icon):string[]{
    const listLinks:string[] = [];
    icon.icons.forEach( link =>{
      const linkSeparated = link.split(":");
      listLinks.push("https://api.iconify.design/" + linkSeparated[0] + "/" + linkSeparated[1] + ".svg");
    });
    return listLinks;
  }

  getIconsBySearch(search:string):Observable<string[]>{
    return this.iconApi.getIconsBySearch(search).pipe(
      map(dado => this.createIconLinkList(dado))
    );
  }

}
