import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HardwareComponent } from '../hardware/hardware-component/hardware.component';
import { HardwareDetailComponent } from '../hardware/hardware-detail/hardware-detail.component';
import { HardwareEditComponent } from '../hardware/hardware-edit/hardware-edit.component';
import { ReviewDetailComponent } from '../review/review-detail/review-detail.component';

export const routes: Routes = [
  { path: 'hardwares', component: HardwareComponent },
  { path: 'hardwares/detail/:code', component: HardwareDetailComponent },
  { path: 'hardwares/edit/:code', component: HardwareEditComponent },
  { path: 'review/:id', component: ReviewDetailComponent }
];

@NgModule({
  declarations: [],
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule { }
