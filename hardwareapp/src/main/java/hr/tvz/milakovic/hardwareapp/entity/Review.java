package hr.tvz.milakovic.hardwareapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    private Long id;

    private String title;
    private String content;
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "hardware_id")
    private Hardware hardware;
}
