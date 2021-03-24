import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

import { LoginProfissionalDTO } from '../dto/login-profissional/login-profissionalDTO';
import { Observable } from 'rxjs';
import { environment } from '../../environments/environment';

@Injectable({
  providedIn: 'root'
})
export class LoginProfissionalService {

  apiURL: string = environment.apiURLBase + '/loginprofissional';  

    constructor(protected http: HttpClient) { }

    // incluir(loginProfissionalDTO: LoginProfissionalDTO) : Observable<LoginProfissionalDTO> {
    //   return this.http.post<LoginProfissionalDTO>(`${environment.url}${this.api}`, loginProfissionalDTO);
    // }

    // addFoto(loginProfissionalDTO: LoginProfissionalDTO, formData: FormData) : Observable<any> {
    //   return this.http.put(`${environment.url}${this.api}/${loginProfissionalDTO.idProfissional}/foto`, formData);
    // }

}
