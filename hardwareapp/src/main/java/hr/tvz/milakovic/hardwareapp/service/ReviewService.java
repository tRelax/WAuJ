package hr.tvz.milakovic.hardwareapp.service;

import hr.tvz.milakovic.hardwareapp.entity.ReviewDTO;

import java.util.List;

public interface ReviewService {

    List<ReviewDTO> findAll();
    List<ReviewDTO> findAllByHardwareCode(String code);
}
