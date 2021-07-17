import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServicoProfissionalDTO } from 'src/app/dto/servico-profissional/servico-profissionalDTO';
import { TipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/tipo-servico-profissionalDTO';
import { ServicoProfissionalService } from 'src/app/services/servico-profissional.service';
import { TipoServicoProfissionalService } from 'src/app/services/tipo-servico-profissional.service';
import { Table } from 'primeng/table/table';

@Component({
  selector: 'app-listar-tipo-servico',
  templateUrl: './listar-tipo-servico.component.html',
  styleUrls: ['./listar-tipo-servico.component.css']
})
export class ListarTipoServicoComponent implements OnInit {

  @ViewChild('table')
  table: Table;

  constructor(    
    private servicoProfissionalService:     ServicoProfissionalService,
    private tipoServicoProfissionalService: TipoServicoProfissionalService,
    private activatedRoute:                 ActivatedRoute,
    private router:                         Router    
  ) { }

  listarTipoServicoProfissionalDTO: TipoServicoProfissionalDTO[];
  servicoProfissionalDTO: ServicoProfissionalDTO;
  idServico: number;

  mensagemSucesso: string;  
  errors:          String[];

  ngOnInit(): void {
    this.idServico = this.activatedRoute.snapshot.params.id;
    this.tipoServicoProfissionalService.listarPorUmServico(this.idServico).subscribe( dado => {
      this.listarTipoServicoProfissionalDTO = dado;
      this.listarTipoServicoProfissionalDTO.map((tipo: TipoServicoProfissionalDTO) => this.mapearValorGrid(tipo));
    })
    this.servicoProfissionalService.consultar(this.idServico).subscribe( dado => {
      this.servicoProfissionalDTO = dado;
    });
    
  }

  private mapearValorGrid(tipo: TipoServicoProfissionalDTO): TipoServicoProfissionalDTO {  
    let valorMoedaNumber = parseFloat(tipo.preco.toString());
    tipo.precoFmt = valorMoedaNumber.toLocaleString('pt-BR', { style: 'currency', currency: 'BRL' });
    return tipo;
  }

  agendar(id: number){
    this.router.navigate(['agendar', id], {
      relativeTo: this.activatedRoute['agendar'],
    });
  }

}
