package hr.tvz.milakovic.hardwareapp.repository;

import hr.tvz.milakovic.hardwareapp.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    List<Review> findAllByHardwareCode(String code);
}
