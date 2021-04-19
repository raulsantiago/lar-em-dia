import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { IncluirTipoServicoProfissionalDTO } from '../dto/servico-profissional/incluir-tipo-servico-profissionalDTO';
import { TipoServicoProfissionalDTO } from '../dto/servico-profissional/tipo-servico-profissionalDTO';
import { AlterarTipoServicoProfissionalDTO } from '../dto/servico-profissional/alterar-tipo-servico-profissionalDTO';

@Injectable({
  providedIn: 'root'
})
export class TipoServicoProfissionalService {

  apiURL: string = environment.api + "/tiposervico";    
  
  constructor(protected http: HttpClient) { }

  listar(): Observable<TipoServicoProfissionalDTO[]>{
    return this.http.get<TipoServicoProfissionalDTO[]>(`${this.apiURL}`);
  }

  consultar(id: number): Observable<TipoServicoProfissionalDTO>{
    return this.http.get<TipoServicoProfissionalDTO>(`${this.apiURL}/${id}`);
  }

  inserir(incluirTipoServicoProfissionalDTO: IncluirTipoServicoProfissionalDTO): Observable<IncluirTipoServicoProfissionalDTO>{
    return this.http.post<IncluirTipoServicoProfissionalDTO>(`${this.apiURL}`, incluirTipoServicoProfissionalDTO);
  }

  alterar(alterarTipoServicoProfissionalDTO: AlterarTipoServicoProfissionalDTO, id: number): Observable<AlterarTipoServicoProfissionalDTO> {
    return this.http.put<AlterarTipoServicoProfissionalDTO>(`${this.apiURL}/${id}`, alterarTipoServicoProfissionalDTO);
  }


}
