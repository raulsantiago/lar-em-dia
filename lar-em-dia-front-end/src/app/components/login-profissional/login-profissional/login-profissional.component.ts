import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginProfissionalDTO } from 'src/app/dto/login-profissional/login-profissionalDTO';
import { AuthService } from 'src/app/services/auth.service';
import { environment } from 'src/environments/environment';
import * as CryptoJS from 'crypto-js';
import { LoginProfissionalService } from 'src/app/services/login-profissional.service';

@Component({
  selector: 'app-login-profissional',
  templateUrl: './login-profissional.component.html',
  styleUrls: ['./login-profissional.component.css']
})
export class LoginProfissionalComponent implements OnInit {

  constructor(
    private router: Router,
    private authService: AuthService,
    private loginProfissionalService: LoginProfissionalService
    ) { }

  
  nome: string;
  cpf: string;
  email: string;
  celular: string;
  senha: string;  
  ativo: boolean = true;

  textoEncriptado: string;
  textoDesencriptado: string;

  cadastrando: boolean;
  mensagemSucesso: string;  
  errors: String[];

  mensagemSucesso2: string;  
  errors2: String[];
  not: any;
  emailRec: string;

  ngOnInit(): void {
    //this.converterSenha;    
  }

  // converterSenha(conversion: string) {
  //   if (conversion === 'encriptar') {
  //     this.textoEncriptado = CryptoJS.AES.encrypt(this.senha.trim(), environment.key.trim()).toString();
  //     console.log(this.textoEncriptado);
  //   } else {
  //     this.textoDesencriptado = CryptoJS.AES.decrypt(this.senha.trim(), environment.key.trim()).toString(CryptoJS.enc.Utf8);
  //     console.log(this.textoDesencriptado);
  //   }
  // }

  onSubmit(){
    this.authService
    .tentarLogar(this.email, this.senha)
    .subscribe(response => {      
      const access_token = JSON.stringify(response);
      localStorage.setItem('access_token', access_token);
      this.router.navigate(['/gerenciarprofissional']);
    }, errorResponse => {
      this.errors = ['Usuário e/ou senha incorreto(s).'];
      setTimeout( res => { this.errors = null; }, 5000);
    })
  }

  preparaCadastrar(event){
    event.preventDefault();
    this.cadastrando = true;    
  }

  cancelaCadastro(){
    this.cadastrando = false;
  }  

  incluir(){
    const loginProfissionalDTO: LoginProfissionalDTO = new LoginProfissionalDTO();
    loginProfissionalDTO.email = this.email;
    loginProfissionalDTO.senha = this.senha;    
    loginProfissionalDTO.ativo = this.ativo;
    loginProfissionalDTO.celular = this.celular;
    loginProfissionalDTO.cpf = this.cpf;        
    loginProfissionalDTO.nome = this.nome;    
    this.authService.incluirProfissional(loginProfissionalDTO)
    .subscribe( response => {      
      this.mensagemSucesso = "Cadastro realizado com sucesso! Efetue login";
      setTimeout( res => { this.mensagemSucesso = ''; }, 2000);
      this.cadastrando = false;
      this.email = '';
      this.senha = '';
      this.errors = null;
      }, errorResponse => {
        this.mensagemSucesso = null;
        this.errors = errorResponse.error.errors;
        setTimeout( res => { this.errors = null; }, 5000);
    })
  }

  esqueceuSenha(){
    this.mensagemSucesso2 = "Email enviado com sucesso aguarde chegar na sua conta";
    setTimeout( res => { this.mensagemSucesso2 = ''; }, 95000);
    this.loginProfissionalService.sendMailProf(this.emailRec, this.not).subscribe( response => {      
      this.mensagemSucesso2 = "Email enviado com sucesso aguarde chegar na sua conta";
      setTimeout( res => { this.mensagemSucesso2 = ''; }, 95000);
      this.emailRec = '';      
      this.errors2 = null;      
      }, errorResponse => {
        this.emailRec = '';      
        this.mensagemSucesso2 = null;
        this.errors2 = ["E-mail não consta na nossa base de dados."];
        //this.errors2 = errorResponse.error.errors;
        setTimeout( res => { this.errors2 = null; }, 10000);
    });
    
    
  }

}
