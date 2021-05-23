import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AgendaDTO } from 'src/app/dto/agenda/agendaDTO';
import { TipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/tipo-servico-profissionalDTO';
import { AgendaService } from 'src/app/services/agenda.service';
import { TipoServicoProfissionalService } from 'src/app/services/tipo-servico-profissional.service';
import { Location } from '@angular/common';
import { AuthService } from 'src/app/services/auth.service';
import { LoginClienteService } from 'src/app/services/login-cliente.service';
import { GerenciarClienteDTO } from 'src/app/dto/login-cliente/gerenciar-clienteDTO';
import { PedidoService } from 'src/app/services/pedido.service';
import { IncluirPedidoContratadoDTO } from 'src/app/dto/pedido/incluir-pedido-contratadoDTO';
import { DatePipe } from '@angular/common'
import { AlterarAgendaDTO } from 'src/app/dto/agenda/alterar-agendaDTO';

@Component({
  selector: 'app-agendar-servico',
  templateUrl: './agendar-servico.component.html',
  styleUrls: ['./agendar-servico.component.css']
})
export class AgendarServicoComponent implements OnInit {

  constructor(
    private tipoServicoProfissionalService: TipoServicoProfissionalService,
    private activatedRoute:                 ActivatedRoute,
    private agendaService:                  AgendaService,    
    private authService:                    AuthService,
    private _location:                      Location,
    private loginClienteService:            LoginClienteService,
    private pedidoService:                  PedidoService,
    public datepipe:                        DatePipe
  ) { }

  tipoServicoProfissionalDTO: TipoServicoProfissionalDTO;
  listarAgendaDTO:            AgendaDTO[];
  gerenciarClienteDTO:        GerenciarClienteDTO;
  agendaDTO:                  AgendaDTO;

  descricao: string;
  local:     string;
  data:      string;
  
  idTipo:      number;
  emailLogado: string;
  ativar:      boolean = false;

  mensagemSucesso: string;  
  errors:          String[];

  ngOnInit(): void {
    this.emailLogado = this.authService.getUsuarioAutenticado();
    this.loginClienteService.consultarEmail(this.emailLogado).subscribe( dado => {
      this.gerenciarClienteDTO = dado;
    });

    this.idTipo = this.activatedRoute.snapshot.params.id;
    this.tipoServicoProfissionalService.consultar(this.idTipo).subscribe( dado => {
      this.tipoServicoProfissionalDTO = dado;
    });
    this.agendaService.listar().subscribe( dado => {
      this.listarAgendaDTO = dado;
    });

  }

  inserir(){
    let incluirPedidoContratadoDTO: IncluirPedidoContratadoDTO = new IncluirPedidoContratadoDTO();
    incluirPedidoContratadoDTO.descricao = this.descricao;
    incluirPedidoContratadoDTO.local = this.local;
    incluirPedidoContratadoDTO.situacao = true;
    incluirPedidoContratadoDTO.precoContratado = this.tipoServicoProfissionalDTO.preco;
    incluirPedidoContratadoDTO.idAgenda = parseInt(this.data);
    incluirPedidoContratadoDTO.idTipo = this.tipoServicoProfissionalDTO.idTipo;
    incluirPedidoContratadoDTO.idCliente = this.gerenciarClienteDTO.idCliente;    
    this.pedidoService.inserir(incluirPedidoContratadoDTO)
      .subscribe( response => {        
        this.agendaService.consultar(parseInt(this.data))
          .subscribe( dado => {
            this.agendaDTO = dado;            
            let agenda: AlterarAgendaDTO = new AlterarAgendaDTO();            
            agenda.disponivel = false;                        
            this.agendaService.alterar(parseInt(this.data), agenda)
            .subscribe( response => {
              this.mensagemSucesso = 'Cadastro realizado com sucesso!';
              setInterval( res => { this.mensagemSucesso = ''; }, 3000);
              this.errors = null;
              setInterval( res => { 
                // this.router.navigate([''], {
                //   relativeTo: this.activatedRoute[''],
                // });
              }, 3500);
            }, errorResponse => {
              this.mensagemSucesso = null;
              this.errors = errorResponse.error.errors;
              setInterval( res => { this.errors = null; }, 15000);
            });   
          }, errorResponse => {
            this.mensagemSucesso = null;
            this.errors = errorResponse.error.errors;
            setInterval( res => { this.errors = null; }, 15000);
          });
      }, errorResponse => {
        this.mensagemSucesso = null;
        this.errors = errorResponse.error.errors;
        setInterval( res => { this.errors = null; }, 15000);
      });

  }

  habilitar(){
    if(this.ativar){
      this.ativar = false;
    } else {
      this.ativar = true;
    }    
  }

  backClicked() {
    this._location.back();
  }

}
