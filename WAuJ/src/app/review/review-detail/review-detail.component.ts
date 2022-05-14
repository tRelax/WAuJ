import { Component, Input, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { switchMap } from 'rxjs';
import { Hardware } from 'src/app/hardware/hardware';
import { HardwareService } from 'src/app/hardware/hardware.service';
import { Review } from '../review';
import { ReviewService } from '../review.service';

@Component({
  selector: 'app-review-detail',
  templateUrl: './review-detail.component.html',
  styleUrls: ['./review-detail.component.css']
})
export class ReviewDetailComponent implements OnInit {

  review: Review;
  hardwareFromReview: Hardware;

  constructor(
    private route: ActivatedRoute,
    private hardwareService: HardwareService,
    private reviewService: ReviewService) { }

  ngOnInit(): void {
    this.route.paramMap.pipe(
      switchMap(params => {
        const id = params.get('id');
        return this.reviewService.getReviewById(+id);
      }
      )
    ).subscribe((review: Review) => {
      this.review = review;
      this.hardwareService.getHardwareById(review.hardwareId).subscribe(
        hardware => this.hardwareFromReview = hardware
      );
    });
  }

}
