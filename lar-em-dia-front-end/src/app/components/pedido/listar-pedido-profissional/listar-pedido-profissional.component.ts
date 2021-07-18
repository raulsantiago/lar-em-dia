import { DatePipe } from '@angular/common';
import { Component, OnInit, ViewChild }  from '@angular/core';
import { GerenciarClienteDTO } from 'src/app/dto/login-cliente/gerenciar-clienteDTO';
import { AlterarPedidoContratadoDTO } from 'src/app/dto/pedido/alterar-pedido-contratadoDTO';
import { ListaPedidosProfissionalDTO } from 'src/app/dto/pedido/lista-pedidos-profissionalDTO';
import { PedidoContratadoDTO } from 'src/app/dto/pedido/pedido-contratadoDTO';
import { LoginClienteService } from 'src/app/services/login-cliente.service';
import { PedidoService } from 'src/app/services/pedido.service';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table/table';

@Component({
  selector: 'app-listar-pedido-profissional',
  templateUrl: './listar-pedido-profissional.component.html',
  styleUrls: ['./listar-pedido-profissional.component.css']
})
export class ListarPedidoProfissionalComponent implements OnInit {

  @ViewChild('table')
  table: Table;

  constructor(
    private pedidoService:        PedidoService,
    private loginClienteService:  LoginClienteService,
    public  datepipe:             DatePipe,
    private messageService:       MessageService
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
  id:                         number;

  ngOnInit(): void {
    this.pedidoService.listaPedidosViewProfissional().subscribe( dado => {
      this.listaPedidosProfissionalDTO = dado;
      this.listaPedidosProfissionalDTO.map((lista: ListaPedidosProfissionalDTO) => this.mapearValorGrid(lista));
      console.log(this.listaPedidosProfissionalDTO);
    });
  }

  private mapearValorGrid(lista: ListaPedidosProfissionalDTO): ListaPedidosProfissionalDTO {  
    let valorMoedaNumberPreco   = parseFloat(lista.precoContratado.toString());
    let valorMoedaNumberDespesa = lista.despesas === null || lista.despesas === undefined ? null : parseFloat(lista.despesas.toString());
    lista.precoContratadoFmt    = valorMoedaNumberPreco.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
    lista.despesasFmt           = valorMoedaNumberDespesa === null || valorMoedaNumberDespesa === undefined ? null : valorMoedaNumberDespesa.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
    let calculo                 = lista.precoContratado - lista.despesas;
    let valorMoedaNumberLucro   = parseFloat(calculo.toString());
    lista.lucroFmt              = lista.despesas === null || lista.despesas === undefined ? null : valorMoedaNumberLucro.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
    return lista;
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
    let pedido: AlterarPedidoContratadoDTO = new AlterarPedidoContratadoDTO();
    pedido.dataHoraInicio = this.datepipe.transform(this.dataInicial, 'yyyy-MM-ddTHH:mm:ss') == undefined || this.datepipe.transform(this.dataInicial, 'yyyy-MM-ddTHH:mm:ss') == null ? '' : this.datepipe.transform(this.dataInicial, 'yyyy-MM-ddTHH:mm:ss');
    pedido.dataHoraFim = this.datepipe.transform(this.dataFinal, 'yyyy-MM-ddTHH:mm:ss') == undefined || this.datepipe.transform(this.dataFinal, 'yyyy-MM-ddTHH:mm:ss') == null ? '' : this.datepipe.transform(this.dataFinal, 'yyyy-MM-ddTHH:mm:ss');
    pedido.despesas = this.despesas == undefined || this.despesas == null ? null : this.despesas;
    pedido.situacao = false;
    this.pedidoService.alterar(id, pedido).subscribe( respose => { 
      this.messageService.add({severity:'success', summary: 'Sucesso', detail: 'Registro realizado.' , life: 2000 });
      setTimeout( res => { this.ngOnInit() }, 2100);
    }, errorResponse => {      
      this.errors2 = errorResponse.error.errors;
      this.errors2.forEach(response => {
        this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 5000 });
      });      
    });  
  }

  showConfirm(id: number){    
    this.id = id;    
    this.messageService.clear();
    this.messageService.add({key: 'ex', sticky: true, severity:'warn', summary:'Deseja realmente excluir o serviço?', detail:'Confirme para excluir'});
  }

  onConfirm() {
    this.messageService.clear('ex');
    this.pedidoService.excluirProf(this.id).subscribe( respose => {
      this.messageService.add({severity:'success', summary: 'Sucesso', detail: 'Exclusão realizada.' , life: 2000 });
      setTimeout( res => { this.ngOnInit() }, 2100);      
    }, errorResponse => {      
      this.errors = errorResponse.error.errors;
      this.errors.forEach(response => {
        this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 5000 });
      });
    });
  }
  
  onReject() {
    this.messageService.clear('ex');
  }

}
