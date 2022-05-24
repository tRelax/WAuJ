import { Component, OnInit } from '@angular/core';
import { TranslateService } from '@ngx-translate/core';

@Component({
  selector: 'app-navbar-language',
  templateUrl: './navbar-language.component.html',
  styleUrls: ['./navbar-language.component.css']
})
export class NavbarLanguageComponent implements OnInit {

  currentLanguage: string;

  constructor(
    private translateService: TranslateService,
  ) { }

  ngOnInit(): void {
  }

  languageChange(newLanguage: string) {
    switch(newLanguage){
      case 'hr':
        this.translateService.use('hr');
        this.currentLanguage = 'hr';
        break;
      case 'en':
        this.translateService.use('en');
        this.currentLanguage = 'en';
        break;
      case 'cn':
        this.translateService.use('cn');
        this.currentLanguage = 'cn';
        break;
      case 'it':
        this.translateService.use('it');
        this.currentLanguage = 'it';
        break;
      case 'es':
        this.translateService.use('es');
        this.currentLanguage = 'es';
        break;
      case 'fr':
        this.translateService.use('fr');
        this.currentLanguage = 'fr';
        break;
    }
  }
}
