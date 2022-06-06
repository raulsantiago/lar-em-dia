import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit { 

  constructor() {}

  usuarioLogado: string;
  exibir: boolean = true;
  idSetTimeout: any;
  
  ngOnInit(): void {    
    this.idSetTimeout = setTimeout( res => { this.exibir = false; 
    }, 4500);
  }

  // sportSetTimeout(){
  //   clearInterval(this.idSetTimeout);
  // }  

}
