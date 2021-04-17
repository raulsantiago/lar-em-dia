import { Component, OnInit } from '@angular/core';
import { TipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/tipo-servico-profissionalDTO';
import { TipoServicoProfissionalService } from 'src/app/services/tipo-servico-profissional.service';

@Component({
  selector: 'app-listar-servico',
  templateUrl: './listar-servico.component.html',
  styleUrls: ['./listar-servico.component.css']
})
export class ListarServicoComponent implements OnInit {

  constructor(private tipoServicoProfissionalService: TipoServicoProfissionalService) { }
 
  listaTipoServicoProfissionalDTO: TipoServicoProfissionalDTO[];

  ngOnInit(): void {
    let listarServicosPromise: Promise<TipoServicoProfissionalDTO> = this.tipoServicoProfissionalService.listar().toPromise();

    Promise.all([listarServicosPromise]).then(( data: TipoServicoProfissionalDTO[]) =>{      
      this.listaTipoServicoProfissionalDTO = data[0];
    });

  }   
    
  
}
