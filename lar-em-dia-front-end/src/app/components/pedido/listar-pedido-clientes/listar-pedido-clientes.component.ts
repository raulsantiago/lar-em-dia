import { Component, OnInit, ViewChild } from '@angular/core';
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
import { Table } from 'primeng/table/table';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-listar-pedido-clientes',
  templateUrl: './listar-pedido-clientes.component.html',
  styleUrls: ['./listar-pedido-clientes.component.css']
})
export class ListarPedidoClientesComponent implements OnInit {

  @ViewChild('table')
  table: Table;

  constructor(
    private pedidoService:                  PedidoService,
    private loginClienteService:            LoginClienteService,
    private agendaService:                  AgendaService,
    private authService:                    AuthService,    
    private router:                         Router,
    private route:                          ActivatedRoute,
    private messageService:                 MessageService    
  ) { }
  
  tipoServicoProfissionalDTO: TipoServicoProfissionalDTO;
  pedidoContratadoDTO:        PedidoContratadoDTO;

  listaPedidosClienteDTO: ListaPedidosClienteDTO[]; 
  gerenciarClienteDTO:    GerenciarClienteDTO;

  idPedido: number;
  idAgenda: number;
  
  emailLogado:    string;
  exibirDetalhar: boolean = false;

  mensagemSucesso: string;  
  errors:          String[];

  ngOnInit(): void {
    this.emailLogado = this.authService.getUsuarioAutenticado();
    this.loginClienteService.consultarEmail(this.emailLogado).subscribe( dado => {
      this.gerenciarClienteDTO = dado;
        this.pedidoService.pedidosPorIdCliente(this.gerenciarClienteDTO?.idCliente).subscribe(dado => {
          this.listaPedidosClienteDTO = dado;
          this.listaPedidosClienteDTO.map((lista: ListaPedidosClienteDTO) => this.mapearValorGrid(lista));
        });
    });

  }

  private mapearValorGrid(lista: ListaPedidosClienteDTO): ListaPedidosClienteDTO {  
    let valorMoedaNumber = parseFloat(lista.precoContratado.toString());
    lista.precoContratadoFmt = valorMoedaNumber.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
    return lista;
  }

  showConfirm(idPedido: number, idAgenda: number){
    this.idAgenda = idAgenda;
    this.idPedido = idPedido;
    console.log(this.idPedido);
    this.messageService.clear();
    this.messageService.add({key: 'ex', sticky: true, severity:'warn', summary:'Deseja realmente excluir o serviço já agendado?', detail:'Confirme para cancelar'});
  }

  onConfirm() {
    this.messageService.clear('ex');
    this.pedidoService.excluir(this.idPedido)
    .subscribe( response => {
      let agenda: AlterarAgendaDTO = new AlterarAgendaDTO();
      agenda.disponivel = true;
      this.agendaService.alterar(this.idAgenda, agenda)
        .subscribe( response => {
          this.messageService.add({severity:'success', summary: 'Sucesso', detail: 'Exclusão realizada!' , life: 2000 });
          setTimeout( res => { this.ngOnInit(); }, 2100);
        }, errorResponse => {            
            this.errors = errorResponse.error.errors;            
            this.errors.forEach(response => {
              this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 2000 });
            });
            setTimeout(() => {
              this.exibirDetalhar = true;
            }, 2100);
        });  
    }, errorResponse => {
        this.errors = errorResponse.error.errors;        
        this.errors.forEach(response => {
          this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 2000 });
        });
        setTimeout(() => {
          this.exibirDetalhar = true;
        }, 2100);
    });
  }

  onReject() {
      this.messageService.clear('ex');
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
