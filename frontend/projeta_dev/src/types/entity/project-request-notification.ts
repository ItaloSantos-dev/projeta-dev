import { TypeProjectRequestNotification } from "../enums/type-project-request-notification";
import { ProjectRequest } from "./project-request";

export interface ProjectRequestNotification{
    id: number;
    projectRequest: ProjectRequest;
    usernameSender: string;
    usernameReceiver: string;
    read: boolean;
    type: TypeProjectRequestNotification;
    createdAt: string;
}