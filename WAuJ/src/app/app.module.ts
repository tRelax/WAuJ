import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppComponent } from './app.component';
import { HardwarePriceComponent } from './hardware/hardware-price/hardware-price.component';
import { AppRoutingModule } from './routing/app-routing.module';
import { HardwareComponent } from './hardware/hardware-component/hardware.component';
import { HardwareDetailComponent } from './hardware/hardware-detail/hardware-detail.component';
import { HardwareEditComponent } from './hardware/hardware-edit/hardware-edit.component';
import { ReviewDetailComponent } from './review/review-detail/review-detail.component';

@NgModule({
  declarations: [
    AppComponent,
    HardwareComponent,
    HardwareDetailComponent,
    HardwarePriceComponent,
    HardwareEditComponent,
    ReviewDetailComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
