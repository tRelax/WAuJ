package hr.tvz.milakovic.hardwareapp.service;

import hr.tvz.milakovic.hardwareapp.entity.HardwareDTO;
import hr.tvz.milakovic.hardwareapp.entity.Review;
import hr.tvz.milakovic.hardwareapp.entity.ReviewDTO;
import hr.tvz.milakovic.hardwareapp.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public ReviewServiceImpl(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Override
    public List<ReviewDTO> findAll() {
        return reviewRepository.findAll().stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findAllByHardwareCode(String code) {
        return reviewRepository.findAllByHardwareCode(code).stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findAllByContentContainingIgnoreCase(String clip) {
        return reviewRepository.findAllByContentContainingIgnoreCase(clip).stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }

    @Override
    public List<ReviewDTO> findAllByScoreBetween(Integer min, Integer max) {
        return reviewRepository.findByScoreBetween(min, max).stream().map(this::mapReviewToDTO).collect(Collectors.toList());
    }
    @Override
    public Optional<ReviewDTO> findById(Long id) {
        return reviewRepository.findById(id).map(this::mapReviewToDTO);
    }

    public ReviewDTO mapReviewToDTO(Review review) {
        return new ReviewDTO(review.getId(), review.getTitle(), review.getContent(), review.getScore(), review.getHardware().getId());
    }
}
