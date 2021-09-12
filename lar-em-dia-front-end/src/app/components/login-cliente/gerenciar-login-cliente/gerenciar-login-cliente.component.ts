import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { GerenciarClienteDTO } from 'src/app/dto/login-cliente/gerenciar-clienteDTO';
import { IncluirLoginClienteDTO } from 'src/app/dto/login-cliente/incluir-login-clienteDTO';
import { EstadoAtendidoDTO } from 'src/app/dto/regiao/estado-atendidoDTO';
import { MunicipioAtendidoDTO } from 'src/app/dto/regiao/municipio-atendidoDTO';
import { AuthService } from 'src/app/services/auth.service';
import { LoginClienteService } from 'src/app/services/login-cliente.service';
import { LoginProfissionalService } from 'src/app/services/login-profissional.service';
import { RegiaoService } from 'src/app/services/regiao.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-gerenciar-login-cliente',
  templateUrl: './gerenciar-login-cliente.component.html',
  styleUrls: ['./gerenciar-login-cliente.component.css']
})
export class GerenciarLoginClienteComponent implements OnInit {

  constructor(
    private loginClienteService:      LoginClienteService,
    private authService:              AuthService,
    private router:                   Router,
    private regiaoService:            RegiaoService,
    private loginProfissionalService: LoginProfissionalService,    
    private messageService:           MessageService
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
  estado: number;
  municipio: number;
  referencia: string;
  foto: any = null;
  ativo: boolean = true;

  gerenciarClienteDTO: GerenciarClienteDTO
  listaEstadoAtendidoDTO: EstadoAtendidoDTO[];
  listaMunicipioAtendidoDTO: MunicipioAtendidoDTO[];

  usuarioLogado:   string;
  mensagemSucesso: string;  
  errors:          string[];

  ngOnInit(): void {
    this.usuarioLogado = this.authService.getUsuarioAutenticado();
    this.loginClienteService.consultarEmail(this.usuarioLogado).subscribe( dado =>{
      this.gerenciarClienteDTO = dado; 
      this.estado = this.gerenciarClienteDTO?.estadoAtendidoDTO?.idEstado;
      this.municipio = this.gerenciarClienteDTO?.municipioAtendidoDTO?.idMunicipio;
      this.regiaoService.listarMunicipioAtivoPorUfAtivo(this.gerenciarClienteDTO?.estadoAtendidoDTO?.idEstado).subscribe( dado =>{
        this.listaMunicipioAtendidoDTO = dado;        
      });
    });
    
    this.regiaoService.listarUfAtivo().subscribe( dado => {
      this.listaEstadoAtendidoDTO = dado;
    });
    
  }

  tipoAcaoUf(idEstado: number){
    this.regiaoService.listarMunicipioAtivoPorUfAtivo(idEstado).subscribe( dado =>{
      this.listaMunicipioAtendidoDTO = dado;      
    });
  }

  alterar(){
    let incluirLoginClienteDTO: IncluirLoginClienteDTO = new IncluirLoginClienteDTO();
    incluirLoginClienteDTO.nome = this.gerenciarClienteDTO.nome;
    incluirLoginClienteDTO.cpf = this.gerenciarClienteDTO.cpf;
    incluirLoginClienteDTO.email = this.gerenciarClienteDTO.email;
    incluirLoginClienteDTO.celular = this.gerenciarClienteDTO.celular;
    incluirLoginClienteDTO.senha = this.gerenciarClienteDTO.senha;
    incluirLoginClienteDTO.endereco = this.gerenciarClienteDTO.endereco;
    incluirLoginClienteDTO.numero = this.gerenciarClienteDTO.numero;
    incluirLoginClienteDTO.bairro = this.gerenciarClienteDTO.bairro;
    incluirLoginClienteDTO.complemento = this.gerenciarClienteDTO.complemento;
    incluirLoginClienteDTO.referencia = this.gerenciarClienteDTO.referencia;
    incluirLoginClienteDTO.ativo = this.gerenciarClienteDTO.ativo;
    incluirLoginClienteDTO.idEstado = this.estado;
    incluirLoginClienteDTO.idMunicipio = this.municipio;
    this.loginClienteService.alterar(incluirLoginClienteDTO, this.gerenciarClienteDTO.idCliente).subscribe( response => {
      this.messageService.add({severity:'success', summary: 'Sucesso', detail: 'Cadastro alterado!' , life: 1500 });
      }, errorResponse => {        
        this.errors = errorResponse.error.errors;
        this.errors.forEach(response => {
          this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 1500 });
        });
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
  

  solicitarServico(){
    this.loginProfissionalService.consultarAtivo(true).subscribe( response => {      
      if(response){
        this.router.navigate(['/solicitar']);
      }
    }, errorResponse => {      
      this.errors = errorResponse.error.errors;
      this.errors.forEach(response => {
        this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 1500 });
      });
    });
    
  }
  
  



}
