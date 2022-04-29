package hr.tvz.milakovic.hardwareapp.repository;

import hr.tvz.milakovic.hardwareapp.entity.Hardware;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface HardwareRepository{

    List<Hardware> findAll();
    Optional<Hardware> findByCode(String code);
    Optional<Hardware> save(Hardware hardware);
    Optional<Hardware> update(String code, Hardware updatedHardware);
    void deleteByCode(String code);

    List<Hardware> findBetweenPrices(Double min, Double max);
    List<Hardware> findWithString(String pattern);

}
