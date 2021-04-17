import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';
import { IncluirServicoProfissionalDTO } from '../dto/servico-profissional/incluir-servico-profissionalDTO';
import { ServicoProfissionalDTO } from '../dto/servico-profissional/servico-profissionalDTO';

@Injectable({
  providedIn: 'root'
})
export class ServicoProfissionalService {

  apiURL: string = environment.api + "/servico";    
  
  constructor(protected http: HttpClient) { }


  consultarNome(nome: String): Observable<ServicoProfissionalDTO>{
    return this.http.get<ServicoProfissionalDTO>(`${this.apiURL}/${nome}/nome`);
  }

  inserir(incluirServicoProfissionalDTO: IncluirServicoProfissionalDTO): Observable<IncluirServicoProfissionalDTO>{
    return this.http.post<IncluirServicoProfissionalDTO>(`${this.apiURL}`, incluirServicoProfissionalDTO);
  }


  /*
   alterar(id: number, gerenciarProfissionalDTO: GerenciarProfissionalDTO): Observable<GerenciarProfissionalDTO> {
    return this.http.put<GerenciarProfissionalDTO>(`${this.apiURL}/${id}`, gerenciarProfissionalDTO);
  }
  */
}
