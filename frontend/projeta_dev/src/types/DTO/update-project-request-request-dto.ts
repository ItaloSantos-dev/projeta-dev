import { ProjectStatus } from "../enums/project-status";

export interface UpdateProjectRequestRequestDTO{
    newStatus:ProjectStatus,
    positionId:number|null
}