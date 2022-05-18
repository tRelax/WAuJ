package hr.tvz.milakovic.hardwareapp.DTO;

import hr.tvz.milakovic.hardwareapp.enums.HardwareType;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HardwareDTO {
    Long id;
    String code;
    String name;
    Double price;
    HardwareType type;
    Integer available;

}
