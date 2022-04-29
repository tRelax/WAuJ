package hr.tvz.milakovic.hardwareapp.service;

import hr.tvz.milakovic.hardwareapp.command.HardwareCommand;
import hr.tvz.milakovic.hardwareapp.entity.Hardware;
import hr.tvz.milakovic.hardwareapp.entity.HardwareDTO;
import hr.tvz.milakovic.hardwareapp.repository.HardwareRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class HardwareServiceImpl implements HardwareService {

    private final HardwareRepository hardwareRepository;

    public HardwareServiceImpl(HardwareRepository hardwareRepository) {
        this.hardwareRepository = hardwareRepository;
    }

    @Override
    public List<HardwareDTO> findAll() {
        return hardwareRepository.findAll().stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public Optional<HardwareDTO> findByCode(String code) {
        return hardwareRepository.findByCode(code).map(this::mapHardwareToDTO);
    }

//    @Override
//    public List<HardwareDTO> findAllWithPriceLowerThen(Double priceLowerThen) {
//        return hardwareRepository.findAllWithPriceLowerThen(priceLowerThen).stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
//    }

    @Override
    public Optional<HardwareDTO> save(HardwareCommand command) {
        return hardwareRepository.save(mapCommandToHardware(command)).map(this::mapHardwareToDTO);
    }


    @Override
    public Optional<HardwareDTO> update(Long id, HardwareCommand command) {
        return hardwareRepository.update(id, mapCommandToHardware(command)).map(this::mapHardwareToDTO);
    }

    @Override
    public void deleteHardwareByCode(String code) {
        hardwareRepository.deleteByCode(code);
    }

    public HardwareDTO mapHardwareToDTO(Hardware hardware) {
        return new HardwareDTO(hardware.getId(), hardware.getCode(), hardware.getName(), hardware.getPrice(), hardware.getType(), hardware.getAvailable());
    }

    private Hardware mapCommandToHardware(HardwareCommand command) {
        return new Hardware(command.getId(), command.getCode(), command.getName(), command.getPrice(), command.getType(), command.getAvailable());
    }
}
