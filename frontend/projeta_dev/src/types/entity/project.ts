import { User } from "./user";

export interface Project {
  id: number; 
  title: string;
  imgUrl: string;
  description: string;
  stack: string;
  status: string;
  inputType: string;
  repositoryLink: string;
  createdAt: string; 
  users:User[];
  positions:string[];
}