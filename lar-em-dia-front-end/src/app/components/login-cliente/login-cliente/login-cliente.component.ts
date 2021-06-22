import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { IncluirLoginClienteDTO } from 'src/app/dto/login-cliente/incluir-login-clienteDTO';
import { EstadoAtendidoDTO } from 'src/app/dto/regiao/estado-atendidoDTO';
import { MunicipioAtendidoDTO } from 'src/app/dto/regiao/municipio-atendidoDTO';
import { AuthService } from 'src/app/services/auth.service';
import { LoginClienteService } from 'src/app/services/login-cliente.service';
import { RegiaoService } from 'src/app/services/regiao.service';

@Component({
  selector: 'app-login-cliente',
  templateUrl: './login-cliente.component.html',
  styleUrls: ['./login-cliente.component.css']
})
export class LoginClienteComponent implements OnInit {

  constructor(
    private router: Router,
    private authService: AuthService,
    private regiaoService: RegiaoService,
    private loginClienteService: LoginClienteService
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
  estado: number;
  municipio: number;
  referencia: string;  
  ativo: boolean = true;

  emailRec: string;

  estadoAtendidoDTO: EstadoAtendidoDTO;
  listaEstadoAtendidoDTO: EstadoAtendidoDTO[];
  listaMunicipioAtendidoDTO: MunicipioAtendidoDTO[];
  
  cadastrando: boolean;
  mensagemSucesso: string;  
  errors: String[];

  mensagemSucesso2: string;  
  errors2: String[];
  not: any;

  ngOnInit(): void {   
    this.regiaoService.listarUfAtivo().subscribe( dado => {
      this.listaEstadoAtendidoDTO = dado;
    });
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
      this.errors = ['Usuário e/ou senha incorreto(s).'];
      setTimeout( res => { this.errors = null; }, 5000);
    })
  }

  tipoAcaoUf(idEstado: number){
    this.regiaoService.listarMunicipioAtivoPorUfAtivo(idEstado).subscribe( dado =>{
      this.listaMunicipioAtendidoDTO = dado;      
    });
  }

  incluir(){
    const loginClienteDTO: IncluirLoginClienteDTO = new IncluirLoginClienteDTO();
    loginClienteDTO.ativo = this.ativo;
    loginClienteDTO.bairro = this.bairro;
    loginClienteDTO.celular = this.celular;
    loginClienteDTO.complemento = this.complemento;
    loginClienteDTO.cpf = this.cpf;
    loginClienteDTO.email = this.email;
    loginClienteDTO.endereco = this.endereco;
    loginClienteDTO.idEstado = this.estado;    
    loginClienteDTO.idMunicipio = this.municipio;
    loginClienteDTO.nome = this.nome;
    loginClienteDTO.numero = this.numero;
    loginClienteDTO.referencia = this.referencia;
    loginClienteDTO.senha = this.senha;    
    this.authService.incluirCliente(loginClienteDTO)
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
    this.loginClienteService.sendMail(this.emailRec, this.not).subscribe( response => {
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
