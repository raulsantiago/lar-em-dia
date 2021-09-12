import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  usuarioLogado: string;

  constructor() {}

  exibir: boolean = true;

  ngOnInit(): void {    
    setTimeout( res => { this.exibir = false; }, 4500);
  }

  
}
