import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlterarTipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/alterar-tipo-servico-profissionalDTO';
import { ServicoProfissionalDTO } from 'src/app/dto/servico-profissional/servico-profissionalDTO';
import { TipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/tipo-servico-profissionalDTO';
import { ServicoProfissionalService } from 'src/app/services/servico-profissional.service';
import { TipoServicoProfissionalService } from 'src/app/services/tipo-servico-profissional.service';

@Component({
  selector: 'app-alterar-servico',
  templateUrl: './alterar-servico.component.html',
  styleUrls: ['./alterar-servico.component.css']
})
export class AlterarServicoComponent implements OnInit {

  constructor(
    private activatedRoute:                 ActivatedRoute,
    private router:                         Router,
    private tipoServicoProfissionalService: TipoServicoProfissionalService,    
    private servicoProfissionalService:     ServicoProfissionalService
  ) { }

  servicoProfissionalDTO: ServicoProfissionalDTO;
  idServico: number;
  nomeServico: string;
  ativo: boolean;

  alterarTipoServicoProfissionalDTO: AlterarTipoServicoProfissionalDTO;
  tipoServicoProfissionalDTO: TipoServicoProfissionalDTO;
  idTipo: number;
  nomeTipo: string;
  preco: number;
  
  mensagemSucesso: string;  
  errors: String[];

  ngOnInit(): void {
    let id = this.activatedRoute.snapshot.params.id;

    this.tipoServicoProfissionalService.consultar(id).subscribe(data => {      
      this.tipoServicoProfissionalDTO = data;
      this.idTipo = this.tipoServicoProfissionalDTO.idTipo;
      this.nomeTipo = this.tipoServicoProfissionalDTO.nome;
      this.preco = this.tipoServicoProfissionalDTO.preco;
      this.idServico = this.tipoServicoProfissionalDTO.servicoProfissionalDTO.idServico;
      this.nomeServico = this.tipoServicoProfissionalDTO.servicoProfissionalDTO.nome;
      this.ativo = this.tipoServicoProfissionalDTO.servicoProfissionalDTO.ativo;
    });

  }

  alterar(){
    const servicoProfissionalDTO: ServicoProfissionalDTO = new ServicoProfissionalDTO();
    servicoProfissionalDTO.ativo = this.ativo;
    servicoProfissionalDTO.nome = this.nomeServico;
    this.servicoProfissionalService.alterar(servicoProfissionalDTO, this.idServico)
      .subscribe(response => {
        const alterarTipoServicoProfissionalDTO: AlterarTipoServicoProfissionalDTO = new AlterarTipoServicoProfissionalDTO();
        alterarTipoServicoProfissionalDTO.nome = this.nomeTipo;
        alterarTipoServicoProfissionalDTO.preco = this.preco;
        this.tipoServicoProfissionalService.alterar(alterarTipoServicoProfissionalDTO, this.idTipo)
          .subscribe(response => {
            this.mensagemSucesso = "Cadastro alterado com sucesso!";
            setInterval( res => { this.mensagemSucesso = ''; }, 5000);
            this.errors = null;
            this.router.navigate(['listarservico']);
          }, errorResponse => {
            this.mensagemSucesso = null;
            this.errors = errorResponse.error.errors;
            setInterval( res => { this.errors = null; }, 5000);
          });
      }, errorResponse => {
        this.mensagemSucesso = null;
        this.errors = errorResponse.error.errors;
        setInterval( res => { this.errors = null; }, 5000);
      });

  }

}
