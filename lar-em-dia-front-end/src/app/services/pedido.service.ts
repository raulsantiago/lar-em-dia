import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { AlterarPedidoContratadoDTO } from '../dto/pedido/alterar-pedido-contratadoDTO';
import { IncluirPedidoContratadoDTO } from '../dto/pedido/incluir-pedido-contratadoDTO';
import { ListaPedidosClienteDTO } from '../dto/pedido/lista-pedidos-clienteDTO';
import { ListaPedidosProfissionalDTO } from '../dto/pedido/lista-pedidos-profissionalDTO';
import { ListaGraficoDTO } from '../dto/pedido/listaGraficoDTO';
import { ListaLucroHoraDTO } from '../dto/pedido/listaLucroHoraDTO';
import { PedidoContratadoDTO } from '../dto/pedido/pedido-contratadoDTO';


@Injectable({
  providedIn: 'root'
})
export class PedidoService {

  apiURL: string = environment.api + "/pedido";

  constructor(protected http: HttpClient) { }

  consultar(id: number): Observable<PedidoContratadoDTO>{
    return this.http.get<PedidoContratadoDTO>(`${this.apiURL}/${id}`);
  }

  pedidosPorIdCliente(id: number): Observable<ListaPedidosClienteDTO[]>{
    return this.http.get<ListaPedidosClienteDTO[]>(`${this.apiURL}/${id}/cliente`);
  }

  listaPedidosViewProfissional(): Observable<ListaPedidosProfissionalDTO[]>{
    return this.http.get<ListaPedidosProfissionalDTO[]>(`${this.apiURL}/profissional`);
  }

  listaPedidosGrafico(periodoInicial: string, periodoFinal: string): Observable<ListaGraficoDTO[]>{
    return this.http.get<ListaGraficoDTO[]>(`${this.apiURL}/${periodoInicial}/${periodoFinal}/grafico`);
  }

  listaPedidosLucroHora(periodoInicial: string, periodoFinal: string): Observable<ListaLucroHoraDTO[]>{
    return this.http.get<ListaLucroHoraDTO[]>(`${this.apiURL}/${periodoInicial}/${periodoFinal}/lucro`);
  }

  listaTodosPedidosLucroHora(): Observable<ListaLucroHoraDTO[]>{
    return this.http.get<ListaLucroHoraDTO[]>(`${this.apiURL}/lucro`);
  }

  inserir(incluirPedidoContratadoDTO: IncluirPedidoContratadoDTO): Observable<IncluirPedidoContratadoDTO>{
    return this.http.post<IncluirPedidoContratadoDTO>(`${this.apiURL}`, incluirPedidoContratadoDTO);
  }

  excluir(id: number): Observable<PedidoContratadoDTO>{
    return this.http.delete<PedidoContratadoDTO>(`${this.apiURL}/${id}`);
  }

  excluirProf(id: number): Observable<PedidoContratadoDTO>{
    return this.http.delete<PedidoContratadoDTO>(`${this.apiURL}/${id}/prof`);
  }

  alterar(id: number, pedidoDTO: AlterarPedidoContratadoDTO): Observable<AlterarPedidoContratadoDTO>{
    return this.http.patch<AlterarPedidoContratadoDTO>(`${this.apiURL}/${id}`, pedidoDTO)
  }



}
