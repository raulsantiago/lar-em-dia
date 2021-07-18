import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { RegiaoService } from 'src/app/services/regiao.service';
import { EstadoAtendidoDTO } from 'src/app/dto/regiao/estado-atendidoDTO';
import { MunicipioAtendidoDTO } from 'src/app/dto/regiao/municipio-atendidoDTO';
import { IncluirMunicipioAtendidoDTO } from 'src/app/dto/regiao/incluir-municipio-atendidoDTO';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-gerenciar-municipio',
  templateUrl: './gerenciar-municipio.component.html',
  styleUrls: ['./gerenciar-municipio.component.css']
})
export class GerenciarMunicipioComponent implements OnInit {

  constructor(    
    private router:         Router,
    private route:          ActivatedRoute,
    private regiaoService:  RegiaoService,
    private messageService: MessageService
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
      this.listaMunicipioAtendidoDTO.forEach((municipio: MunicipioAtendidoDTO) => {
        municipio.ativoFmt = municipio.ativo ? 'Sim' : 'Não';
        municipio.ativoEstadoMuniFmt = municipio.estadoAtendidoDTO.ativo ? 'Sim' : 'Não';
        municipio.ativoClienteFmt = municipio.ativo && municipio.estadoAtendidoDTO.ativo ? 'Sim' : 'Não';
      });
    });

    this.regiaoService.listarUf().subscribe( dado => {
      this.listaEstadoAtendidoDTO = dado;
      this.listaEstadoAtendidoDTO.forEach((estado: EstadoAtendidoDTO) => {
        estado.ativoEstadoFmt = estado.ativo ? 'Sim' : 'Não';
      });
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
        this.messageService.add({severity:'success', summary: 'Sucesso', detail: 'Cadastro realizado!' , life: 2000 });
        this.municipio = '';
        this.estado = 'Selecione';
        setTimeout( res => { this.ngOnInit(); }, 2100);
      }, errorResponse => {        
        this.errors = errorResponse.error.errors;
        this.errors.forEach(response => {
          this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 2000 });
        });
      });
  }

  configUfMunicipio(idMunicipio: number){
    this.router.navigate(['regiao', idMunicipio], {
      relativeTo: this.route['regiao'],
    });
  }

  

}
