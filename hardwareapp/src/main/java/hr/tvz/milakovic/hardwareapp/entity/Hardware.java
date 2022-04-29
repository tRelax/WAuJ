package hr.tvz.milakovic.hardwareapp.entity;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
//TODO add id and all its features
public class Hardware {
    private String code;
    private String name;
    private Double price;
    private HardwareType type;
    private Integer available;

    
}
