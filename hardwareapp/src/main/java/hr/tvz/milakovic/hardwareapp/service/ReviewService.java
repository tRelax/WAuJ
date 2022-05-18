package hr.tvz.milakovic.hardwareapp.service;

import hr.tvz.milakovic.hardwareapp.DTO.ReviewDTO;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    List<ReviewDTO> findAll();
    List<ReviewDTO> findAllByHardwareCode(String code);
    Optional<ReviewDTO> findById(Long id);
}
