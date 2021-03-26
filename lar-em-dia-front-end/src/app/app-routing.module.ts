import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home/home.component';
import { LoginProfissionalComponent } from './components/login-profissional/login-profissional/login-profissional.component';
import { AuthGuard } from './auth.guard'

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'loginprofissional', component: LoginProfissionalComponent }  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
