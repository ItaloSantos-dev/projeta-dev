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
  paid:boolean
  repositoryLink: string;
  createdAt: string;
  requestsCount:number;
  contibutors:string[];
  positions:string[];
  fixed:boolean;
  usersWithRequests:string[];
}