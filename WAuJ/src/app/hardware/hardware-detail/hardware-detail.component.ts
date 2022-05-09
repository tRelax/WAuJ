import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { Review } from 'src/app/review/review';
import { ReviewService } from 'src/app/review/review.service';
import { Hardware } from '../hardware';
import { HardwareService } from '../hardware.service';

@Component({
  selector: 'app-hardware-detail',
  templateUrl: './hardware-detail.component.html',
  styleUrls: ['./hardware-detail.component.css']
})
export class HardwareDetailComponent implements OnInit {

  @Input() hardware: Hardware;
  reviews: Review[];

  constructor(
    private route: ActivatedRoute,
    private hardwareService: HardwareService,
    private reviewService: ReviewService
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
      this.reviewService.getReviewsByHardwareCode(hardware.code).subscribe(
        reviews => this.reviews = reviews 
      )
    });
  }

}
