import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { GerenciarClienteDTO } from 'src/app/dto/login-cliente/gerenciar-clienteDTO';
import { EstadoAtendidoDTO } from 'src/app/dto/regiao/estado-atendidoDTO';
import { MunicipioAtendidoDTO } from 'src/app/dto/regiao/municipio-atendidoDTO';
import { AuthService } from 'src/app/services/auth.service';
import { LoginClienteService } from 'src/app/services/login-cliente.service';
import { RegiaoService } from 'src/app/services/regiao.service';

@Component({
  selector: 'app-gerenciar-login-cliente',
  templateUrl: './gerenciar-login-cliente.component.html',
  styleUrls: ['./gerenciar-login-cliente.component.css']
})
export class GerenciarLoginClienteComponent implements OnInit {

  constructor(
    private loginClienteService: LoginClienteService,
    private authService: AuthService,
    private router: Router,
    private regiaoService: RegiaoService
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
  estado: EstadoAtendidoDTO;
  municipio: MunicipioAtendidoDTO;
  referencia: string;
  foto: any = null;
  ativo: boolean = true;

  gerenciarClienteDTO: GerenciarClienteDTO
  estadoAtendidoDTO: EstadoAtendidoDTO[];
  municipioAtendidoDTO: MunicipioAtendidoDTO[];

  usuarioLogado: string;
  mensagemSucesso: string;  
  errors: String[];

  exibeSelection: boolean = true;
 

  ngOnInit(): void {    
    this.exibeSelection = true;
    this.usuarioLogado = this.authService.getUsuarioAutenticado();
    this.loginClienteService.consultarEmail(this.usuarioLogado).subscribe( dado =>{
      this.gerenciarClienteDTO = dado;      
    });    
    
  }


  onClick(event){
    this.exibeSelection = false;
    this.regiaoService.listarUfAtivo().subscribe( dado => {
      this.estadoAtendidoDTO = dado;
    });
    
  }

  tipoAcaoUf(idEstado: number){
    this.regiaoService.listarMunicipioAtivoPorUfAtivo(idEstado).subscribe( dado =>{
      this.municipioAtendidoDTO = dado;
    });
  }

  alterar(){
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
