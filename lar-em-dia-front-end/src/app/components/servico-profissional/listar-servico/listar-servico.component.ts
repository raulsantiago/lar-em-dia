import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-listar-servico',
  templateUrl: './listar-servico.component.html',
  styleUrls: ['./listar-servico.component.css']
})
export class ListarServicoComponent implements OnInit {

  constructor() { }

  servicoProfissionalDTO: Object[];

  ngOnInit(): void {
    this.servicoProfissionalDTO = [
      {nome: "Ar condicionado de janela", tipoServidoDTO: "Limpeza", preco: "R$ 123.456,99"},
      {nome: "Ar condicionado de janela", tipoServidoDTO: "Instalação", preco: "R$ 0,01"}
    ];
  }   
    
  
}
