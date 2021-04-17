import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { IncluirTipoServicoProfissionalDTO } from '../dto/servico-profissional/incluir-tipo-servico-profissionalDTO';
import { TipoServicoProfissionalDTO } from '../dto/servico-profissional/tipo-servico-profissionalDTO';

@Injectable({
  providedIn: 'root'
})
export class TipoServicoProfissionalService {

  apiURL: string = environment.api + "/tiposervico";    
  
  constructor(protected http: HttpClient) { }

  inserir(incluirTipoServicoProfissionalDTO: IncluirTipoServicoProfissionalDTO): Observable<IncluirTipoServicoProfissionalDTO>{
    return this.http.post<IncluirTipoServicoProfissionalDTO>(`${this.apiURL}`, incluirTipoServicoProfissionalDTO);
  }

  listar(): Observable<TipoServicoProfissionalDTO>{
    return this.http.get<TipoServicoProfissionalDTO>(`${this.apiURL}`);
  }

}
