import { HttpClient, HttpParams } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { environmentToken } from 'src/environments/environment.token';
import { LoginProfissionalDTO } from '../dto/login-profissional/login-profissionalDTO';
import { JwtHelperService } from '@auth0/angular-jwt'  
import { LoginClienteDTO } from '../dto/login-cliente/login-clienteDTO';
import { IncluirLoginClienteDTO } from '../dto/login-cliente/incluir-login-clienteDTO';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiURLProfissional: string = environment.api + "/loginprofissional";
  apiURLCliente: string = environment.api + "/logincliente";
  tokenURL: string = environmentToken.apiURLBase + environmentToken.obterTokenUrl;
  clientID: string = environmentToken.clientId;
  clientSecret: string = environmentToken.clientSecret;
  
  jwtHelper: JwtHelperService = new JwtHelperService();
  
  constructor(private http: HttpClient) { }  

  obterToken(){
    const tokenString = localStorage.getItem('access_token');
    if(tokenString){
      const token = JSON.parse(tokenString).access_token;
      return token;
    }
    return null;
  }

  encerrarSessao(){
    localStorage.removeItem('access_token');
  }

  getUsuarioAutenticado(){
    const token = this.obterToken();
    if(token){
      const usuario = this.jwtHelper.decodeToken(token).user_name;      
      return usuario;
    }
    return null;
  }

  getUsuarioPerfil(){
    const token = this.obterToken();
    if(token){
      const perfil = this.jwtHelper.decodeToken(token).authorities[0];
      return perfil;
    }
    return null;
  }

  isAuthenticated() : boolean {
    const token = this.obterToken();
    if(token){
      const expired = this.jwtHelper.isTokenExpired(token);
      return !expired;
    }
    return false;
  }

  tentarLogar( username: string, password: string ) : Observable<any> {
    const params = new HttpParams()
                        .set('username', username)
                        .set('password', password)
                        .set('grant_type', 'password')

    const headers = {
      'Authorization': 'Basic ' + btoa(`${this.clientID}:${this.clientSecret}`),
      'Content-Type': 'application/x-www-form-urlencoded'
    }
    
    return this.http.post( this.tokenURL, params.toString(), { headers });
  }

  incluirProfissional(loginProfissionalDTO: LoginProfissionalDTO) : Observable<LoginProfissionalDTO> {
    return this.http.post<LoginProfissionalDTO>(this.apiURLProfissional, loginProfissionalDTO);
  }

  incluirCliente(loginClienteDTO: IncluirLoginClienteDTO) : Observable<IncluirLoginClienteDTO> {
    return this.http.post<IncluirLoginClienteDTO>(this.apiURLCliente, loginClienteDTO);
  }




}
