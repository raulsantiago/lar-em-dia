// Angular
import { DEFAULT_CURRENCY_CODE, LOCALE_ID, NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { registerLocaleData, DatePipe, CurrencyPipe, CommonModule } from  '@angular/common';
import { HttpClient, HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TranslateLoader, TranslateModule, TranslateService } from '@ngx-translate/core';
import { TranslateHttpLoader  } from '@ngx-translate/http-loader';
import localePt from '@angular/common/locales/pt';
registerLocaleData(localePt);

// Boostrap
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';

// Primeng
import { NgxMaskModule } from 'ngx-mask';
import { InputTextModule } from 'primeng/inputtext';
import { ButtonModule } from 'primeng/button';
import { CheckboxModule } from 'primeng/checkbox';
import { RadioButtonModule } from 'primeng/radiobutton';
import { RippleModule } from 'primeng/ripple';
import { CalendarModule } from 'primeng/calendar';
import { DataViewModule } from 'primeng/dataview';
import { PaginatorModule } from 'primeng/paginator';
import { DropdownModule } from 'primeng/dropdown';
import { DialogModule } from 'primeng/dialog';
import { TooltipModule } from 'primeng/tooltip';
import { MenubarModule } from 'primeng/menubar';
import { ConfirmDialogModule } from 'primeng/confirmdialog';
import { ToastModule } from 'primeng/toast';
import { ConfirmationService, MessageService } from 'primeng/api';
import { FileUploadModule } from 'primeng/fileupload';
import { InputMaskModule } from 'primeng/inputmask';
import { TreeTableModule } from 'primeng/treetable';
import { NgxCurrencyModule } from "ngx-currency";
import { InputNumberModule } from 'primeng/inputnumber';
import { PickListModule } from 'primeng/picklist';
import { TableModule, Table, TableService } from 'primeng/table';


// Componentes
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginProfissionalComponent } from './components/login-profissional/login-profissional/login-profissional.component';
import { LoginProfissionalService } from './services/login-profissional.service';
import { AuthService } from './services/auth.service';
import { HomeComponent } from './components/home/home/home.component';
import { TokenInterceptor } from './token.interceptor';
import { GerenciarLoginProfissionalComponent } from './components/login-profissional/gerenciar-login-profissional/gerenciar-login-profissional.component';
import { LoginClienteComponent } from './components/login-cliente/login-cliente/login-cliente.component';
import { GerenciarLoginClienteComponent } from './components/login-cliente/gerenciar-login-cliente/gerenciar-login-cliente.component';
import { LoginClienteService } from './services/login-cliente.service';
import { IncluirServicoComponent } from './components/servico-profissional/incluir-servico/incluir-servico.component';
import { ListarServicoComponent } from './components/servico-profissional/listar-servico/listar-servico.component';
import { AlterarServicoComponent } from './components/servico-profissional/alterar-servico/alterar-servico.component';
import { IncluirTipoServicoComponent } from './components/servico-profissional/incluir-tipo-servico/incluir-tipo-servico.component';
import { GerenciarMunicipioComponent } from './components/regiao/gerenciar-municipio/gerenciar-municipio.component';
import { ConfigurarMunicipioEstadoComponent } from './components/regiao/configurar-municipio-estado/configurar-municipio-estado.component';
import { AgendaComponent } from './components/agenda/agenda/agenda.component';
import { ListarTipoServicoComponent } from './components/pedido/listar-tipo-servico/listar-tipo-servico.component';
import { ListarServicoSolicitacaoComponent } from './components/pedido/listar-servico-solicitacao/listar-servico-solicitacao.component';
import { AgendarServicoComponent } from './components/pedido/agendar-servico/agendar-servico.component';
import { ListarPedidoClientesComponent } from './components/pedido/listar-pedido-clientes/listar-pedido-clientes.component';
import { ModalContatoComponent } from './components/pedido/listar-pedido-clientes/modal-contato/modal-contato.component';
import { ListarPedidoProfissionalComponent } from './components/pedido/listar-pedido-profissional/listar-pedido-profissional.component';
import { ModalFecharPedidoComponent } from './components/pedido/listar-pedido-profissional/modal-fechar-pedido/modal-fechar-pedido.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginProfissionalComponent,
    HomeComponent,
    GerenciarLoginProfissionalComponent,
    LoginClienteComponent,
    GerenciarLoginClienteComponent,
    IncluirServicoComponent,
    ListarServicoComponent,
    AlterarServicoComponent,
    IncluirTipoServicoComponent,
    GerenciarMunicipioComponent,
    ConfigurarMunicipioEstadoComponent,
    AgendaComponent,
    ListarTipoServicoComponent,
    ListarServicoSolicitacaoComponent,
    AgendarServicoComponent,
    ListarPedidoClientesComponent,
    ModalContatoComponent,
    ListarPedidoProfissionalComponent,
    ModalFecharPedidoComponent
  ],
  imports: [    
    MenubarModule,
    ConfirmDialogModule,
    ToastModule,    
    InputMaskModule,
    InputNumberModule,        
    InputNumberModule,
    PickListModule,
    TableModule,
    NgxCurrencyModule,    
    TreeTableModule,
    FileUploadModule,    
    TooltipModule,
    DialogModule,
    NgxMaskModule.forRoot(),
    CommonModule,
    HttpClientModule,
    DataViewModule,
    DropdownModule,
    PaginatorModule,
    CalendarModule,
    BrowserModule,    
    NgbModule,
    ReactiveFormsModule,
    FormsModule,
    BrowserAnimationsModule,        
    InputTextModule,
		CheckboxModule,
		ButtonModule,
		RadioButtonModule,
    RippleModule,
    AppRoutingModule,
    TranslateModule.forRoot(
      {
        loader: {
          provide: TranslateLoader,
          useFactory: (http: HttpClient) => { return new TranslateHttpLoader(http, './assets/i18n/', '.json');},
          deps: [HttpClient]
        }
      }
    )
  ],
  providers: [
    ConfirmationService,
    MessageService,
    TableService,    
    Table,
    HttpClient,
    DatePipe,
    CurrencyPipe,
    LoginProfissionalService,
    LoginClienteService,
    TranslateService,
    AuthService,    
    {
      provide: LOCALE_ID,
      useValue: 'pt-BR'
    },
    {
      provide:  DEFAULT_CURRENCY_CODE,
      useValue: 'BRL'
    },
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
