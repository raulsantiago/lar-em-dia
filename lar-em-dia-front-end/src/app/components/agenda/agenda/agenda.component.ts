import { Component, OnInit } from '@angular/core';
import { AgendaDTO } from 'src/app/dto/agenda/agendaDTO';
import { IncluirAgendaDTO } from 'src/app/dto/agenda/incluir-agendaDTO';
import { AgendaService } from 'src/app/services/agenda.service';
import { DatePipe } from '@angular/common'

@Component({
  selector: 'app-agenda',
  templateUrl: './agenda.component.html',
  styleUrls: ['./agenda.component.css']
})
export class AgendaComponent implements OnInit {

  constructor(    
    private agendaService: AgendaService,
    public datepipe:       DatePipe
  ) { }

  agendaDTO:       AgendaDTO;
  listarAgendaDTO: AgendaDTO[];
  data:            Date;

  mensagemSucesso: string;  
  errors:          String[];

  ngOnInit(): void {
    this.agendaService.listar().subscribe( dado => {
      this.listarAgendaDTO = dado;
    });
  }

  inserir(){
    let incluirAgendaDTO: IncluirAgendaDTO = new IncluirAgendaDTO();
    incluirAgendaDTO.dia = this.datepipe.transform(this.data, 'dd/MM/yyyy');
    incluirAgendaDTO.disponivel = true;
    incluirAgendaDTO.turno = 'Manhã';    
    this.agendaService.inserir(incluirAgendaDTO)
      .subscribe( response => {
        let incluirAgendaDTO2: IncluirAgendaDTO = new IncluirAgendaDTO();
        incluirAgendaDTO2.dia = this.datepipe.transform(this.data, 'dd/MM/yyyy');
        incluirAgendaDTO2.disponivel = true;
        incluirAgendaDTO2.turno = 'Tarde';
        this.agendaService.inserir(incluirAgendaDTO2)
          .subscribe( response => {
            this.mensagemSucesso = 'Cadastro realizado com sucesso!';
            setTimeout( res => { this.mensagemSucesso = ''; }, 3000);
            this.errors = null;        
            setTimeout( res => { this.ngOnInit(); }, 3500);
          }
          , errorResponse => {
            this.mensagemSucesso = null;
            this.errors = errorResponse.error.errors;
            setTimeout( res => { this.errors = null; }, 15000);
          });              
      }, errorResponse => {
        this.mensagemSucesso = null;
        this.errors = errorResponse.error.errors;
        setTimeout( res => { this.errors = null; }, 5000);
      });
  }

  excluir(id: number){
    this.agendaService.excluir(id)
      .subscribe( response => {
        this.mensagemSucesso = 'Cadastro excluído com sucesso!';
        setTimeout( res => { this.mensagemSucesso = ''; }, 5000);
        this.errors = null;
        setTimeout( res => { this.ngOnInit(); }, 6000);        
      }, errorResponse => {
        this.mensagemSucesso = null;
        this.errors = errorResponse.error.errors;
        setTimeout( res => { this.errors = null; }, 5000);
      });
  }

}
