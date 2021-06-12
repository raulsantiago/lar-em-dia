import { DatePipe } from '@angular/common';
import { Component, OnInit } from '@angular/core';
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
    private pedidoService:                  PedidoService,    
    private router:                         Router,
    private route:                          ActivatedRoute,
    private loginClienteService:            LoginClienteService,
    public datepipe:       DatePipe
  ) { }

  listaPedidosProfissionalDTO: ListaPedidosProfissionalDTO[];
  pedidoContratado: PedidoContratadoDTO;
  gerenciarClienteDTO: GerenciarClienteDTO;

  exibirDetalhar: boolean = false;
  displayBasic2: boolean;

  mensagemSucesso: string;  
  mensagemSucesso2: string;  
  errors:          String[];
  errors2:          String[];

  alterarPedidoContratadoDTO: AlterarPedidoContratadoDTO;

  dataInicial:      Date;
  dataFinal:         Date;
  despesas:         number;
  //idPedido: number;


  ngOnInit(): void {
    this.pedidoService.listaPedidosViewProfissional().subscribe( dado => {
      this.listaPedidosProfissionalDTO = dado;
    });
  }

  showBasicDialog2() {
    this.displayBasic2 = true;
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
    console.log(id);
    console.log(this.dataFinal);
    console.log(this.dataInicial);
    console.log(this.despesas);
    console.log(this.datepipe.transform(this.dataFinal, 'yyyy-dd-MMTHH:mm:ss'));
    //console.log(this.datepipe.transform(this.dataFinal, 'MM/dd/yyyy HH:mm'));
    let pedido: AlterarPedidoContratadoDTO = new AlterarPedidoContratadoDTO();
    pedido.dataHoraFim = this.datepipe.transform(this.dataFinal, 'yyyy-dd-MMTHH:mm:ss');
    pedido.dataHoraInicio = this.datepipe.transform(this.dataInicial, 'yyyy-dd-MMTHH:mm:ss');
    pedido.despesas = this.despesas == undefined || this.despesas == null ? null : this.despesas;
    pedido.situacao = false;

    // this.alterarPedidoContratadoDTO.dataHoraFim =  this.datepipe.transform(this.dataFinal, 'dd/MM/yyyy HH:mm:ss');
    // this.alterarPedidoContratadoDTO.dataHoraInicio = this.datepipe.transform(this.dataInicial, 'dd/MM/yyyy HH:mm:ss');
    // this.alterarPedidoContratadoDTO.situacao = false;
    // this.alterarPedidoContratadoDTO.despesas = this.despesas;
    // console.log(this.alterarPedidoContratadoDTO);
    console.log(pedido);
    this.pedidoService.alterar(id, pedido).subscribe( respose => {      
      this.mensagemSucesso2 = 'Cadastro realizado com sucesso!';
      setTimeout( res => { this.mensagemSucesso2 = ''; }, 3000);
      this.errors2 = null;      
    }, errorResponse => {
      this.mensagemSucesso2 = null;
      this.errors2 = errorResponse.error.errors;
      setTimeout( res => { this.errors2 = null; }, 5000);
    });  
  }




}
