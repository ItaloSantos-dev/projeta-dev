import { User } from "./user";

export interface Project {
  id: number; 
  title: string;
  slug:string;
  imgUrl: string;
  description: string;
  stack: string;
  creator:string;
  status: string;
  inputType: string;
  repositoryLink: string;
  createdAt: string; 
  contibutors:string[];
  positions:string[];
}