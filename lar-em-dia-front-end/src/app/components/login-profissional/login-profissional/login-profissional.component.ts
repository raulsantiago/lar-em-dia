import { OnInit } from '@angular/core';
import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { LoginProfissionalDTO } from 'src/app/dto/login-profissional/login-profissionalDTO';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login-profissional',
  templateUrl: './login-profissional.component.html',
  styleUrls: ['./login-profissional.component.css']
})
export class LoginProfissionalComponent implements OnInit {

  constructor(
    private router: Router,
    private authService: AuthService
    ) { }

  
  nome: string;
  cpf: string;
  email: string;
  celular: string;
  senha: string;
  foto: any = null;
  ativo: boolean = true;

  cadastrando: boolean;
  mensagemSucesso: string;  
  errors: String[];

  ngOnInit(): void {    
  }

  onSubmit(){
    console.log(`Email: ${this.email}, Senha: ${this.senha}`);

    this.authService
    .tentarLogar(this.email, this.senha)
    .subscribe(response => {
      console.log(response)
      const access_token = JSON.stringify(response);
      localStorage.setItem('access_token', access_token);
      this.router.navigate(['/gerenciarprofissional']);
    }, errorResponse => {
      this.errors = ['Usuário e/ou senha incorreto(s).']
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
    loginProfissionalDTO.foto = this.foto;
    loginProfissionalDTO.nome = this.nome;
    console.log(`nome: ${this.nome}, cpf: ${this.cpf}, email: ${this.email}, senha: ${this.senha}
    , ativo: ${this.ativo}, celular: ${this.celular}, foto: ${this.foto}`);
    this.authService.incluir(loginProfissionalDTO)
    .subscribe( response => {
      console.log(response);
      this.mensagemSucesso = "Cadastro realizado com sucesso! Efetue login";
      this.cadastrando = false;
            this.email = '';
            this.senha = '';
            // this.ativo = null;
            // this.celular = '';
            // this.cpf = '';
            // this.foto = null;
            // this.nome = '';
            // console.log(`nome: ${this.nome}, cpf: ${this.cpf}, email: ${this.email}, senha: ${this.senha}
            // , ativo: ${this.ativo}, celular: ${this.celular}, foto: ${this.foto}`);
            this.errors = []      
      }, errorResponse => {
            this.mensagemSucesso = null;
            this.errors = errorResponse.error.errors;
    })

  }

}
