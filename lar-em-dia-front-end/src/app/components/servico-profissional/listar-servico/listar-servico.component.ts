import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TipoServicoProfissionalDTO } from 'src/app/dto/servico-profissional/tipo-servico-profissionalDTO';
import { TipoServicoProfissionalService } from 'src/app/services/tipo-servico-profissional.service';

@Component({
  selector: 'app-listar-servico',
  templateUrl: './listar-servico.component.html',
  styleUrls: ['./listar-servico.component.css']
})
export class ListarServicoComponent implements OnInit {

  constructor(
    private tipoServicoProfissionalService: TipoServicoProfissionalService,
    private router:                         Router,
    private route:                          ActivatedRoute
    ) { }

  listaTipoServicoProfissionalDTO: TipoServicoProfissionalDTO[];

  ngOnInit(): void {
    let listarServicosPromise: Promise<TipoServicoProfissionalDTO[]> = this.tipoServicoProfissionalService.listar().toPromise();
    Promise.all([listarServicosPromise]).then(data => {
      this.listaTipoServicoProfissionalDTO = data[0];      
    });

  }   

  alterarServicoTipo(id:number) {
    this.router.navigate(['alterarservico', id], {
      relativeTo: this.route['alterarservico'],
    });
  }


  incluirServicoTipo(id:number) {
    this.router.navigate(['cadastrartiposervico', id], {
      relativeTo: this.route['cadastrartiposervico'],
    });
  }
    
  
}
