import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { IncluirTipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/incluir-tipo-servico-profissionalDTO';
import { ServicoProfissionalDTO } from 'src/app/dto/servico-profissional/servico-profissionalDTO';
import { TipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/tipo-servico-profissionalDTO';
import { TipoServicoProfissionalService } from 'src/app/services/tipo-servico-profissional.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-incluir-tipo-servico',
  templateUrl: './incluir-tipo-servico.component.html',
  styleUrls: ['./incluir-tipo-servico.component.css']
})
export class IncluirTipoServicoComponent implements OnInit {

  constructor(    
    private tipoServicoProfissionalService: TipoServicoProfissionalService,
    private activatedRoute:                 ActivatedRoute,
    private messageService:                 MessageService
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
      this.listaTipoServicoProfissionalDTO.map((tipo: TipoServicoProfissionalDTO) => this.mapearValorGrid(tipo));
    });
  }

  private mapearValorGrid(tipo: TipoServicoProfissionalDTO): TipoServicoProfissionalDTO {  
    let valorMoedaNumber = parseFloat(tipo.preco.toString());
    tipo.precoFmt = valorMoedaNumber.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
    return tipo;
  }

  inserir(){
    let TipoServicoProfissionalDTO: IncluirTipoServicoProfissionalDTO = new IncluirTipoServicoProfissionalDTO();
    TipoServicoProfissionalDTO.idServico = this.idServico;
    TipoServicoProfissionalDTO.nome = this.nomeTipo;
    TipoServicoProfissionalDTO.preco = this.preco;      
    this.tipoServicoProfissionalService.inserir(TipoServicoProfissionalDTO)
      .subscribe(response => {        
        this.messageService.add({severity:'success', summary: 'Sucesso', detail: 'Cadastro realizado.' , life: 5000 });
        setTimeout( res => { this.ngOnInit(); }, 5100);
        TipoServicoProfissionalDTO.nome = '';
        TipoServicoProfissionalDTO.preco = null;
      }, errorResponse => {        
        this.errors = errorResponse.error.errors;
        this.errors.forEach(response => {
          this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 5000 });
        });
      });
  }

}
