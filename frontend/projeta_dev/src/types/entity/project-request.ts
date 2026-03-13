import { ProjectStatus } from "../enums/project-status";
import { Project } from "./project";

export interface ProjectRequest{
    id: number;
    project: Project;
    status: ProjectStatus;
    created_at: string; 
}