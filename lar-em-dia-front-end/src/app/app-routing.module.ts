import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home/home.component';
import { LoginProfissionalComponent } from './components/login-profissional/login-profissional/login-profissional.component';
import { AuthGuard } from './auth.guard'
import { GerenciarLoginProfissionalComponent } from './components/login-profissional/gerenciar-login-profissional/gerenciar-login-profissional.component';
import { GerenciarLoginClienteComponent } from './components/login-cliente/gerenciar-login-cliente/gerenciar-login-cliente.component';
import { LoginClienteComponent } from './components/login-cliente/login-cliente/login-cliente.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'loginprofissional', component: LoginProfissionalComponent },
  { path: 'gerenciarprofissional', component: GerenciarLoginProfissionalComponent },
  { path: 'logincliente', component: LoginClienteComponent },
  { path: 'gerenciarcliente', component: GerenciarLoginClienteComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
