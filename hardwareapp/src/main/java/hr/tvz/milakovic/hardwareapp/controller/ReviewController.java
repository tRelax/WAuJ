package hr.tvz.milakovic.hardwareapp.controller;

import hr.tvz.milakovic.hardwareapp.entity.ReviewDTO;
import hr.tvz.milakovic.hardwareapp.service.ReviewService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("review")
@CrossOrigin(origins = "http://localhost:4200")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @GetMapping
    public List<ReviewDTO> getAllReviews(){
        return reviewService.findAll();
    }

    @GetMapping("/{code}")
    public List<ReviewDTO> getAllReviewsByHardwareCode(@PathVariable String code){
        return reviewService.findAllByHardwareCode(code);
    }

}