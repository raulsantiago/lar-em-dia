import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AgendaDTO } from '../dto/agenda/agendaDTO';
import { AlterarAgendaDTO } from '../dto/agenda/alterar-agendaDTO';
import { IncluirAgendaDTO } from '../dto/agenda/incluir-agendaDTO';

@Injectable({
  providedIn: 'root'
})
export class AgendaService {

  apiURL: string = environment.api + "/agenda";

  constructor(protected http: HttpClient) { }

  listar(): Observable<AgendaDTO[]>{
    return this.http.get<AgendaDTO[]>(`${this.apiURL}`);
  }

  consultar(id: number): Observable<AgendaDTO>{
    return this.http.get<AgendaDTO>(`${this.apiURL}/${id}`);
  }

  inserir(incluirAgendaDTO: IncluirAgendaDTO): Observable<IncluirAgendaDTO>{
    return this.http.post<IncluirAgendaDTO>(`${this.apiURL}`, incluirAgendaDTO);
  }

  excluir(id: number): Observable<AgendaDTO>{
    return this.http.delete<AgendaDTO>(`${this.apiURL}/${id}`);
  }

  alterar(id: number, agendaDTO: AlterarAgendaDTO): Observable<AlterarAgendaDTO>{
    return this.http.patch<AlterarAgendaDTO>(`${this.apiURL}/${id}`, agendaDTO)
  }
  
}
