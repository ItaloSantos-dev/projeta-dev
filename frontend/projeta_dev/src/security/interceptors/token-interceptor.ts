import { HttpEvent, HttpHandlerFn, HttpRequest } from "@angular/common/http";
import { inject } from "@angular/core";
import { Observable } from "rxjs";
import { AuthService } from "../../app/service/auth-service";
import { Router } from "@angular/router";

export function TokenInterceptor(
    req: HttpRequest<unknown>,
    next: HttpHandlerFn

):Observable<HttpEvent<unknown>> {
    console.log("veio interceptor");
    
    const authService = inject(AuthService)

    const router = inject(Router);

    const token = authService.getToken();

    if (token==null)
        router.navigate(['/login']);

    
    const newRequest = req.clone({setHeaders:{"Authorization": "Bearer " + token}});

    return next(newRequest);
}