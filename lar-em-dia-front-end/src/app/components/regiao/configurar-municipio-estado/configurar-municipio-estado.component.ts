import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AlterarEstadoAtendidoDTO } from 'src/app/dto/regiao/alterar-estado-atendidoDTO';
import { AlterarMunicipioAtendidoDTO } from 'src/app/dto/regiao/alterar-municipio-atendidoDTO';
import { EstadoAtendidoDTO } from 'src/app/dto/regiao/estado-atendidoDTO';
import { MunicipioAtendidoDTO } from 'src/app/dto/regiao/municipio-atendidoDTO';
import { RegiaoService } from 'src/app/services/regiao.service';

@Component({
  selector: 'app-configurar-municipio-estado',
  templateUrl: './configurar-municipio-estado.component.html',
  styleUrls: ['./configurar-municipio-estado.component.css']
})
export class ConfigurarMunicipioEstadoComponent implements OnInit {

  constructor(
    private activatedRoute: ActivatedRoute,
    private router:         Router,
    private regiaoService:  RegiaoService    
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
            this.mensagemSucesso = "Cadastro alterado com sucesso!";
            setInterval( res => { this.mensagemSucesso = ''; }, 5000);
            this.errors = null;
            this.router.navigate(['regiao']);
          },errorResponse => {
            this.mensagemSucesso = null;
            this.errors = errorResponse.error.errors;
            setInterval( res => { this.errors = null; }, 5000);
          });
      }, errorResponse => {
        this.mensagemSucesso = null;
        this.errors = errorResponse.error.errors;
        setInterval( res => { this.errors = null; }, 5000);
      });

  }

}
