package hr.tvz.milakovic.hardwareapp.entity;

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
