import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GerenciarClienteDTO } from 'src/app/dto/login-cliente/gerenciar-clienteDTO';
import { AuthService } from 'src/app/services/auth.service';
import { LoginClienteService } from 'src/app/services/login-cliente.service';

@Component({
  selector: 'app-gerenciar-login-cliente',
  templateUrl: './gerenciar-login-cliente.component.html',
  styleUrls: ['./gerenciar-login-cliente.component.css']
})
export class GerenciarLoginClienteComponent implements OnInit {

  constructor(
    private loginClienteService: LoginClienteService,
    private authService: AuthService,
    private router: Router
    ) { }

  idCliente: number;
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

  gerenciarClienteDTO: GerenciarClienteDTO

  usuarioLogado: string;
  mensagemSucesso: string;  
  errors: String[];
 

  ngOnInit(): void {

    /*
    this.usuarioLogado = this.authService.getUsuarioAutenticado();    
    console.log(this.usuarioLogado);
    this.loginProfissionalService.consultarEmail(this.usuarioLogado).subscribe( dado =>{
      this.gerenciarProfissionalDTO = dado;
    });


    */
    this.usuarioLogado = this.authService.getUsuarioAutenticado();
    this.loginClienteService.consultarEmail(this.usuarioLogado).subscribe( dado =>{
      this.gerenciarClienteDTO = dado;
      // console.log(dado) ;
      // this.idCliente = dado.idCliente;
      // this.ativo = dado.ativo;
      // this.celular = dado.celular;
      // this.cpf = dado.cpf;
      // this.email = dado.email;
      // this.foto = dado.foto;      
      // this.nome = dado.nome;
      // this.senha = dado.senha;
      // this.endereco = dado.endereco;
      // this.numero = dado.numero;
      // this.bairro = dado.bairro;
      // this.complemento = dado.complemento;
      // this.estado = dado.estado;
      // this.municipio = dado.municipio;
      // this.referencia = dado.referencia;
    });
  }

  alterar(){
    // let gerenciarClienteDTO: GerenciarClienteDTO = new GerenciarClienteDTO();
    // gerenciarClienteDTO.referencia = this.referencia
    // gerenciarClienteDTO.ativo = this.ativo   
    // gerenciarClienteDTO.celular = this.celular
    // gerenciarClienteDTO.cpf = this.cpf
    // gerenciarClienteDTO.email = this.email
    // gerenciarClienteDTO.foto = this.foto
    // gerenciarClienteDTO.nome = this.nome
    // gerenciarClienteDTO.senha = this.senha
    // gerenciarClienteDTO.endereco = this.endereco
    // gerenciarClienteDTO.numero = this.numero
    // gerenciarClienteDTO.bairro = this.bairro
    // gerenciarClienteDTO.complemento = this.complemento
    // gerenciarClienteDTO.estado = this.estado
    // gerenciarClienteDTO.municipio = this.municipio
    this.loginClienteService.alterar(this.gerenciarClienteDTO.idCliente, this.gerenciarClienteDTO).subscribe( response => {      
      this.mensagemSucesso = "Cadastro alterado com sucesso!";
      setInterval( res => { this.mensagemSucesso = ''; }, 5000);
      this.errors = null;
      }, errorResponse => {
        this.mensagemSucesso = null;
        this.errors = errorResponse.error.errors;
        setInterval( res => { this.errors = null; }, 5000);
    })
  }

  logout(){
    this.authService.encerrarSessao();
    this.router.navigate(['']);    
  }

  uploadFoto(event){
    const files = event.target.files;
    if(files){
      const foto = files[0];
      const formData: FormData = new FormData();
      formData.append("foto", foto);
      this.loginClienteService.addFoto(this.gerenciarClienteDTO, formData)
        .subscribe(response => this.ngOnInit() );
    }
  }



}
