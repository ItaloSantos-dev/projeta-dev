import { Project } from "../entity/project";

export interface MyProjectsDTO{
    projectsICreated: Project[],
    projectsIParticipe: Project[]
}