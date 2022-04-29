package hr.tvz.milakovic.hardwareapp.repositoryMOCK;

import hr.tvz.milakovic.hardwareapp.entity.Hardware;
import hr.tvz.milakovic.hardwareapp.entity.HardwareType;
import hr.tvz.milakovic.hardwareapp.repository.HardwareRepository;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class HardwareRepositoryImpl implements HardwareRepository {

    private List<Hardware> hardwareList = new ArrayList<>(
            Arrays.asList(
            new Hardware("CPU0001", "AMD Ryzen 7 5800X", 4220.00, HardwareType.CPU, 1),
                new Hardware("GPU0001", "Palit GeForce RTX 2060 Dual 12GB GDDR6", 5554.44, HardwareType.GPU, 8),
                new Hardware("OTH0001", "Other Test Object", 1000.00, HardwareType.OTHER, 101)
            )
    );

    @Override
    public List<Hardware> findAll() {
        return hardwareList;
    }

    @Override
    public Optional<Hardware> findByCode(String code) {
        return hardwareList.stream().filter(it -> it.getCode().equals(code)).findAny();
    }

//    @Override
//    public List<Hardware> findAllWithPriceLowerThen(Double priceLowerThen) {
//        return hardwareList.stream().filter(h -> h.getPrice() < priceLowerThen).collect(Collectors.toList());
//    }

    @Override
    public Optional<Hardware> save(Hardware hardware) {
        if (!hardwareList.contains(hardware)){
            hardwareList.add(hardware);
            return Optional.of(hardware);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Optional<Hardware> update(String code, Hardware updatedHardware) {
        for (Hardware h : hardwareList){
            boolean sameCode = code.equals(h.getCode());
            if (sameCode){
                Hardware hTemp = h;
                hTemp.setPrice(updatedHardware.getPrice());
                System.out.println(hTemp);
            }
        }

        boolean exists = hardwareList.removeIf(
                h -> Objects.equals(h.getCode(), code) && Objects.equals(h.getCode(), updatedHardware.getCode())
        );

        if(exists){
            hardwareList.add(updatedHardware);
            System.out.println("POZDRAV!!!");
            return Optional.of(updatedHardware);
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void deleteByCode(String code) {
        hardwareList.removeIf(h -> Objects.equals(h.getCode(), code));
    }

    @Override
    public List<Hardware> findBetweenPrices(Double min, Double max) {
        return null;
    }

    @Override
    public List<Hardware> findWithString(String pattern) {
        return null;
    }
}
