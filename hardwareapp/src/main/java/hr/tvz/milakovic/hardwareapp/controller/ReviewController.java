package hr.tvz.milakovic.hardwareapp.controller;

import hr.tvz.milakovic.hardwareapp.DTO.ReviewDTO;
import hr.tvz.milakovic.hardwareapp.service.ReviewService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_DELETER"})
    public List<ReviewDTO> getAllReviews(){
        return reviewService.findAll();
    }

    @GetMapping(params= "code")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_DELETER"})
    public List<ReviewDTO> getAllReviewsByHardwareCode(@RequestParam String code){
        return reviewService.findAllByHardwareCode(code);
    }

    @GetMapping(params = "id")
    @Secured({"ROLE_ADMIN", "ROLE_USER", "ROLE_DELETER"})
    public ResponseEntity<ReviewDTO> getReviewById(@RequestParam Long id){
        return reviewService.findById(id).map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}