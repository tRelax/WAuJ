import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Hardware } from '../hardware';
import { HardwareType } from '../hardware-type';
import { HardwareService } from '../hardware.service';

@Component({
  selector: 'app-hardware',
  templateUrl: './hardware.component.html',
  styleUrls: ['./hardware.component.css']
})
export class HardwareComponent implements OnInit {

  hardwares: Hardware[];
  selectedHardware: Hardware;

  constructor(
    private hardwareService: HardwareService,
    private router: Router
    ) { }

  ngOnInit(): void {
    this.getHardwares();
  }

  getHardwares(): void{
    this.hardwareService.getHardwares().subscribe(hardwares => this.hardwares = hardwares);
  }

  onSelect(hardware: Hardware): void {
    this.selectedHardware = hardware;
  }

  add(code: string, name: string, price: number, typeString: string, available: number) : void {
    name = name.trim();
    code = code.trim();

    if (!name || !code || !price || !typeString || !available) { return; }

    if(!(<any>Object).values(HardwareType).includes(typeString)){
      return ;
    }

    var type : HardwareType = (<any>HardwareType)[typeString];

    var id: number = 0;

    this.hardwareService.addHardware({ id, code, name, price, type, available } as Hardware)
    .subscribe(hardware => {
      this.hardwares.push(hardware);
    })
  }

  navigateToDetails(hardware: Hardware) {
    this.router.navigate([`/hardwares/detail/${hardware.code}`]);
  }

  deleteHardware(hardware: Hardware): void {
    this.hardwares = this.hardwares.filter(h => h !== hardware);
    this.hardwareService.deleteHardware(hardware).subscribe();
  }

  editHardware(code: string): void{
    this.router.navigate(['/hardwares/edit', code]);
  }

}
