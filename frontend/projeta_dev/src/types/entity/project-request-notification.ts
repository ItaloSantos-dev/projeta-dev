import { TypeProjectRequestNotification } from "../enums/type-project-request-notification";
import { ProjectRequest } from "./project-request";

export interface ProjectRequestNotification{
    id: number;
    projectTitle:string,
    projectSlug:string,
    usernameSender: string;
    usernameReceiver: string;
    projectRequest: ProjectRequest;
    read: boolean;
    type: TypeProjectRequestNotification;
    createdAt: string;
    readAt: string;
}