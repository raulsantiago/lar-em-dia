import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GerenciarClienteDTO } from 'src/app/dto/login-cliente/gerenciar-clienteDTO';
import { ListaPedidosClienteDTO } from 'src/app/dto/pedido/lista-pedidos-clienteDTO';
import { PedidoContratadoDTO } from 'src/app/dto/pedido/pedido-contratadoDTO';
import { TipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/tipo-servico-profissionalDTO';
import { AuthService } from 'src/app/services/auth.service';
import { LoginClienteService } from 'src/app/services/login-cliente.service';
import { PedidoService } from 'src/app/services/pedido.service';

@Component({
  selector: 'app-listar-pedido-clientes',
  templateUrl: './listar-pedido-clientes.component.html',
  styleUrls: ['./listar-pedido-clientes.component.css']
})
export class ListarPedidoClientesComponent implements OnInit {

  constructor(
    private pedidoService:                  PedidoService,
    private loginClienteService:            LoginClienteService,
    private authService:                    AuthService,    
    private router:                         Router,
    private route:                          ActivatedRoute
  ) { }
  
  tipoServicoProfissionalDTO: TipoServicoProfissionalDTO;
  pedidoContratadoDTO:              PedidoContratadoDTO;

  listaPedidosClienteDTO: ListaPedidosClienteDTO[]; 
  gerenciarClienteDTO:    GerenciarClienteDTO;
  
  emailLogado: string;

  mensagemSucesso: string;  
  errors:          String[];

  ngOnInit(): void {
    this.emailLogado = this.authService.getUsuarioAutenticado();
    this.loginClienteService.consultarEmail(this.emailLogado).subscribe( dado => {
      this.gerenciarClienteDTO = dado;
        this.pedidoService.pedidosPorIdCliente(this.gerenciarClienteDTO?.idCliente).subscribe(dado => {
          this.listaPedidosClienteDTO = dado;
          console.log(this.listaPedidosClienteDTO);
        });
    });

  }

  cancelar(){}

}
