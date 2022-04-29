import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { delay, switchMap } from 'rxjs';
import { Hardware } from '../hardware';
import { HardwareType } from '../hardware-type';
import { HardwareService } from '../hardware.service';

@Component({
  selector: 'app-hardware-edit',
  templateUrl: './hardware-edit.component.html',
  styleUrls: ['./hardware-edit.component.css']
})
export class HardwareEditComponent implements OnInit {

  @Input() hardware: Hardware;
  hardwareTypeString: string;

  constructor(
    private route: ActivatedRoute,
    private router: Router,
    private hardwareService: HardwareService
  ) { }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap(params => {
        const code = params.get('code');
        return this.hardwareService.getHardware(code);
      }
      )
    ).subscribe((hardware: Hardware) => {
      this.hardware = hardware;
      this.hardwareTypeString = HardwareType[this.hardware.type];
    });
  }

  update(code: string, name: string,  price: number, typeString: string, available: number): void {
    name = name.trim();
    code = code.trim();

    if (!name || !code || !price || !typeString || !available) { return; }

    if(!(<any>Object).values(HardwareType).includes(typeString)){
      return ;
    }

    var type : HardwareType = (<any>HardwareType)[typeString];

    this.hardwareService.updateHardware({ code, name, price, type, available } as Hardware).subscribe(
      (hardware: Hardware) => {
        this.hardware = hardware;
        console.log('Uspjesno spremljeni podatci!');
        delay(2000);
        this.router.navigate(['hardwares'])
      },
      () => {
        console.log('Došlo je do pogreške!');
      }
    )
  }

}
