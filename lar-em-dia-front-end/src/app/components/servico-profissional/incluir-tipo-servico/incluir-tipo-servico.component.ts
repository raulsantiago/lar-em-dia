import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IncluirTipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/incluir-tipo-servico-profissionalDTO';
import { ServicoProfissionalDTO } from 'src/app/dto/servico-profissional/servico-profissionalDTO';
import { TipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/tipo-servico-profissionalDTO';
import { TipoServicoProfissionalService } from 'src/app/services/tipo-servico-profissional.service';

@Component({
  selector: 'app-incluir-tipo-servico',
  templateUrl: './incluir-tipo-servico.component.html',
  styleUrls: ['./incluir-tipo-servico.component.css']
})
export class IncluirTipoServicoComponent implements OnInit {

  constructor(    
    private tipoServicoProfissionalService: TipoServicoProfissionalService,
    private activatedRoute:                 ActivatedRoute
    ) { }
  
  servicoProfissionalDTO: ServicoProfissionalDTO;  
  listaTipoServicoProfissionalDTO: TipoServicoProfissionalDTO[];
  incluirTipoServicoProfissionalDTO: IncluirTipoServicoProfissionalDTO;

  nomeTipo: string;
  preco: number;
  idServico: number;

  mensagemSucesso: string;  
  errors: String[];

  ngOnInit(): void {
    this.idServico = this.activatedRoute.snapshot.params.id;
    let listarServicosPromise: Promise<TipoServicoProfissionalDTO[]> = this.tipoServicoProfissionalService.listarPorUmServico(this.idServico).toPromise();
    Promise.all([listarServicosPromise]).then(data => {
      this.listaTipoServicoProfissionalDTO = data[0];
    });
  }

  inserir(){
    let TipoServicoProfissionalDTO: IncluirTipoServicoProfissionalDTO = new IncluirTipoServicoProfissionalDTO();
    TipoServicoProfissionalDTO.idServico = this.idServico;
    TipoServicoProfissionalDTO.nome = this.nomeTipo;
    TipoServicoProfissionalDTO.preco = this.preco;      
    this.tipoServicoProfissionalService.inserir(TipoServicoProfissionalDTO)
      .subscribe(response => {
        this.mensagemSucesso = 'Cadastro realizado com sucesso!';
        setTimeout( res => { this.mensagemSucesso = ''; }, 5000);
        TipoServicoProfissionalDTO.nome = '';
        TipoServicoProfissionalDTO.preco = null;
        this.errors = null;
        this.ngOnInit();
      }, errorResponse => {
        this.mensagemSucesso = null;        
        this.errors = errorResponse.error.errors;
        setTimeout( res => { this.errors = null; }, 5000);
      });
  }

}
