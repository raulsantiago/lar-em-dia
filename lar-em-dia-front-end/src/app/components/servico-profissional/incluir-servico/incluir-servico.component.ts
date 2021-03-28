import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-incluir-servico',
  templateUrl: './incluir-servico.component.html',
  styleUrls: ['./incluir-servico.component.css']
})
export class IncluirServicoComponent implements OnInit {

  constructor() { }

  nomeServico: string;
  nomeTipo: string;
  preco: string;

  mensagemSucesso: string;

  ngOnInit(): void {
  }

  inserir(){

  }

}
