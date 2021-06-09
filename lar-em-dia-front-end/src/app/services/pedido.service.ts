import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { IncluirPedidoContratadoDTO } from '../dto/pedido/incluir-pedido-contratadoDTO';
import { ListaPedidosClienteDTO } from '../dto/pedido/lista-pedidos-clienteDTO';
import { ListaPedidosProfissionalDTO } from '../dto/pedido/lista-pedidos-profissionalDTO';
import { PedidoContratadoDTO } from '../dto/pedido/pedido-contratadoDTO';

@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  apiURL: string = environment.api + "/pedido";

  constructor(protected http: HttpClient) { }

  pedidosPorIdCliente(id: number): Observable<ListaPedidosClienteDTO[]>{
    return this.http.get<ListaPedidosClienteDTO[]>(`${this.apiURL}/${id}/cliente`);
  }

  listaPedidosViewProfissional(): Observable<ListaPedidosProfissionalDTO[]>{
    return this.http.get<ListaPedidosProfissionalDTO[]>(`${this.apiURL}/profissional`);
  }

  inserir(incluirPedidoContratadoDTO: IncluirPedidoContratadoDTO): Observable<IncluirPedidoContratadoDTO>{
    return this.http.post<IncluirPedidoContratadoDTO>(`${this.apiURL}`, incluirPedidoContratadoDTO);
  }

  excluir(id: number): Observable<PedidoContratadoDTO>{
    return this.http.delete<PedidoContratadoDTO>(`${this.apiURL}/${id}`);
  }

}
