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

 gerenciarProfissionalDTO: GerenciarProfissionalDTO; 

  usuarioLogado: string;
  mensagemSucesso: string;  
  errors: String[];

  ngOnInit(): void {
    this.usuarioLogado = this.authService.getUsuarioAutenticado();        
    this.loginProfissionalService.consultarEmail(this.usuarioLogado).subscribe( dado =>{
      this.gerenciarProfissionalDTO = dado;
    });

  }

  alterar(){
    this.loginProfissionalService.alterar(this.gerenciarProfissionalDTO.idProfissional, this.gerenciarProfissionalDTO).subscribe( response => {
      this.mensagemSucesso = "Cadastro alterado com sucesso!";
      setInterval( res => { this.mensagemSucesso = ''; }, 5000);
      this.errors = null;
      }, errorResponse => {
        this.mensagemSucesso = null;
        this.errors = errorResponse.error.errors;
        setInterval( res => { this.errors = null }, 5000);
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
      this.loginProfissionalService.addFoto(this.gerenciarProfissionalDTO, formData)
        .subscribe(response => this.ngOnInit() );
    }
  }

}
