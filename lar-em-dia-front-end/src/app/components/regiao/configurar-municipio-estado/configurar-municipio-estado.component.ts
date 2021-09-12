import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlterarEstadoAtendidoDTO } from 'src/app/dto/regiao/alterar-estado-atendidoDTO';
import { AlterarMunicipioAtendidoDTO } from 'src/app/dto/regiao/alterar-municipio-atendidoDTO';
import { MunicipioAtendidoDTO } from 'src/app/dto/regiao/municipio-atendidoDTO';
import { RegiaoService } from 'src/app/services/regiao.service';
import { MessageService } from 'primeng/api';

@Component({
  selector: 'app-configurar-municipio-estado',
  templateUrl: './configurar-municipio-estado.component.html',
  styleUrls: ['./configurar-municipio-estado.component.css']
})
export class ConfigurarMunicipioEstadoComponent implements OnInit {

  constructor(
    private activatedRoute: ActivatedRoute,
    private router:         Router,
    private regiaoService:  RegiaoService,
    private messageService: MessageService    
  ) { }

  ativoUf: boolean;
  ativoMunicipio: boolean;
  idUf: number;
  idMunicipio: number;

  municipioAtendidoDTO: MunicipioAtendidoDTO

  mensagemSucesso: string;  
  errors: String[];


  ngOnInit(): void {
    this.idMunicipio = this.activatedRoute.snapshot.params.id;
    this.regiaoService.consultar(this.idMunicipio).subscribe(data => {
      this.municipioAtendidoDTO = data;
      this.ativoUf = this.municipioAtendidoDTO?.estadoAtendidoDTO?.ativo;
      this.ativoMunicipio = this.municipioAtendidoDTO.ativo;
      this.idUf = this.municipioAtendidoDTO.estadoAtendidoDTO.idEstado;
    });    
  }

  alterar(){
    const estadoAtendidoDTO: AlterarEstadoAtendidoDTO = new AlterarEstadoAtendidoDTO();
    estadoAtendidoDTO.ativo = this.ativoUf;
    estadoAtendidoDTO.uf = this.municipioAtendidoDTO?.estadoAtendidoDTO?.uf;
    this.regiaoService.alterarEstado(estadoAtendidoDTO, this.idUf)
      .subscribe(response => {
        const municipioDTO: AlterarMunicipioAtendidoDTO = new AlterarMunicipioAtendidoDTO();
        municipioDTO.ativo = this.ativoMunicipio;
        municipioDTO.municipio = this.municipioAtendidoDTO?.municipio;
        this.regiaoService.alterarMunicipio(municipioDTO, this.idMunicipio)
          .subscribe(response => {
            this.messageService.add({severity:'success', summary: 'Sucesso', detail: 'Cadastro alterado!' , life: 1500 });
            setTimeout( res => { this.router.navigate(['regiao']); }, 1600);
          },errorResponse => {
            this.errors = errorResponse.error.errors;
            this.errors.forEach(response => {
              this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 1500 });
            });
          });
      }, errorResponse => {
        this.errors = errorResponse.error.errors;
        this.errors.forEach(response => {
          this.messageService.add({severity:'error', summary:'Erro', detail: response.toString(), life: 1500 });
        });
      });
  }

}
