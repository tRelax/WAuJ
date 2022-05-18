import {Component} from '@angular/core';
import {Router} from "@angular/router";
import {Location} from "@angular/common";
import { HardwareService } from '../hardware.service';
import { Hardware } from '../hardware';
@Component({
  selector: 'app-hardware-new',
  templateUrl: './hardware-new.component.html',
  styleUrls: ['./hardware-new.component.css']
})
export class HardwareNewComponent {
  constructor(
    private hardwareService: HardwareService,
    private router: Router,
    private location: Location
  ) {
  }

  add(code: string, name: string, type: string, available: number, price: number): void {
    code = code.trim();
    name = name.trim();
    type = type.trim();
    if (!code || !name || !type) {
      return;
    }

    this.hardwareService.addHardware({code, name, type, available, price} as Hardware)
      .subscribe(
        () => {
          this.router.navigate(['/hardwares']).then();
        }
      );
  }

  goBack(): void {
    this.location.back();
  }

}
