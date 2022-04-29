package hr.tvz.milakovic.hardwareapp.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HardwareDTO {
    String code;
    String name;
    Double price;
    HardwareType type;
    Integer available;

}
