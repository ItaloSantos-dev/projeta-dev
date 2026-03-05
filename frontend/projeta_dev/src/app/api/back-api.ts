
import { inject, Inject, Injectable } from "@angular/core";
import { LoginDTO } from "../../types/DTO/login-dto";
import { Observable } from "rxjs";
import { HttpClient, HttpParams } from "@angular/common/http";
import { RegisterDTO } from "../../types/DTO/register-dto";
import { Content } from "../../types/entity/content";
import { ContentRequestDTO } from "../../types/DTO/content-request-dto";

@Injectable({
    providedIn:'root',
})
export class BackApi {
    
    private urlBase = "http://localhost:8080/";

    private httpClient = inject(HttpClient);

    login(data:LoginDTO):Observable<string> {
        return this.httpClient.post(this.urlBase + "auth/login", data, {responseType:'text'});
    }
    register(data:RegisterDTO):Observable<any>{
        return this.httpClient.post<any>(this.urlBase + "auth/register", data);
    }

    getMostPopularContents(page:number, strategy:string):Observable<Content[]>{
        let params = new HttpParams();
        params = params.set("page", page);
        params = params.append("strategy", strategy);
        return this.httpClient.get<Content[]>(this.urlBase+"contents/most-popular", {params:params});
    }

    getContent(data:ContentRequestDTO):Observable<Content>{ 
        console.log(data);
        return this.httpClient.post<Content>(this.urlBase + "contents", data);
    }
}

