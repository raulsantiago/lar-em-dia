// Angular
import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { DatePipe, CurrencyPipe, CommonModule } from  '@angular/common';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';

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

@NgModule({
  declarations: [
    AppComponent,
    LoginProfissionalComponent,
    HomeComponent,
    GerenciarLoginProfissionalComponent,
    LoginClienteComponent,
    GerenciarLoginClienteComponent
  ],
  imports: [
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
    NgxMaskModule,
    InputTextModule,
		CheckboxModule,
		ButtonModule,
		RadioButtonModule,
    RippleModule,
    AppRoutingModule
  ],
  providers: [
    DatePipe,
    CurrencyPipe,
    LoginProfissionalService,
    LoginClienteService,
    AuthService,
    {
      provide: HTTP_INTERCEPTORS,
      useClass: TokenInterceptor,
      multi: true
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
