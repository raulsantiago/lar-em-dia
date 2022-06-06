import { Component, OnInit } from '@angular/core';
import { PrimeNGConfig } from 'primeng/api';
import { TranslateService } from '@ngx-translate/core';
import { AuthService } from './services/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {

  title = 'lar-em-dia-front-end';

  supportLanguages = ['pt'];  

  constructor(
    private config: PrimeNGConfig,
    private translateService: TranslateService,
    private authService: AuthService
    )
    {
      this.translateService.addLangs(this.supportLanguages);
      this.translateService.setDefaultLang('pt');
    }
      
  ngOnInit(): void {
    this.translateService.setDefaultLang('pt');
    this.translate('pt');    
  }

  translate(lang: string) {
    this.translateService.use(lang);
    this.translateService.get('calendario').subscribe(res => this.config.setTranslation(res));
  }

  showContentHeader(){
    return {
      'content-header': this.authService.isAuthenticated()
    }
  }
  
}
