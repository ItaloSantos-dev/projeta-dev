import { UserExperienceLevel } from "../enums/user-experience-level";

export interface RegisterDTO {
  name: string;
  username:string;
  email: string;
  password: string;
  telephoneNumber: string;
  experienceLevel: UserExperienceLevel;
  principalStack: string;
}