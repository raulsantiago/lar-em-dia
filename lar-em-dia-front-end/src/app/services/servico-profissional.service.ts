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

  consultar(id: number): Observable<ServicoProfissionalDTO>{
    return this.http.get<ServicoProfissionalDTO>(`${this.apiURL}/${id}`);
  }

  listarAtivos(): Observable<ServicoProfissionalDTO[]>{
    return this.http.get<ServicoProfissionalDTO[]>(`${this.apiURL}/ativos`);
  }

  inserir(incluirServicoProfissionalDTO: IncluirServicoProfissionalDTO): Observable<IncluirServicoProfissionalDTO>{
    return this.http.post<IncluirServicoProfissionalDTO>(`${this.apiURL}`, incluirServicoProfissionalDTO);
  }

  alterar(servicoProfissionalDTO: ServicoProfissionalDTO, id: number): Observable<ServicoProfissionalDTO> {
    return this.http.put<ServicoProfissionalDTO>(`${this.apiURL}/${id}`, servicoProfissionalDTO);
  }
  
}
