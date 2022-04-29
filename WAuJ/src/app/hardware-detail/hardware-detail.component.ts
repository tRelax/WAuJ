import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { Hardware } from '../hardware';
import { HardwareService } from '../hardware.service';

@Component({
  selector: 'app-hardware-detail',
  templateUrl: './hardware-detail.component.html',
  styleUrls: ['./hardware-detail.component.css']
})
export class HardwareDetailComponent implements OnInit {

  @Input() hardware: Hardware;

  constructor(
    private route: ActivatedRoute,
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
    });
  }

}
