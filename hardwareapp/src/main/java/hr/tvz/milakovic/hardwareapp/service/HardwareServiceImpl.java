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
//        Optional<Hardware> temp = hardwareRepository.findByCode(code);
//        if (temp.get().getType().equals(HardwareType.OTHER) && temp.get().getAvailable() > 100){
//            HardwareDTO tempDTO = mapHardwareToDTO(temp.get());
//            tempDTO.setPrice(tempDTO.getPrice() * 0.75);
//            return tempDTO;
//        }
//        return mapHardwareToDTO(temp.get());
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
    public Optional<HardwareDTO> update(String code, HardwareCommand command) {
        return hardwareRepository.update(code, mapCommandToHardware(command)).map(this::mapHardwareToDTO);
    }

    @Override
    public List<HardwareDTO> findBetweenPrices(Double min, Double max) {
        return hardwareRepository.findBetweenPrices(min, max).stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public List<HardwareDTO> findWithString(String pattern) {
        return hardwareRepository.findWithString(pattern).stream().map(this::mapHardwareToDTO).collect(Collectors.toList());
    }

    @Override
    public void deleteHardwareByCode(String code) {
        hardwareRepository.deleteByCode(code);
    }

    public HardwareDTO mapHardwareToDTO(Hardware hardware){
        return new HardwareDTO(hardware.getCode(), hardware.getName(), hardware.getPrice(), hardware.getType(), hardware.getAvailable());
    }

    private Hardware mapCommandToHardware(HardwareCommand command) {
        return new Hardware(command.getCode(), command.getName(), command.getPrice(), command.getType(), command.getAvailable());
    }
}
