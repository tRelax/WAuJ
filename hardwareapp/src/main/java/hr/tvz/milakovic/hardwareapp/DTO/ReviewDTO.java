package hr.tvz.milakovic.hardwareapp.DTO;

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
    private Integer rating;
    private Long hardwareId;
}
