import { Component, OnInit } from '@angular/core';
import { IncluirServicoProfissionalDTO } from 'src/app/dto/servico-profissional/incluir-servico-profissionalDTO';
import { IncluirTipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/incluir-tipo-servico-profissionalDTO';
import { ServicoProfissionalDTO } from 'src/app/dto/servico-profissional/servico-profissionalDTO';
import { ServicoProfissionalService } from 'src/app/services/servico-profissional.service';
import { TipoServicoProfissionalService } from 'src/app/services/tipo-servico-profissional.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-incluir-servico',
  templateUrl: './incluir-servico.component.html',
  styleUrls: ['./incluir-servico.component.css']
})
export class IncluirServicoComponent implements OnInit {

  constructor(
    private servicoProfissionalService:     ServicoProfissionalService,
    private tipoServicoProfissionalService: TipoServicoProfissionalService,
    private messageService:                 MessageService
    ) { }

  nomeServico: string;
  nomeTipo: string;
  preco: number;
  servicoProfissionalDTO: ServicoProfissionalDTO;
  incluirTipoServicoProfissionalDTO: IncluirTipoServicoProfissionalDTO;

  mensagemSucesso: string;  
  errors: String[];

  ngOnInit(): void {
  }

  inserir(){
    const incluirServicoProfissionalDTO: IncluirServicoProfissionalDTO = new IncluirServicoProfissionalDTO();
    incluirServicoProfissionalDTO.nome = this.nomeServico;
    incluirServicoProfissionalDTO.ativo = true;
    this.servicoProfissionalService.inserir(incluirServicoProfissionalDTO)
      .subscribe( response => {
        this.servicoProfissionalService.consultarNome(this.nomeServico)
          .subscribe(data => {
            this.servicoProfissionalDTO = data;
            const incluirTipoServicoProfissionalDTO: IncluirTipoServicoProfissionalDTO = new IncluirTipoServicoProfissionalDTO();
            incluirTipoServicoProfissionalDTO.idServico = this.servicoProfissionalDTO.idServico;
            incluirTipoServicoProfissionalDTO.nome = this.nomeTipo;
            incluirTipoServicoProfissionalDTO.preco = this.preco;          
            this.tipoServicoProfissionalService.inserir(incluirTipoServicoProfissionalDTO)
              .subscribe(response => {
                this.messageService.add({severity:'success', summary: 'Sucesso', detail: 'Cadastro realizado.' , life: 1500 });
                this.nomeServico = '';
                this.nomeTipo = '';
                this.preco = null;
              }
              , errorResponse => {                
                this.errors = errorResponse.error.errors;
                this.errors.forEach(response => {
                  this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 1500 });
                });
              });
          
            }, errorResponse => {
              this.errors = errorResponse.error.errors;
              this.errors.forEach(response => {
                this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 1500 });
              });
          });

      }, errorResponse => {
        this.errors = errorResponse.error.errors;
        this.errors.forEach(response => {
          this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 1500 });
        });        
      });      

  }


}
