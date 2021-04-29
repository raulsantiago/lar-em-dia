import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegiaoService } from 'src/app/services/regiao.service';
import { EstadoAtendidoDTO } from 'src/app/dto/regiao/estado-atendidoDTO';
import { MunicipioAtendidoDTO } from 'src/app/dto/regiao/municipio-atendidoDTO';
import { IncluirMunicipioAtendidoDTO } from 'src/app/dto/regiao/incluir-municipio-atendidoDTO';

@Component({
  selector: 'app-gerenciar-municipio',
  templateUrl: './gerenciar-municipio.component.html',
  styleUrls: ['./gerenciar-municipio.component.css']
})
export class GerenciarMunicipioComponent implements OnInit {

  constructor(    
    private router:        Router,
    private route:         ActivatedRoute,
    private regiaoService: RegiaoService
  ) { }

  estado: string;
  municipio: string;
  municipios: Object[];

  listaEstadoAtendidoDTO: EstadoAtendidoDTO[];
  listaMunicipioAtendidoDTO: MunicipioAtendidoDTO[];

  estadoAtendidoDTO: EstadoAtendidoDTO;
  municipioAtendidoDTO: MunicipioAtendidoDTO;
  
  mensagemSucesso: string;  
  errors: String[];

  ngOnInit(): void {
    this.regiaoService.listarMunicipo().subscribe( dado => {
      this.listaMunicipioAtendidoDTO = dado;      
    });
    
  }

  tipoAcaoUf(uf: string){
    this.regiaoService.listaMunicipioIbge(uf).subscribe( dado => {
      this.municipios = dado;      
    });
  }

  inserir(){
    let municipioAtendidoDTO: IncluirMunicipioAtendidoDTO = new IncluirMunicipioAtendidoDTO();
    municipioAtendidoDTO.municipio = this.municipio;
    municipioAtendidoDTO.ativo = true;
    municipioAtendidoDTO.uf = this.estado;    
    this.regiaoService.inserir(municipioAtendidoDTO)
      .subscribe( response => {
        this.mensagemSucesso = 'Cadastro realizado com sucesso!';
        setInterval( res => { this.mensagemSucesso = ''; }, 5000);
        this.errors = null;        
        this.ngOnInit();
      }, errorResponse => {
        this.mensagemSucesso = null;
        this.errors = errorResponse.error.errors;
        setInterval( res => { this.errors = null; }, 5000);
      });
  }

  configUfMunicipio(idMunicipio: number){
    this.router.navigate(['regiao', idMunicipio], {
      relativeTo: this.route['regiao'],
    });
  }

  

}
