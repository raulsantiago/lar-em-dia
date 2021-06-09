import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';

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

  constructor() { }

  ngOnInit(): void {
  }

  fechar(){
    this.visibleChange.emit(false);
  }

}
