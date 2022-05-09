import { Component, Input, OnInit } from '@angular/core';
import { Hardware } from '../hardware';

@Component({
  selector: 'app-hardware-price',
  templateUrl: './hardware-price.component.html',
  styleUrls: ['./hardware-price.component.css']
})
export class HardwarePriceComponent implements OnInit {

  @Input() hardware: Hardware; 

  constructor() { }

  ngOnInit(): void {
  }

}
