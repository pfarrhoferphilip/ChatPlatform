import { Routes } from '@angular/router';
import {ChatComponent} from './chat/chat.component';
import {LoginComponent} from './login/login.component';

export const routes: Routes = [
  {
    title: "Chat",
    path: "",
    component: ChatComponent
  },
  {
    title: "Login",
    path: "login",
    component: LoginComponent
  }
];
