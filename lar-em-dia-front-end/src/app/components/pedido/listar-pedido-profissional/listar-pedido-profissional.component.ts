import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GerenciarClienteDTO } from 'src/app/dto/login-cliente/gerenciar-clienteDTO';
import { ListaPedidosProfissionalDTO } from 'src/app/dto/pedido/lista-pedidos-profissionalDTO';
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
    private loginClienteService:            LoginClienteService
  ) { }

  listaPedidosProfissionalDTO: ListaPedidosProfissionalDTO[];
  gerenciarClienteDTO: GerenciarClienteDTO;

  exibirDetalhar: boolean = false;

  mensagemSucesso: string;  
  errors:          String[];


  ngOnInit(): void {
    this.pedidoService.listaPedidosViewProfissional().subscribe( dado => {
      this.listaPedidosProfissionalDTO = dado;
    });
  }

  finalizar(id: number){        
    this.exibirDetalhar = true;
  }

  detalhar(id: number){
  }
  
  endereco(id: number){
    this.loginClienteService.consultar(id).subscribe( dado => {
      this.gerenciarClienteDTO = dado;
    });    
  }

  exibirPopup(nome: string): void {
    this.exibirDetalhar = nome === 'detalhar' ? true : false;
  }

}
