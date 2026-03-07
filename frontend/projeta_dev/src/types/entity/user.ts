import { UserExperienceLevel } from "../enums/user-experience-level";
import { Project } from "./project";

export interface User {
  name: string;
  username: string;
  experienceLevel: UserExperienceLevel;
  principalStack: string;
  myProjects:Project[]
}