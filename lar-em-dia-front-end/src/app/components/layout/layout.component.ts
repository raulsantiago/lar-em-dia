import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { MenuItem } from 'primeng/api';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-layout',
  templateUrl: './layout.component.html',
  styleUrls: ['./layout.component.css']
})
export class LayoutComponent implements OnInit {

  constructor(
    private authService: AuthService,
    private router: Router    
  ) { }

  items: MenuItem[];  
  activeItem: MenuItem;
  usuarioLogado: string;
  perfilUsuario: string;

  ngOnInit() {
    this.usuarioLogado = this.authService.getUsuarioAutenticado();
    this.perfilUsuario = this.authService.getUsuarioPerfil();
    this.items = [
        { label: 'Home', icon: 'pi pi-fw pi-home', routerLink: ['/home']  },
        this.perfilUsuario === 'ROLE_USER' ? {label: 'Serviços', icon: 'fa fa-briefcase', routerLink: ['/solicitar'] } : {label: ''},
        this.perfilUsuario === 'ROLE_USER' ? {label: 'Meus Pedidos', icon: 'fa fa-check', routerLink: ['/listarpedidoscliente'] } : {label: ''},
        this.perfilUsuario === 'ROLE_ADMIN' ? {label: 'Região', icon: 'pi pi-fw pi-map', routerLink: ['/regiao']} : {label: ''},
        this.perfilUsuario === 'ROLE_ADMIN' ? {label: 'Serviços', icon: 'fa fa-plug', routerLink: ['/listarservico']} : {label: ''},
        this.perfilUsuario === 'ROLE_ADMIN' ? {label: 'Agenda', icon: 'pi pi-fw pi-calendar', routerLink: ['/agenda']} : {label: ''},
        this.perfilUsuario === 'ROLE_ADMIN' ? {label: 'Contratos', icon: 'fa fa-compress', routerLink: ['/listarpedidosprofissional']} : {label: ''},
        this.perfilUsuario === 'ROLE_ADMIN' ? {label: 'Relatórios', icon: 'fa fa-bar-chart', routerLink: ['/relatorios']} : {label: ''}
    ];
    this.activeItem = this.items[0];
  }

  logout(){
    this.authService.encerrarSessao();
    this.router.navigate(['/home']);
    this.usuarioLogado = null;
    this.ngOnInit();
  }

  usuario(){
    if(this.perfilUsuario === 'ROLE_ADMIN'){
      this.router.navigate(['/gerenciarprofissional']);
    } else {
      this.router.navigate(['/gerenciarcliente']);
    }
  }

}
