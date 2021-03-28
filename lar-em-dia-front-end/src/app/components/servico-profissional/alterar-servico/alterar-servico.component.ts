import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-alterar-servico',
  templateUrl: './alterar-servico.component.html',
  styleUrls: ['./alterar-servico.component.css']
})
export class AlterarServicoComponent implements OnInit {

  constructor() { }

  idServico: number;
  nomeServico: string;
  nomeTipo: string;
  preco: string;
  ativo: boolean;

  mensagemSucesso: string;

  ngOnInit(): void {
  }

  alterar(){

  }

}
