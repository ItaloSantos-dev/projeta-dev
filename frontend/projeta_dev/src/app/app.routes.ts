import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { Login } from './components/auth/login/login';
import { Register } from './components/auth/register/register';
import { Dashboard } from './components/dashboard/dashboard';
import { ShowContent } from './components/contents/show-content/show-content';
import { CreateProject } from './components/projects/create-project/create-project';
import { ShowMyProjects } from './components/projects/show-my-projects/show-my-projects';
import { ShowProject } from './components/projects/show-project/show-project';

export const routes: Routes = [
    {path:"", component:Dashboard},
    {path:"dashboard/:page", component:Dashboard},
    {path:"home", component:Home},
    {path:"login", component:Login},
    {path:"register", component:Register},
    {path:"contents/:owner/:slug", component:ShowContent},
    {path:"projects/create", component:CreateProject},
    
    {path:"projects/my-projects", component:ShowMyProjects},
    {path:"projects/my-projects/:id", component:ShowProject}
];
