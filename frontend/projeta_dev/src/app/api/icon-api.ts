import { HttpClient, HttpParams } from "@angular/common/http";
import { inject, Injectable } from "@angular/core";
import { Observable } from "rxjs";
import { Icon } from "../../types/entity/icon";

@Injectable({
    providedIn:'root',
})

export class IconApi{
    httpClient = inject(HttpClient);
    private urlBase = "https://api.iconify.design/search";
    getIconsBySearch(search:string):Observable<Icon>{
        let params = new HttpParams();
        params = params.set("query", search);
        return this.httpClient.get<Icon>(this.urlBase, {params:params});
    }
}