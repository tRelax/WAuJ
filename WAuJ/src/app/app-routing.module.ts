import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HardwareComponent } from './hardware-component/hardware.component';
import { HardwareDetailComponent } from './hardware-detail/hardware-detail.component';
import { HardwareEditComponent } from './hardware-edit/hardware-edit.component';

export const routes: Routes = [
  { path: 'hardwares', component: HardwareComponent },
  { path: 'hardwares/detail/:code', component: HardwareDetailComponent },
  { path: 'hardwares/edit/:code', component: HardwareEditComponent }
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
