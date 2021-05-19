import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServicoProfissionalDTO } from 'src/app/dto/servico-profissional/servico-profissionalDTO';
import { TipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/tipo-servico-profissionalDTO';
import { ServicoProfissionalService } from 'src/app/services/servico-profissional.service';
import { TipoServicoProfissionalService } from 'src/app/services/tipo-servico-profissional.service';

@Component({
  selector: 'app-listar-tipo-servico',
  templateUrl: './listar-tipo-servico.component.html',
  styleUrls: ['./listar-tipo-servico.component.css']
})
export class ListarTipoServicoComponent implements OnInit {

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
    })
    this.servicoProfissionalService.consultar(this.idServico).subscribe( dado => {
      this.servicoProfissionalDTO = dado;
    });

  }

  agendar(id: number){
    this.router.navigate(['agendar', id], {
      relativeTo: this.activatedRoute['agendar'],
    });
  }

}
