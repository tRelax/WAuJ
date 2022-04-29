package hr.tvz.milakovic.hardwareapp.service;

import hr.tvz.milakovic.hardwareapp.command.HardwareCommand;
import hr.tvz.milakovic.hardwareapp.entity.HardwareDTO;

import java.util.List;
import java.util.Optional;

public interface HardwareService {
    List<HardwareDTO> findAll();
    Optional<HardwareDTO> findByCode(String code);

//    List<HardwareDTO> findAllWithPriceLowerThen(Double priceLowerThen);

    Optional<HardwareDTO> save(HardwareCommand command);

    Optional<HardwareDTO> update(String code, HardwareCommand command);

    List<HardwareDTO> findBetweenPrices(Double min, Double max);

    List<HardwareDTO> findWithString(String pattern);

    void deleteHardwareByCode(String code);
}
