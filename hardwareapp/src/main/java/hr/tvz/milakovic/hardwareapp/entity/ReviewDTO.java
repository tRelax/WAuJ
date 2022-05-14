package hr.tvz.milakovic.hardwareapp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReviewDTO {
    private Long id;
    private String title;
    private String content;
    private Integer score;
    private Long hardwareId;
}
