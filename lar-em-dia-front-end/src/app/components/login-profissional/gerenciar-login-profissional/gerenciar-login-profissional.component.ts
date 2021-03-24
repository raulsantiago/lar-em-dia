import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-gerenciar-login-profissional',
  templateUrl: './gerenciar-login-profissional.component.html',
  styleUrls: ['./gerenciar-login-profissional.component.css']
})
export class GerenciarLoginProfissionalComponent implements OnInit {

  constructor() { }

  nome: string;
  cpf: string;
  email: string;
  celular: string;
  senha: string;
  foto: any; 
  ativo: boolean;

  loginError: boolean;
  mensagemSucesso: boolean = false;
  


  ngOnInit(): void {
  }

  incluir(){
    
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
