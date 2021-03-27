import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GerenciarProfissionalDTO } from 'src/app/dto/login-profissional/gerenciar-profissionalDTO';
import { AuthService } from 'src/app/services/auth.service';
import { LoginProfissionalService } from 'src/app/services/login-profissional.service';

@Component({
  selector: 'app-gerenciar-login-profissional',
  templateUrl: './gerenciar-login-profissional.component.html',
  styleUrls: ['./gerenciar-login-profissional.component.css']
})
export class GerenciarLoginProfissionalComponent implements OnInit {

  constructor(
    private loginProfissionalService: LoginProfissionalService,
    private authService: AuthService,
    private router: Router
    ) { }

  idProfissional: number;
  nome: string;
  cpf: string;
  email: string;
  celular: string;
  senha: string;
  foto: any = null;
  ativo: boolean = true;

 //gerenciarProfissionalDTO: GerenciarProfissionalDTO;

  usuarioLogado: string;
  mensagemSucesso: string;  
  errors: String[];
  

  ngOnInit(): void {
    this.usuarioLogado = this.authService.getUsuarioAutenticado();    
    this.loginProfissionalService.consultarEmail(this.usuarioLogado).subscribe( dado =>{
      console.log(dado);
      this.ativo = dado.ativo;
      this.celular = dado.celular;
      this.cpf = dado.cpf;
      this.email = dado.email;
      this.foto = dado.foto;      
      this.nome = dado.nome;
      this.senha = dado.senha;
      this.idProfissional = dado.idProfissional;
    });
    

  }

  alterar(){
    let gerenciarProfissionalDTO: GerenciarProfissionalDTO = new GerenciarProfissionalDTO();
    console.log(gerenciarProfissionalDTO);
    console.log(this.ativo+"-"+this.idProfissional);    
    gerenciarProfissionalDTO.ativo = this.ativo;
    gerenciarProfissionalDTO.celular = this.celular;
    gerenciarProfissionalDTO.cpf = this.cpf;
    gerenciarProfissionalDTO.email = this.email;
    gerenciarProfissionalDTO.foto = this.foto;
    gerenciarProfissionalDTO.nome = this.nome;
    gerenciarProfissionalDTO.senha = this.senha;
    gerenciarProfissionalDTO.idProfissional = this.idProfissional;
    console.log(gerenciarProfissionalDTO);
    this.loginProfissionalService.alterar(this.idProfissional, gerenciarProfissionalDTO).subscribe( response => {
      console.log(response);
      this.mensagemSucesso = "Cadastro alterado com sucesso!";
            this.errors = []      
      }, errorResponse => {
            this.mensagemSucesso = null;
            this.errors = errorResponse.error.errors;
    })
  }

  logout(){
    this.authService.encerrarSessao();
    this.router.navigate(['']);    
  }


   // uploadFoto(event, loginProfissionalDTO){
  //   const files = event.target.files;
  //   if(files){
  //     const foto = files[0];
  //     const formData: FormData = new FormData();
  //     formData.append("foto", foto);
  //     this.loginProfissionalService.addFoto(loginProfissionalDTO, formData);
  //       //.subscribe(response => this.consultaid());
  //   }
  // }

  

}
