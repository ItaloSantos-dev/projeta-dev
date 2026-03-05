import { UserExperienceLevel } from "../enums/user-experience-level";

export interface User {
  name: string;
  email: string;
  experienceLevel: UserExperienceLevel;
  telephoneNumber: string;
  principalStack: string;
}