import { User } from "./user";

export interface Project {
  id: number; 
  title: string;
  slug:string;
  imgUrl: string;
  description: string;
  stack: string;
  status: string;
  inputType: string;
  repositoryLink: string;
  createdAt: string; 
  contirbutors:User[];
  positions:string[];
}