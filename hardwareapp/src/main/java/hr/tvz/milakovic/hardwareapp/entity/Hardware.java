package hr.tvz.milakovic.hardwareapp.entity;

import hr.tvz.milakovic.hardwareapp.enums.HardwareType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Hardware {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String code;

    private String name;

    private Double price;

    @Enumerated(EnumType.STRING)
    private HardwareType type;

    private Integer available;

    @OneToMany(mappedBy = "hardware", fetch = FetchType.EAGER)
    private List<Review> reviewList;

    public Hardware(Long id, String code, String name, Double price, HardwareType type, Integer available) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.available = available;
    }

    public Hardware(String code, String name, Double price, HardwareType type, Integer available) {
        this.code = code;
        this.name = name;
        this.price = price;
        this.type = type;
        this.available = available;
    }
}
