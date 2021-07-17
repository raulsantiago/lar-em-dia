import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/tipo-servico-profissionalDTO';
import { TipoServicoProfissionalService } from 'src/app/services/tipo-servico-profissional.service';
import { Table } from 'primeng/table/table';

@Component({
  selector: 'app-listar-servico',
  templateUrl: './listar-servico.component.html',
  styleUrls: ['./listar-servico.component.css']
})
export class ListarServicoComponent implements OnInit {

  @ViewChild('table')
  table: Table;

  constructor(
    private tipoServicoProfissionalService: TipoServicoProfissionalService,
    private router:                         Router,
    private route:                          ActivatedRoute
    ) { }

  listaTipoServicoProfissionalDTO: TipoServicoProfissionalDTO[];

  ngOnInit(): void {
    let listarServicosPromise: Promise<TipoServicoProfissionalDTO[]> = this.tipoServicoProfissionalService.listar().toPromise();
    Promise.all([listarServicosPromise]).then(data => {
      this.listaTipoServicoProfissionalDTO = data[0];
      this.listaTipoServicoProfissionalDTO.forEach((tipo: TipoServicoProfissionalDTO) => {
        tipo.ativoServicoFmt = tipo.servicoProfissionalDTO.ativo ? 'Sim' : 'Não';
        tipo.nomeServico = tipo.servicoProfissionalDTO.nome;
      });
      this.listaTipoServicoProfissionalDTO.map((tipo: TipoServicoProfissionalDTO) => this.mapearValorGrid(tipo));
    });

  }
  
  private mapearValorGrid(tipo: TipoServicoProfissionalDTO): TipoServicoProfissionalDTO {  
    let valorMoedaNumber = parseFloat(tipo.preco.toString());
    tipo.precoFmt = valorMoedaNumber.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
    return tipo;
  }

  alterarServicoTipo(id:number) {
    this.router.navigate(['alterarservico', id], {
      relativeTo: this.route['alterarservico'],
    });
  }


  incluirServicoTipo(id:number) {
    this.router.navigate(['cadastrartiposervico', id], {
      relativeTo: this.route['cadastrartiposervico'],
    });
  }
    
  
}
