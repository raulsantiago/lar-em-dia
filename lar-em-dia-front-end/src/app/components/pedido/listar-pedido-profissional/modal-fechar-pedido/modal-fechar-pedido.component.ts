import { Component, OnInit, Input, Output, EventEmitter, SimpleChanges } from '@angular/core';
import { FormBuilder, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { AlterarPedidoContratadoDTO } from 'src/app/dto/pedido/alterar-pedido-contratadoDTO';
import { PedidoContratadoDTO } from 'src/app/dto/pedido/pedido-contratadoDTO';
import { PedidoService } from 'src/app/services/pedido.service';
import { DatePipe } from '@angular/common'

@Component({
  selector: 'app-modal-fechar-pedido',
  templateUrl: './modal-fechar-pedido.component.html',
  styleUrls: ['./modal-fechar-pedido.component.css']
})
export class ModalFecharPedidoComponent implements OnInit {

  @Input()
  visible: boolean;

  @Output()
  visibleChange: EventEmitter<boolean> = new EventEmitter<boolean>();

  @Input()
  pedidoContratado: PedidoContratadoDTO;

  @Output()
  pedidoContratadoChange: EventEmitter<PedidoContratadoDTO> = new EventEmitter<PedidoContratadoDTO>();

  form:                       FormGroup;
  alterarPedidoContratadoDTO: AlterarPedidoContratadoDTO;
  pedidoContratadoDTO: PedidoContratadoDTO;

  mensagemSucesso: string;  
  errors:          String[];

  dataInicial:      Date;
  dataFinal:         Date;
  despesas:         number;
  idPedido: number;

  constructor(
    private pedidoService: PedidoService,
    private formBuilder:   FormBuilder,
    private activatedRoute:                 ActivatedRoute,
    private router:                         Router,
    public datepipe:       DatePipe
  ) { }

  ngOnInit(): void {
    console.log(this.pedidoContratado?.dataHoraInicio);
    if(this.pedidoContratado){
      this.dataInicial = this.pedidoContratado?.dataHoraInicio == null || this.pedidoContratado?.dataHoraInicio == undefined ? null : new Date(this.pedidoContratado?.dataHoraInicio);
      this.dataFinal =   this.pedidoContratado?.dataHoraFim == null || this.pedidoContratado?.dataHoraFim == undefined ? null : new Date(this.pedidoContratado?.dataHoraFim);
      this.despesas = this.pedidoContratado?.despesas == null || this.pedidoContratado?.despesas == undefined ? null : this.pedidoContratado?.despesas;
    }
      
  }

  fechar(){
    this.visibleChange.emit(false);
    this.pedidoContratadoChange.emit(null);
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
      this.mensagemSucesso = 'Cadastro realizado com sucesso!';
      setTimeout( res => { this.mensagemSucesso = ''; }, 3000);
      this.errors = null;      
    }, errorResponse => {
      this.mensagemSucesso = null;
      this.errors = errorResponse.error.errors;
      setTimeout( res => { this.errors = null; }, 5000);
    });  
  }



}
