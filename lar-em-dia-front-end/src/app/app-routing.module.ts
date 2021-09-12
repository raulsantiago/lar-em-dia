import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HomeComponent } from './components/home/home/home.component';
import { LoginProfissionalComponent } from './components/login-profissional/login-profissional/login-profissional.component';
import { GerenciarLoginProfissionalComponent } from './components/login-profissional/gerenciar-login-profissional/gerenciar-login-profissional.component';
import { GerenciarLoginClienteComponent } from './components/login-cliente/gerenciar-login-cliente/gerenciar-login-cliente.component';
import { LoginClienteComponent } from './components/login-cliente/login-cliente/login-cliente.component';
import { IncluirServicoComponent } from './components/servico-profissional/incluir-servico/incluir-servico.component';
import { ListarServicoComponent } from './components/servico-profissional/listar-servico/listar-servico.component';
import { AlterarServicoComponent } from './components/servico-profissional/alterar-servico/alterar-servico.component';
import { IncluirTipoServicoComponent } from './components/servico-profissional/incluir-tipo-servico/incluir-tipo-servico.component';
import { GerenciarMunicipioComponent } from './components/regiao/gerenciar-municipio/gerenciar-municipio.component';
import { ConfigurarMunicipioEstadoComponent } from './components/regiao/configurar-municipio-estado/configurar-municipio-estado.component';
import { AgendaComponent } from './components/agenda/agenda/agenda.component';
import { ListarServicoSolicitacaoComponent } from './components/pedido/listar-servico-solicitacao/listar-servico-solicitacao.component';
import { ListarTipoServicoComponent } from './components/pedido/listar-tipo-servico/listar-tipo-servico.component';
import { AgendarServicoComponent } from './components/pedido/agendar-servico/agendar-servico.component';
import { ListarPedidoClientesComponent } from './components/pedido/listar-pedido-clientes/listar-pedido-clientes.component';
import { ListarPedidoProfissionalComponent } from './components/pedido/listar-pedido-profissional/listar-pedido-profissional.component';
import { LayoutComponent } from './components/layout/layout.component';
import { SinteticoComponent } from './components/relatorios/sintetico/sintetico.component';

const routes: Routes = [
  { path: 'loginprofissional', component: LoginProfissionalComponent },
  { path: 'logincliente', component: LoginClienteComponent },


  

  { path: '', component: LayoutComponent, children: [
    { path: '' , redirectTo: '/home', pathMatch: 'full' },
    { path: 'home', component: HomeComponent },
    { path: 'gerenciarprofissional', component: GerenciarLoginProfissionalComponent },    
    { path: 'gerenciarcliente', component: GerenciarLoginClienteComponent },
    { path: 'cadastrarservico', component: IncluirServicoComponent },
    { path: 'listarservico', component: ListarServicoComponent },
    { path: 'alterarservico/:id', component: AlterarServicoComponent },
    { path: 'cadastrartiposervico/:id', component: IncluirTipoServicoComponent },
    { path: 'regiao', component: GerenciarMunicipioComponent },
    { path: 'regiao/:id', component: ConfigurarMunicipioEstadoComponent },
    { path: 'agenda', component: AgendaComponent },
    { path: 'solicitar', component: ListarServicoSolicitacaoComponent },
    { path: 'solicitar/:id', component: ListarTipoServicoComponent },
    { path: 'agendar/:id', component: AgendarServicoComponent },
    { path: 'listarpedidoscliente', component: ListarPedidoClientesComponent },
    { path: 'listarpedidosprofissional', component: ListarPedidoProfissionalComponent },
    { path: 'relatorios', component: SinteticoComponent }

  ]}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
