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
    private translateService: TranslateService
  ) { }

  ngOnInit(): void {
    this.setCurrentLanguageDropdownValue();
  }

  setCurrentLanguageDropdownValue() {
    if (this.translateService.currentLang === 'hr') {
      this.translateService.get('language.croatian').subscribe(language => this.currentLanguage = language);
    } else if (this.translateService.currentLang === 'en') {
      this.translateService.get('language.english').subscribe(language => this.currentLanguage = language);
    } else {
      throw Error('Unknown current language!');
    }
  }

  onLanguageChange(newLanguage: string) {
    this.translateService.use(newLanguage).subscribe(
      languageSwitched => this.setCurrentLanguageDropdownValue()
    );
  }
}
