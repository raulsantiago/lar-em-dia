import { Component, OnInit, ViewChild } from '@angular/core';
import { AgendaDTO } from 'src/app/dto/agenda/agendaDTO';
import { IncluirAgendaDTO } from 'src/app/dto/agenda/incluir-agendaDTO';
import { AgendaService } from 'src/app/services/agenda.service';
import { DatePipe } from '@angular/common';
import { MessageService } from 'primeng/api';
import { Table } from 'primeng/table/table';

@Component({
  selector: 'app-agenda',
  templateUrl: './agenda.component.html',
  styleUrls: ['./agenda.component.css']
})
export class AgendaComponent implements OnInit {

  @ViewChild('table')
  table: Table;

  constructor(    
    private agendaService: AgendaService,
    public datepipe:       DatePipe,
    private messageService: MessageService
  ) { }

  agendaDTO:       AgendaDTO;
  listarAgendaDTO: AgendaDTO[];
  data:            Date;
  id:              number;

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
            this.messageService.add({severity:'success', summary: 'Sucesso', detail: 'Cadastro realizado.' , life: 2000 });
            setTimeout( res => { this.ngOnInit(); }, 2100);
          }
          , errorResponse => {            
            this.errors = errorResponse.error.errors;
            this.errors.forEach(response => {
              this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 5000 });
            });            
          });              
      }, errorResponse => {
        this.errors = errorResponse.error.errors;
        this.errors.forEach(response => {
          this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 5000 });
        });            
      });
  }

  showConfirm(id: number){
    this.id = id;
    this.messageService.clear();
    this.messageService.add({key: 'ex', sticky: true, severity:'warn', summary:'Deseja realmente excluir a data da agenda?', detail:'Confirme para excluir'});
  }

  onConfirm() {
    this.messageService.clear('ex');
    this.agendaService.excluir(this.id)
      .subscribe( response => {
        this.messageService.add({severity:'success', summary: 'Sucesso', detail: 'Cadastro excluído.' , life: 2000 });
        setTimeout( res => { this.ngOnInit(); }, 2100);
      }, errorResponse => {
        this.errors = errorResponse.error.errors;
        this.errors.forEach(response => {
          this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 5000 });
        });                    
      });
  }

  onReject() {
    this.messageService.clear('ex');
  }

}

