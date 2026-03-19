import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { Login } from './components/auth/login/login';
import { Register } from './components/auth/register/register';
import { Dashboard } from './components/dashboard/dashboard';
import { ShowContent } from './components/contents/show-content/show-content';
import { CreateProject } from './components/projects/create-project/create-project';
import { ShowProject } from './components/projects/show-project/show-project';
import { ShowUser } from './components/users/show-user/show-user';
import { ShowProjectsOfUser } from './components/projects/show-projects-of-user/show-projects-of-user';
import { RequestsProject } from './components/projects/requests-project/requests-project';
import { MyNotifications } from './components/users/my-notifications/my-notifications';
import { UserFollow } from './components/users/user-follow/user-follow';

export const routes: Routes = [
    {path:"", component:Dashboard},
    {path:"dashboard/:page", component:Dashboard},
    {path:"dashboard", component:Dashboard},
    {path:"home", component:Home},
    {path:"login", component:Login},
    {path:"register", component:Register},
    {path:"notifications", component:MyNotifications},
    {path:"contents/:owner/:slug", component:ShowContent},
    {path:"projects/create", component:CreateProject},
    {path:":username", component:ShowUser},
    {path:":username/followers",component:UserFollow},
    {path:":username/following",component:UserFollow},
    {path:":username/projects", component:ShowProjectsOfUser},
    {path:":username/projects/:slug", component:ShowProject},
    {path:":username/projects/:slug/requests", component:RequestsProject},

];
