import { DatePipe } from '@angular/common';
import { Component, OnInit, Pipe, PipeTransform }  from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GerenciarClienteDTO } from 'src/app/dto/login-cliente/gerenciar-clienteDTO';
import { AlterarPedidoContratadoDTO } from 'src/app/dto/pedido/alterar-pedido-contratadoDTO';
import { ListaPedidosProfissionalDTO } from 'src/app/dto/pedido/lista-pedidos-profissionalDTO';
import { PedidoContratadoDTO } from 'src/app/dto/pedido/pedido-contratadoDTO';
import { LoginClienteService } from 'src/app/services/login-cliente.service';
import { PedidoService } from 'src/app/services/pedido.service';

@Component({
  selector: 'app-listar-pedido-profissional',
  templateUrl: './listar-pedido-profissional.component.html',
  styleUrls: ['./listar-pedido-profissional.component.css']
})
export class ListarPedidoProfissionalComponent implements OnInit {

  constructor(
    private pedidoService:        PedidoService,    
    private router:               Router,
    private route:                ActivatedRoute,
    private loginClienteService:  LoginClienteService,
    public  datepipe:             DatePipe
  ) { }

  listaPedidosProfissionalDTO: ListaPedidosProfissionalDTO[];
  pedidoContratado:            PedidoContratadoDTO;
  gerenciarClienteDTO:         GerenciarClienteDTO;
  exibirDetalhar:              boolean = false;

  mensagemSucesso:  string;  
  mensagemSucesso2: string;  
  errors:           string[];
  errors2:          string[];

  alterarPedidoContratadoDTO: AlterarPedidoContratadoDTO;
  dataInicial:                Date;
  dataFinal:                  Date;
  despesas:                   number;

  ngOnInit(): void {
    this.pedidoService.listaPedidosViewProfissional().subscribe( dado => {
      this.listaPedidosProfissionalDTO = dado;
    });
  }

 
  endereco(id: number){
    this.loginClienteService.consultar(id).subscribe( dado => {
      this.gerenciarClienteDTO = dado;
    });    
  }

  exibirPopup(id: number, nome: string): void {
    this.pedidoService.consultar(id).subscribe( dado => {
      this.pedidoContratado = dado;      
      this.dataInicial = this.pedidoContratado?.dataHoraInicio == null || this.pedidoContratado?.dataHoraInicio == undefined ? null : new Date(this.pedidoContratado?.dataHoraInicio);
      this.dataFinal =   this.pedidoContratado?.dataHoraFim == null || this.pedidoContratado?.dataHoraFim == undefined ? null : new Date(this.pedidoContratado?.dataHoraFim);
      this.despesas = this.pedidoContratado?.despesas == null || this.pedidoContratado?.despesas == undefined ? null : this.pedidoContratado?.despesas;
      this.exibirDetalhar = nome === 'detalhar' ? true : false;
    });
  }

  salvar(id: number){    
    console.log(this.datepipe.transform(this.dataInicial, 'yyyy-MM-ddTHH:mm:ss'));
    let pedido: AlterarPedidoContratadoDTO = new AlterarPedidoContratadoDTO();
    pedido.dataHoraInicio = this.datepipe.transform(this.dataInicial, 'yyyy-MM-ddTHH:mm:ss') == undefined || this.datepipe.transform(this.dataInicial, 'yyyy-MM-ddTHH:mm:ss') == null ? null : this.datepipe.transform(this.dataInicial, 'yyyy-MM-ddTHH:mm:ss');
    pedido.dataHoraFim = this.datepipe.transform(this.dataFinal, 'yyyy-MM-ddTHH:mm:ss') == undefined || this.datepipe.transform(this.dataFinal, 'yyyy-MM-ddTHH:mm:ss') == null ? null : this.datepipe.transform(this.dataFinal, 'yyyy-MM-ddTHH:mm:ss');  
    pedido.despesas = this.despesas == undefined || this.despesas == null ? null : this.despesas;
    pedido.situacao = false;
    this.pedidoService.alterar(id, pedido).subscribe( respose => {      
      this.mensagemSucesso2 = 'Cadastro realizado com sucesso!';
      setTimeout( res => { this.mensagemSucesso2 = ''; this.ngOnInit() }, 5000);
      this.errors2 = null;      
    }, errorResponse => {
      this.mensagemSucesso2 = null;
      this.errors2 = errorResponse.error.errors;
      setTimeout( res => { this.errors2 = null; }, 5000);
    });  
  }

  excluir(id: number){
    this.pedidoService.excluirProf(id).subscribe( respose => {
      this.mensagemSucesso = 'Pedido cancelado com sucesso!';
      setTimeout( res => { this.mensagemSucesso = ''; this.ngOnInit() }, 3000);
      this.errors = null;      
    }, errorResponse => {
      this.mensagemSucesso = null;
      this.errors = errorResponse.error.errors;
      setTimeout( res => { this.errors = null; }, 5000);
    });  

  }
  

}
