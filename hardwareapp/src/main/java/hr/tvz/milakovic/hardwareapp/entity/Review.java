package hr.tvz.milakovic.hardwareapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long ID;

    private String title;
    private String content;
    private Integer rating;

    @ManyToOne
    @JoinColumn(name = "hardware_id", referencedColumnName="id")
    private Hardware hardware;
}
