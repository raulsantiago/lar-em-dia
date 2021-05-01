import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AlterarEstadoAtendidoDTO } from '../dto/regiao/alterar-estado-atendidoDTO';
import { AlterarMunicipioAtendidoDTO } from '../dto/regiao/alterar-municipio-atendidoDTO';
import { EstadoAtendidoDTO } from '../dto/regiao/estado-atendidoDTO';
import { IncluirMunicipioAtendidoDTO } from '../dto/regiao/incluir-municipio-atendidoDTO';
import { MunicipioAtendidoDTO } from '../dto/regiao/municipio-atendidoDTO';

@Injectable({
  providedIn: 'root'
})
export class RegiaoService {

  apiURL: string = environment.api + "/regiao";

  constructor(protected http: HttpClient) { }

  listaMunicipioIbge(uf: string): Observable<Object[]>{
    return this.http.get<Object[]>(`https://servicodados.ibge.gov.br/api/v1/localidades/estados/${uf}/municipios?orderBy=nome`);
  }

  listarUfAtivo(): Observable<EstadoAtendidoDTO[]> {
    return this.http.get<EstadoAtendidoDTO[]>(`${this.apiURL}/ufativo`);
  }

  listarMunicipo(): Observable<MunicipioAtendidoDTO[]>{
    return this.http.get<MunicipioAtendidoDTO[]>(`${this.apiURL}/municipio`);
  }

  listarMunicipioAtivoPorUfAtivo(idUf: number): Observable<MunicipioAtendidoDTO[]>{
    return this.http.get<MunicipioAtendidoDTO[]>(`${this.apiURL}/${idUf}/uf`);
  }

  inserir(municipioAtendidoDTO: IncluirMunicipioAtendidoDTO): Observable<IncluirMunicipioAtendidoDTO>{
    return this.http.post<IncluirMunicipioAtendidoDTO>(`${this.apiURL}`, municipioAtendidoDTO);
  }

  consultar(id: number): Observable<MunicipioAtendidoDTO>{
    return this.http.get<MunicipioAtendidoDTO>(`${this.apiURL}/${id}`);
  }

  consultarUf(id: number): Observable<EstadoAtendidoDTO>{
    return this.http.get<EstadoAtendidoDTO>(`${this.apiURL}/${id}/estado`);
  }

  alterarMunicipio(municipioAtendidoDTO: AlterarMunicipioAtendidoDTO, id: number):  Observable<AlterarMunicipioAtendidoDTO>{
    return this.http.put<AlterarMunicipioAtendidoDTO>(`${this.apiURL}/${id}/municipio`, municipioAtendidoDTO);
  }

  alterarEstado(estadoAtendidoDTO: AlterarEstadoAtendidoDTO, id: number):  Observable<AlterarEstadoAtendidoDTO>{
    return this.http.put<AlterarEstadoAtendidoDTO>(`${this.apiURL}/${id}/estado`, estadoAtendidoDTO);
  }

 
}
