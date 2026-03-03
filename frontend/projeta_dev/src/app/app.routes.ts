import { Routes } from '@angular/router';
import { Home } from './components/home/home';
import { Login } from './components/auth/login/login';
import { Register } from './components/auth/register/register';
import { Dashboard } from './components/dashboard/dashboard';
import { ShowContent } from './components/contents/show-content/show-content';

export const routes: Routes = [
    {path:"", component:Home},
    {path:"login", component:Login},
    {path:"register", component:Register},
    {path:"dashboard", component:Dashboard},
    {path:"contents/show", component:ShowContent},
];
