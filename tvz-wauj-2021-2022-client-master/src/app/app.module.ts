import {NgModule} from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';

import {AppRoutingModule} from './app-routing.module';
import {AppComponent} from './app.component';
import {HardwareDetailComponent} from './hardware-all/hardware-detail/hardware-detail.component';
import {HTTP_INTERCEPTORS, HttpClientModule, HttpClient} from "@angular/common/http";
import {LoginComponent} from './login/login.component';
import {FormsModule} from "@angular/forms";
import {HomeComponent} from './home/home.component';
import {AuthenticationInterceptor} from "./security/authentication.interceptor";
import {ForbiddenPageComponent} from "./forbidden-page/forbidden-page.component";
import {PageNotFoundComponent} from "./page-not-found/page-not-found.component";
import { HardwaresComponent } from './hardware-all/hardwares/hardwares.component';
import { HardwareNewComponent } from './hardware-all/hardware-new/hardware-new.component';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';
import { NavbarLanguageComponent } from './navbar-language/navbar-language.component';

export function HttpLoaderFactory(http: HttpClient){
  return new TranslateHttpLoader(http, './assets/i18n/', '.json');
}

@NgModule({
  declarations: [
    AppComponent,
    HardwaresComponent,
    HardwareDetailComponent,
    HardwareNewComponent,
    LoginComponent,
    HomeComponent,
    ForbiddenPageComponent,
    PageNotFoundComponent,
    NavbarLanguageComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule,
    TranslateModule.forRoot(
      {
        loader: {
          provide: TranslateLoader,
          useFactory: HttpLoaderFactory,
          deps: [HttpClient]
        },
        defaultLanguage: 'hr'
      }
    )
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthenticationInterceptor,
      multi: true
    },
  ],
  bootstrap: [AppComponent]
})
export class AppModule {
}
