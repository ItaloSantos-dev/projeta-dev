import { UserExperienceLevel } from "../enums/user-experience-level";
import { Project } from "./project";

export interface User {
  name: string;
  username: string;
  experienceLevel: UserExperienceLevel;
  principalStack: string;
  myProjects:Project[];
  about: string;
  coverUrl: string;
  perfilUrl: string;
  link1: string;
  link2: string;
  link3: string;
  link4: string;
  link5: string;
}