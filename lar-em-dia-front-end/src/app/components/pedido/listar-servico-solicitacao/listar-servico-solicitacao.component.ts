import { Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServicoProfissionalDTO } from 'src/app/dto/servico-profissional/servico-profissionalDTO';
import { ServicoProfissionalService } from 'src/app/services/servico-profissional.service';
import { Table } from 'primeng/table/table';

@Component({
  selector: 'app-listar-servico-solicitacao',
  templateUrl: './listar-servico-solicitacao.component.html',
  styleUrls: ['./listar-servico-solicitacao.component.css']
})
export class ListarServicoSolicitacaoComponent implements OnInit {

  @ViewChild('table')
  table: Table;

  constructor(
    private servicoProfissionalService: ServicoProfissionalService,
    private router:                     Router,
    private route:                      ActivatedRoute
  ) { }

  listarServicoProfissionalDTO: ServicoProfissionalDTO[];

  ngOnInit(): void {
    this.servicoProfissionalService.listarAtivos().subscribe( dado => {
      this.listarServicoProfissionalDTO = dado;
    });
    this.load();
  }

  listarTipos(id: number){
    this.router.navigate(['solicitar', id], {
      relativeTo: this.route['solicitar'],
    });
  }

  load() {    
    console.log('sessionStorage', sessionStorage);    
    (sessionStorage.refresh == 'true' || !sessionStorage.refresh) && location.reload();
    sessionStorage.refresh = false;
  }

}
