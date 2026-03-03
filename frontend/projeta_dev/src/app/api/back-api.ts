
import { inject, Inject, Injectable } from "@angular/core";
import { LoginDTO } from "../../types/DTO/login-dto";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";
import { RegisterDTO } from "../../types/DTO/register-dto";
import { Content } from "../../types/DTO/content";
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

    getMostPopularContents():Observable<Content[]>{
        return this.httpClient.get<Content[]>(this.urlBase+"contents/most-popular");
    }

    getContent(data:ContentRequestDTO):Observable<Content>{ 
        console.log(data);
        return this.httpClient.post<Content>(this.urlBase + "contents", data);
    }
}

