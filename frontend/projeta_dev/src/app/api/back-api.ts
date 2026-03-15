
import { inject, Inject, Injectable } from "@angular/core";
import { LoginDTO } from "../../types/DTO/login-dto";
import { Observable } from "rxjs";
import { HttpClient, HttpParams } from "@angular/common/http";
import { RegisterDTO } from "../../types/DTO/register-dto";
import { Content } from "../../types/entity/content";
import { ContentRequestDTO } from "../../types/DTO/content-request-dto";
import { CreateProjectDTO } from "../../types/DTO/create-project-dto";
import { MyProjectsDTO } from "../../types/DTO/my-projetcts-DTO";
import { Project } from "../../types/entity/project";
import { User } from "../../types/entity/user";
import { CreateHabilityDTO } from "../../types/DTO/create-hability-dto";
import { Hability } from "../../types/entity/hability";
import { ProjectRequest } from "../../types/entity/project-request";
import { ProjectRequestNotification } from "../../types/entity/project-request-notification";
import { PositionSimplified } from "../../types/entity/position-simplified";
import { UpdateProjectRequestRequestDTO } from "../../types/DTO/update-project-request-request-dto";

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

    createProject(data:CreateProjectDTO):Observable<any>{
        return this.httpClient.post(this.urlBase+"projects", data);
    }

    getMyProjects():Observable<MyProjectsDTO>{
        return this.httpClient.get<MyProjectsDTO>(this.urlBase+"projects");
    }

    getUserByUsername(username:string):Observable<User>{
        return this.httpClient.get<User>(this.urlBase + "users/" + username);
    }

    getProjectOfUserByUsernameAndSlug(username:string,slug:string):Observable<Project>{
        const urlfinal = this.urlBase + "users/" + username + "/" + slug;
        return this.httpClient.get<Project>(urlfinal);
    }

    createHability(data:CreateHabilityDTO):Observable<Hability>{
        return this.httpClient.post<Hability>(this.urlBase + "habilitys", data);
    }

    deleteHability(id:number){
        return this.httpClient.delete(this.urlBase + "habilitys/"+ id);
    }

    createProjectRequest(projectId:number):Observable<ProjectRequest>{
        return this.httpClient.post<ProjectRequest>(this.urlBase +"projects/" + projectId + "/requests", null);
    }

    getNotificationOfProjectBySlug(slug:string):Observable<ProjectRequestNotification[]>{
        return this.httpClient.get<ProjectRequestNotification[]>(this.urlBase + "projects/" + slug + "/requests");
    }
    getPositionsOfProjectBySlug(slug:string):Observable<PositionSimplified[]>{
        return this.httpClient.get<PositionSimplified[]>(this.urlBase +"projects/"+ slug + "/positions");
    }

    updateNotificationAndRequest(notificationId:number,data:UpdateProjectRequestRequestDTO):Observable<void>{
        return this.httpClient.put<void>(this.urlBase + "projects/requests/" + notificationId, data);
    }
}

