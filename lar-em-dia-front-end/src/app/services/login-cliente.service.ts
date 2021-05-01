import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { GerenciarClienteDTO } from '../dto/login-cliente/gerenciar-clienteDTO';
import { IncluirLoginClienteDTO } from '../dto/login-cliente/incluir-login-clienteDTO';

@Injectable({
  providedIn: 'root'
})
export class LoginClienteService {

  apiURL: string = environment.api + "/gerenciarcliente";    

  constructor(protected http: HttpClient) { }

  consultarEmail(email: string) : Observable<GerenciarClienteDTO> {
    return this.http.get<GerenciarClienteDTO>(`${this.apiURL}/${email}/email`);
  }

  alterar(gerenciarClienteDTO: IncluirLoginClienteDTO, id: number): Observable<IncluirLoginClienteDTO> {
    return this.http.put<IncluirLoginClienteDTO>(`${this.apiURL}/${id}`, gerenciarClienteDTO);
  }
  
  addFoto(gerenciarClienteDTO: GerenciarClienteDTO, formData: FormData) : Observable<any> {
    return this.http.put(`${this.apiURL}/${gerenciarClienteDTO.idCliente}/foto`, formData, { responseType: 'blob'} );
  }

}
