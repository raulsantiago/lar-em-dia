import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { GerenciarProfissionalDTO } from '../dto/login-profissional/gerenciar-profissionalDTO';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginProfissionalService {

  apiURL: string = environment.api + "/gerenciarprofissional";    

  constructor(protected http: HttpClient) { }

  consultarEmail(email: string) : Observable<GerenciarProfissionalDTO> {
    return this.http.get<GerenciarProfissionalDTO>(`${this.apiURL}/${email}/email`);
  }

  alterar(id: number, gerenciarProfissionalDTO: GerenciarProfissionalDTO): Observable<GerenciarProfissionalDTO> {
    return this.http.put<GerenciarProfissionalDTO>(`${this.apiURL}/${id}`, gerenciarProfissionalDTO);
  }

  addFoto(gerenciarProfissionalDTO: GerenciarProfissionalDTO, formData: FormData) : Observable<any> {
    return this.http.put(`${this.apiURL}/${gerenciarProfissionalDTO.idProfissional}/foto`, formData, { responseType: 'blob'} );
  }

}
