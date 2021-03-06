package hr.tvz.milakovic.hardwareapp.service;

import hr.tvz.milakovic.hardwareapp.command.HardwareCommand;
import hr.tvz.milakovic.hardwareapp.DTO.HardwareDTO;

import java.util.List;
import java.util.Optional;

public interface HardwareService {
    List<HardwareDTO> findAll();
    Optional<HardwareDTO> findByCode(String code);
    Optional<HardwareDTO> findById(Long id);

//    List<HardwareDTO> findAllWithPriceLowerThen(Double priceLowerThen);

    Optional<HardwareDTO> save(HardwareCommand command);

    Optional<HardwareDTO> update(Long id, HardwareCommand command);

    void deleteHardwareByCode(String code);
}
