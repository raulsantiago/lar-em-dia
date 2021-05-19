import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ServicoProfissionalDTO } from 'src/app/dto/servico-profissional/servico-profissionalDTO';
import { ServicoProfissionalService } from 'src/app/services/servico-profissional.service';

@Component({
  selector: 'app-listar-servico-solicitacao',
  templateUrl: './listar-servico-solicitacao.component.html',
  styleUrls: ['./listar-servico-solicitacao.component.css']
})
export class ListarServicoSolicitacaoComponent implements OnInit {

  constructor(
    private servicoProfissionalService: ServicoProfissionalService,
    private router:                     Router,
    private route:                      ActivatedRoute
  ) { }

  listarServicoProfissionalDTO: ServicoProfissionalDTO[];

  mensagemSucesso: string;  
  errors:          String[];


  ngOnInit(): void {
    this.servicoProfissionalService.listarAtivos().subscribe( dado => {
      this.listarServicoProfissionalDTO = dado;
    });

  }

  listarTipos(id: number){
    this.router.navigate(['solicitar', id], {
      relativeTo: this.route['solicitar'],
    });
  }

}
