import { Component, OnInit } from '@angular/core';
import { ListaGraficoDTO } from 'src/app/dto/pedido/listaGraficoDTO';
import { LoginClienteService } from 'src/app/services/login-cliente.service';
import { PedidoService } from 'src/app/services/pedido.service';

@Component({
  selector: 'app-sintetico',
  templateUrl: './sintetico.component.html',
  styleUrls: ['./sintetico.component.css']
})
export class SinteticoComponent implements OnInit {

  graficoData: any;
  options: any;
  quantidadeClientesAtivos: string;
  listaGraficoDTO?: ListaGraficoDTO[];
  exibe: boolean = false;  
  dados?: any[];
  quantitadeServicosTotal: number = 0;
  lucroServicosTotal: number = 0;
  fmtLucroServicosTotal: string;

  dia1: any[] = [];
  dia2: any[] = [];
  dia3: any[] = [];
  dia4: any[] = [];
  dia5: any[] = [];
  dia6: any[] = [];
  dia7: any[] = [];
  dia8: any[] = [];
  dia9: any[] = [];
  dia10: any[] = [];
  dia11: any[] = [];
  dia12: any[] = [];
  dia13: any[] = [];
  dia14: any[] = [];
  dia15: any[] = [];
  dia16: any[] = [];
  dia17: any[] = [];
  dia18: any[] = [];
  dia19: any[] = [];
  dia20: any[] = [];
  dia21: any[] = [];
  dia22: any[] = [];
  dia23: any[] = [];
  dia24: any[] = [];
  dia25: any[] = [];
  dia26: any[] = [];
  dia27: any[] = [];
  dia28: any[] = [];
  dia29: any[] = [];
  dia30: any[] = [];
  dia31: any[] = [];

  lucroDia1: number = 0;
  lucroDia2: number = 0;
  lucroDia3: number = 0;
  lucroDia4: number = 0;
  lucroDia5: number = 0;
  lucroDia6: number = 0;
  lucroDia7: number = 0;
  lucroDia8: number = 0;
  lucroDia9: number = 0;
  lucroDia10: number = 0;
  lucroDia11: number = 0;
  lucroDia12: number = 0;
  lucroDia13: number = 0;
  lucroDia14: number = 0;
  lucroDia15: number = 0;
  lucroDia16: number = 0;
  lucroDia17: number = 0;
  lucroDia18: number = 0;
  lucroDia19: number = 0;
  lucroDia20: number = 0;
  lucroDia21: number = 0;
  lucroDia22: number = 0;
  lucroDia23: number = 0;
  lucroDia24: number = 0;
  lucroDia25: number = 0;
  lucroDia26: number = 0;
  lucroDia27: number = 0;
  lucroDia28: number = 0;
  lucroDia29: number = 0;
  lucroDia30: number = 0;
  lucroDia31: number = 0;

  qtdDia1: number = 0;
  qtdDia2: number = 0;
  qtdDia3: number = 0;
  qtdDia4: number = 0;
  qtdDia5: number = 0;
  qtdDia6: number = 0;
  qtdDia7: number = 0;
  qtdDia8: number = 0;
  qtdDia9: number = 0;
  qtdDia10: number = 0;
  qtdDia11: number = 0;
  qtdDia12: number = 0;
  qtdDia13: number = 0;
  qtdDia14: number = 0;
  qtdDia15: number = 0;
  qtdDia16: number = 0;
  qtdDia17: number = 0;
  qtdDia18: number = 0;
  qtdDia19: number = 0;
  qtdDia20: number = 0;
  qtdDia21: number = 0;
  qtdDia22: number = 0;
  qtdDia23: number = 0;
  qtdDia24: number = 0;
  qtdDia25: number = 0;
  qtdDia26: number = 0;
  qtdDia27: number = 0;
  qtdDia28: number = 0;
  qtdDia29: number = 0;
  qtdDia30: number = 0;
  qtdDia31: number = 0;

  constructor(private loginClienteService: LoginClienteService, private pedidoService: PedidoService) { }

  ngOnInit(): void {
    this.loginClienteService.quantidadeClientesAtivos().subscribe(dado => {
      this.quantidadeClientesAtivos = dado;      
    });

    let pedidosGraficoPromise: Promise<any> = this.pedidoService.listaPedidosGrafico('2021-07-01T00:00:00', '2021-07-31T23:59:00').toPromise();
    Promise.all([pedidosGraficoPromise]).then( (retorno ) => {
      this.listaGraficoDTO = retorno[0] === null ? '' : retorno[0];
      this.quantitadeServicosTotal = this.listaGraficoDTO.length;
      this.dados = Object.values(this.listaGraficoDTO);

      for (const iterator of this.listaGraficoDTO) {
        this.lucroServicosTotal = this.lucroServicosTotal + (iterator.precoContratado - iterator.despesas);
      }
      this.fmtLucroServicosTotal = this.numberToReal(this.lucroServicosTotal);
    });
  
  }
  
  formatDate(data){
    var parts = data.split("-");
    return `${parts[2]}/${parts[1]}/${parts[0]}`;
  }

  numberToReal(numero) {
    var numero = numero.toFixed(2).split('.');
    numero[0] = "R$ " + numero[0].split(/(?=(?:...)*$)/).join('.');
    return numero.join(',');
  }

  grafico(){ 
    this.exibe = true;
    for (let index = 0; index <  this.dados?.length; index++) {

      switch (this.dados[index]?.dataFim.substring(8,10)) {
        case '01': this.dia1.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );                   
            break;
        case '02': this.dia2.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '03': this.dia3.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '04': this.dia4.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '05': this.dia5.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '06': this.dia6.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '07': this.dia7.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '08': this.dia8.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '09': this.dia9.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '10': this.dia10.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '11': this.dia11.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '12': this.dia12.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '13': this.dia13.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '14': this.dia14.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '15': this.dia15.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '16': this.dia16.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '17': this.dia17.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '18': this.dia18.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '19': this.dia19.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '20': this.dia20.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '21': this.dia21.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '22': this.dia22.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '23': this.dia23.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '24': this.dia24.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '25': this.dia25.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '26': this.dia26.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '27': this.dia27.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '28': this.dia28.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '29': this.dia29.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '30': this.dia30.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  this.dados[index]?.precoContratado - this.dados[index]?.despesas );
            break;
        case '31': this.dia31.push(this.formatDate(this.dados[index]?.dataFim.substring(0,10)),  (this.dados[index]?.precoContratado - this.dados[index]?.despesas) );
            break;
        
      }      
      
    }

    // fazer o calculo do lucro por dia
    for (const iterator of this.dia1) {
         this.lucroDia1 = this.lucroDia1 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia2) {
        this.lucroDia2 = this.lucroDia2 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia3) {
        this.lucroDia3 = this.lucroDia3 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }    
    for (const iterator of this.dia4) {
        this.lucroDia4 = this.lucroDia4 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia5) {
        this.lucroDia5 = this.lucroDia5 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia6) {
        this.lucroDia6 = this.lucroDia6 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia7) {
        this.lucroDia7 = this.lucroDia7 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia8) {
        this.lucroDia8 = this.lucroDia8 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia9) {
        this.lucroDia9 = this.lucroDia9 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia10) {
        this.lucroDia10 = this.lucroDia10 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia11) {
        this.lucroDia11 = this.lucroDia11 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia12) {
        this.lucroDia12 = this.lucroDia12 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia13) {
        this.lucroDia13 = this.lucroDia13 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia14) {
        this.lucroDia14 = this.lucroDia14 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia15) {
        this.lucroDia15 = this.lucroDia15 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia16) {
        this.lucroDia16 = this.lucroDia16 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia17) {
        this.lucroDia17 = this.lucroDia17 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia18) {
        this.lucroDia18 = this.lucroDia18 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia19) {
        this.lucroDia19 = this.lucroDia19 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia20) {
        this.lucroDia20 = this.lucroDia20 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia21) {
        this.lucroDia21 = this.lucroDia21 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia22) {
        this.lucroDia22 = this.lucroDia22 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia23) {
        this.lucroDia23 = this.lucroDia23 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia24) {
        this.lucroDia24 = this.lucroDia24 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia25) {
        this.lucroDia25 = this.lucroDia25 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia26) {
        this.lucroDia26 = this.lucroDia26 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia27) {
        this.lucroDia27 = this.lucroDia27 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia28) {
        this.lucroDia28 = this.lucroDia28 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia29) {
        this.lucroDia29 = this.lucroDia29 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia30) {
        this.lucroDia30 = this.lucroDia30 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }
    for (const iterator of this.dia31) {
        this.lucroDia31 = this.lucroDia31 + (typeof(iterator) === typeof(0) ? iterator : 0);         
    }

    // calculando a quantidade de serviços por dia    
    this.qtdDia1 = this.dia1.length == undefined ? 0 : this.dia1.length / 2;
    this.qtdDia2 = this.dia2.length == undefined ? 0 : this.dia2.length / 2;
    this.qtdDia3 = this.dia3.length == undefined ? 0 : this.dia3.length / 2;
    this.qtdDia4 = this.dia4.length == undefined ? 0 : this.dia4.length / 2;
    this.qtdDia5 = this.dia5.length == undefined ? 0 : this.dia5.length / 2;
    this.qtdDia6 = this.dia6.length == undefined ? 0 : this.dia6.length / 2;
    this.qtdDia7 = this.dia7.length == undefined ? 0 : this.dia7.length / 2;
    this.qtdDia8 = this.dia8.length == undefined ? 0 : this.dia8.length / 2;
    this.qtdDia9 = this.dia9.length == undefined ? 0 : this.dia9.length / 2;
    this.qtdDia10 = this.dia10.length == undefined ? 0 : this.dia10.length / 2;
    this.qtdDia11 = this.dia11.length == undefined ? 0 : this.dia11.length / 2;
    this.qtdDia12 = this.dia12.length == undefined ? 0 : this.dia12.length / 2;
    this.qtdDia13 = this.dia13.length == undefined ? 0 : this.dia13.length / 2;
    this.qtdDia14 = this.dia14.length == undefined ? 0 : this.dia14.length / 2;
    this.qtdDia15 = this.dia15.length == undefined ? 0 : this.dia15.length / 2;
    this.qtdDia16 = this.dia16.length == undefined ? 0 : this.dia16.length / 2;
    this.qtdDia17 = this.dia17.length == undefined ? 0 : this.dia17.length / 2;
    this.qtdDia18 = this.dia18.length == undefined ? 0 : this.dia18.length / 2;
    this.qtdDia19 = this.dia19.length == undefined ? 0 : this.dia19.length / 2;
    this.qtdDia20 = this.dia20.length == undefined ? 0 : this.dia20.length / 2;
    this.qtdDia21 = this.dia21.length == undefined ? 0 : this.dia21.length / 2;
    this.qtdDia22 = this.dia22.length == undefined ? 0 : this.dia22.length / 2;
    this.qtdDia23 = this.dia23.length == undefined ? 0 : this.dia23.length / 2;
    this.qtdDia24 = this.dia24.length == undefined ? 0 : this.dia24.length / 2;
    this.qtdDia25 = this.dia25.length == undefined ? 0 : this.dia25.length / 2;
    this.qtdDia26 = this.dia26.length == undefined ? 0 : this.dia26.length / 2;
    this.qtdDia27 = this.dia27.length == undefined ? 0 : this.dia27.length / 2;
    this.qtdDia28 = this.dia28.length == undefined ? 0 : this.dia28.length / 2;
    this.qtdDia29 = this.dia29.length == undefined ? 0 : this.dia29.length / 2;    
    this.qtdDia30 = this.dia30.length == undefined ? 0 : this.dia30.length / 2;
    this.qtdDia31 = this.dia31.length == undefined ? 0 : this.dia31.length / 2;
    ;

    this.options = {
        title: {
            display: true,
            text: 'Lucro e Quantidade de Serciços por Dia',
            fontSize: 20
        },
        tooltips: {
            mode: 'index',
            intersect: false
        },
        responsive: true,
        scales: {
            xAxes: [{
                stacked: true,
            }],
            yAxes: [{
                stacked: true
            }]
        }
    };

    this.graficoData = {        
        labels: [
        this.dia1[0] == undefined ? 'Dia 01 zerado' : this.dia1[0], this.dia2[0] == undefined ? 'Dia 02 zerado' : this.dia2[0],
        this.dia3[0] == undefined ? 'Dia 03 zerado' : this.dia3[0], this.dia4[0] == undefined ? 'Dia 04 zerado' : this.dia4[0],
        this.dia5[0] == undefined ? 'Dia 05 zerado' : this.dia5[0], this.dia6[0] == undefined ? 'Dia 06 zerado' : this.dia6[0], 
        this.dia7[0] == undefined ? 'Dia 07 zerado' : this.dia7[0], this.dia8[0] == undefined ? 'Dia 08 zerado' : this.dia8[0], 
        this.dia9[0] == undefined ? 'Dia 09 zerado' : this.dia9[0], this.dia10[0] == undefined ? 'Dia 10 zerado' : this.dia10[0],
        this.dia11[0] == undefined ? 'Dia 11 zerado' : this.dia11[0], this.dia12[0] == undefined ? 'Dia 12 zerado' : this.dia12[0],
        this.dia13[0] == undefined ? 'Dia 13 zerado' : this.dia13[0], this.dia14[0] == undefined ? 'Dia 14 zerado' : this.dia14[0], 
        this.dia15[0] == undefined ? 'Dia 15 zerado' : this.dia15[0], this.dia16[0] == undefined ? 'Dia 16 zerado' : this.dia16[0], 
        this.dia17[0] == undefined ? 'Dia 17 zerado' : this.dia17[0], this.dia18[0] == undefined ? 'Dia 18 zerado' : this.dia18[0], 
        this.dia19[0] == undefined ? 'Dia 19 zerado' : this.dia19[0], this.dia20[0] == undefined ? 'Dia 20 zerado' : this.dia20[0], 
        this.dia21[0] == undefined ? 'Dia 21 zerado' : this.dia21[0], this.dia22[0] == undefined ? 'Dia 22 zerado' : this.dia22[0], 
        this.dia23[0] == undefined ? 'Dia 23 zerado' : this.dia23[0], this.dia24[0] == undefined ? 'Dia 24 zerado' : this.dia24[0], 
        this.dia25[0] == undefined ? 'Dia 25 zerado' : this.dia25[0], this.dia26[0] == undefined ? 'Dia 26 zerado' : this.dia26[0], 
        this.dia27[0] == undefined ? 'Dia 27 zerado' : this.dia27[0], this.dia28[0] == undefined ? 'Dia 28 zerado' : this.dia28[0], 
        this.dia29[0] == undefined ? 'Dia 29 zerado' : this.dia29[0], this.dia30[0] == undefined ? 'Dia 30 zerado' : this.dia30[0], 
        this.dia31[0]  == undefined ? 'Dia 31 zerado' : this.dia31[0]
        ],
        datasets: [          
            {
                type: 'horizontalBar',
                label: 'Lucro',
                backgroundColor: '#66BB6A',
                data: [
                    this.lucroDia1, this.lucroDia2, this.lucroDia3, this.lucroDia4, this.lucroDia5,
                    this.lucroDia6, this.lucroDia7, this.lucroDia8 , this.lucroDia9, this.lucroDia10,
                    this.lucroDia11, this.lucroDia12, this.lucroDia13, this.lucroDia14, this.lucroDia15,
                    this.lucroDia16, this.lucroDia17, this.lucroDia18, this.lucroDia19, this.lucroDia20,
                    this.lucroDia21, this.lucroDia22, this.lucroDia23, this.lucroDia24, this.lucroDia25,
                    this.lucroDia26, this.lucroDia27, this.lucroDia28, this.lucroDia29, this.lucroDia30,
                    this.lucroDia31
                ]        
            },
            {
                type: 'horizontalBar',
                label: 'Quantidade Serviços',
                backgroundColor: '#cc0000',
                data: [
                    this.qtdDia1, this.qtdDia2, this.qtdDia3, this.qtdDia4, this.qtdDia5,
                    this.qtdDia6, this.qtdDia7, this.qtdDia8, this.qtdDia9, this.qtdDia10,
                    this.qtdDia11, this.qtdDia12, this.qtdDia13, this.qtdDia14, this.qtdDia15,
                    this.qtdDia16, this.qtdDia17, this.qtdDia18, this.qtdDia19, this.qtdDia20,
                    this.qtdDia21, this.qtdDia22, this.qtdDia23, this.qtdDia24, this.qtdDia25,
                    this.qtdDia26, this.qtdDia27, this.qtdDia28, this.qtdDia29, this.qtdDia30,
                    this.qtdDia31
                ]        
            }

        ]
    };

  }

 
}
