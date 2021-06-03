import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlterarAgendaDTO } from 'src/app/dto/agenda/alterar-agendaDTO';
import { GerenciarClienteDTO } from 'src/app/dto/login-cliente/gerenciar-clienteDTO';
import { ListaPedidosClienteDTO } from 'src/app/dto/pedido/lista-pedidos-clienteDTO';
import { PedidoContratadoDTO } from 'src/app/dto/pedido/pedido-contratadoDTO';
import { TipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/tipo-servico-profissionalDTO';
import { AgendaService } from 'src/app/services/agenda.service';
import { AuthService } from 'src/app/services/auth.service';
import { LoginClienteService } from 'src/app/services/login-cliente.service';
import { PedidoService } from 'src/app/services/pedido.service';

@Component({
  selector: 'app-listar-pedido-clientes',
  templateUrl: './listar-pedido-clientes.component.html',
  styleUrls: ['./listar-pedido-clientes.component.css']
})
export class ListarPedidoClientesComponent implements OnInit {

  constructor(
    private pedidoService:                  PedidoService,
    private loginClienteService:            LoginClienteService,
    private agendaService:                  AgendaService,
    private authService:                    AuthService,    
    private router:                         Router,
    private route:                          ActivatedRoute
  ) { }
  
  tipoServicoProfissionalDTO: TipoServicoProfissionalDTO;
  pedidoContratadoDTO:              PedidoContratadoDTO;

  listaPedidosClienteDTO: ListaPedidosClienteDTO[]; 
  gerenciarClienteDTO:    GerenciarClienteDTO;
  
  emailLogado: string;
  exibirDetalhar: boolean = false;

  mensagemSucesso: string;  
  errors:          String[];

  ngOnInit(): void {
    this.emailLogado = this.authService.getUsuarioAutenticado();
    this.loginClienteService.consultarEmail(this.emailLogado).subscribe( dado => {
      this.gerenciarClienteDTO = dado;
        this.pedidoService.pedidosPorIdCliente(this.gerenciarClienteDTO?.idCliente).subscribe(dado => {
          this.listaPedidosClienteDTO = dado;          
        });
    });

  }

  cancelar(idPedido: number, idAgenda: number){    
    this.pedidoService.excluir(idPedido)
      .subscribe( response => {
        let agenda: AlterarAgendaDTO = new AlterarAgendaDTO();
        agenda.disponivel = true;
        this.agendaService.alterar(idAgenda, agenda)
          .subscribe( response => {
            this.mensagemSucesso = 'Exclusão realizada com sucesso!';
            setTimeout( res => { this.mensagemSucesso = ''; }, 3000);
            this.errors = null;
            setTimeout( res => { 
                this.ngOnInit();
            }, 3500);
          }, errorResponse => {
            this.mensagemSucesso = null;
            this.errors = errorResponse.error.errors;
            setTimeout( res => { this.errors = null; this.exibirDetalhar = true; }, 5000);
          });  
      }, errorResponse => {
        this.mensagemSucesso = null;
        this.errors = errorResponse.error.errors;
        setTimeout( res => { this.errors = null; this.exibirDetalhar = true; }, 5000);
      });
      
  }

  voltar() {
    this.router.navigate(['gerenciarcliente/'], {
      relativeTo: this.route['gerenciarcliente/'],
    });
  }


  exibirPopup(nome: string): void {
    this.exibirDetalhar = nome === 'detalhar' ? true : false;
  }



}
