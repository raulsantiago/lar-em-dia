import { Component, OnInit } from '@angular/core';
import { GerenciarProfissionalDTO } from 'src/app/dto/login-profissional/gerenciar-profissionalDTO';
import { AuthService } from 'src/app/services/auth.service';
import { LoginProfissionalService } from 'src/app/services/login-profissional.service';
import { MessageService } from 'primeng/api';
@Component({
  selector: 'app-gerenciar-login-profissional',
  templateUrl: './gerenciar-login-profissional.component.html',
  styleUrls: ['./gerenciar-login-profissional.component.css']
})
export class GerenciarLoginProfissionalComponent implements OnInit {

  constructor(
    private loginProfissionalService: LoginProfissionalService,
    private authService:              AuthService,
    private messageService:           MessageService
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
      this.messageService.add({severity:'success', summary: 'Sucesso', detail: 'Cadastro alterado!' , life: 5000 });
      }, errorResponse => {
        this.errors = errorResponse.error.errors;
        this.errors.forEach(response => {
          this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 5000 });
        });
    })
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
