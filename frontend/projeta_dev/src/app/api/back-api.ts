
import { inject, Inject, Injectable } from "@angular/core";
import { LoginDTO } from "../../types/DTO/login-dto";
import { Observable } from "rxjs";
import { HttpClient } from "@angular/common/http";

@Injectable({
    providedIn:'root',
})
export class BackApi {
    
    private urlBase = "http://localhost:8080/";

    private httpClient = inject(HttpClient);

    

}

