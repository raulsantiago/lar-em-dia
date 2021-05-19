import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AgendaDTO } from 'src/app/dto/agenda/agendaDTO';
import { TipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/tipo-servico-profissionalDTO';
import { AgendaService } from 'src/app/services/agenda.service';
import { TipoServicoProfissionalService } from 'src/app/services/tipo-servico-profissional.service';
import { Location } from '@angular/common';

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
    private router:                         Router,
    private _location:                      Location    
  ) { }

  tipoServicoProfissionalDTO: TipoServicoProfissionalDTO;    
  idTipo: number;
  listarAgendaDTO: AgendaDTO[];

  descricao: string;
  local:     string;
  data:      string;
  ativar:    boolean = false;

  mensagemSucesso: string;  
  errors:          String[];

  ngOnInit(): void {
    this.idTipo = this.activatedRoute.snapshot.params.id;
    this.tipoServicoProfissionalService.consultar(this.idTipo).subscribe( dado => {
      this.tipoServicoProfissionalDTO = dado;
    });
    this.agendaService.listar().subscribe( dado => {
      this.listarAgendaDTO = dado;
    });

  }

  habilitar(){
    if(this.ativar){
      this.ativar = false;
    } else {
      this.ativar = true;
    }    
  }

  deshabilitar(){
    this.ativar = false;
  }

  backClicked() {
    this._location.back();
  }

  inserir(){

  }

}
