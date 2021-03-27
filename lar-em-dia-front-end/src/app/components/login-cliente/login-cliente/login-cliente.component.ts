import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { LoginClienteDTO } from 'src/app/dto/login-cliente/login-clienteDTO';
import { AuthService } from 'src/app/services/auth.service';

@Component({
  selector: 'app-login-cliente',
  templateUrl: './login-cliente.component.html',
  styleUrls: ['./login-cliente.component.css']
})
export class LoginClienteComponent implements OnInit {

  constructor(
    private router: Router,
    private authService: AuthService
    ) { }
  
  nome: string;
  cpf: string;
  email: string;
  celular: string;
  senha: string;
  endereco: string;
  numero: string;
  bairro: string;
  complemento: string;
  estado: string;
  municipio: string;
  referencia: string;
  foto: any = null;
  ativo: boolean = true;
  
  cadastrando: boolean;
  mensagemSucesso: string;  
  errors: String[];

  ngOnInit(): void {
  }

  preparaCadastrar(event){
    event.preventDefault();
    this.cadastrando = true;    
  }

  cancelaCadastro(){
    this.cadastrando = false;
  }  

  onSubmit(){
    this.authService
    .tentarLogar(this.email, this.senha)
    .subscribe(response => {      
      const access_token = JSON.stringify(response);
      localStorage.setItem('access_token', access_token);
      this.router.navigate(['/gerenciarcliente']);
    }, errorResponse => {
      this.errors = ['Usuário e/ou senha incorreto(s).']
    })
  }

  incluir(){
    const loginClienteDTO: LoginClienteDTO = new LoginClienteDTO();
    loginClienteDTO.ativo = this.ativo;
    loginClienteDTO.bairro = this.bairro;
    loginClienteDTO.celular = this.celular;
    loginClienteDTO.complemento = this.complemento;
    loginClienteDTO.cpf = this.cpf;
    loginClienteDTO.email = this.email;
    loginClienteDTO.endereco = this.endereco;
    loginClienteDTO.estado = this.estado;
    loginClienteDTO.foto = this.foto;
    loginClienteDTO.municipio = this.municipio;
    loginClienteDTO.nome = this.nome;
    loginClienteDTO.numero = this.numero;
    loginClienteDTO.referencia = this.referencia;
    loginClienteDTO.senha = this.senha;
    this.authService.incluirCliente(loginClienteDTO)
    .subscribe( response => {      
      this.mensagemSucesso = "Cadastro realizado com sucesso! Efetue login";
      this.cadastrando = false;
            this.email = '';
            this.senha = '';            
            this.errors = []      
      }, errorResponse => {
            this.mensagemSucesso = null;
            this.errors = errorResponse.error.errors;
    })
  }


}
