import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { GerenciarClienteDTO } from '../dto/login-cliente/gerenciar-clienteDTO';

@Injectable({
  providedIn: 'root'
})
export class LoginClienteService {

  apiURL: string = environment.api + "/gerenciarcliente";    

  constructor(protected http: HttpClient) { }

  consultarEmail(email: string) : Observable<GerenciarClienteDTO> {
    return this.http.get<GerenciarClienteDTO>(`${this.apiURL}/${email}/email`);
  }

  alterar(id: number, gerenciarClienteDTO: GerenciarClienteDTO): Observable<GerenciarClienteDTO> {
    return this.http.put<GerenciarClienteDTO>(`${this.apiURL}/${id}`, gerenciarClienteDTO);
  }


}
