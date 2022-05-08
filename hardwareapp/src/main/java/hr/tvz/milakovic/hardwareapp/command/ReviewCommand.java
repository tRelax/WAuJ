package hr.tvz.milakovic.hardwareapp.command;

import lombok.Data;

import javax.validation.constraints.*;

@Data
public class ReviewCommand {

    private Long id;

    @NotBlank(message = "Title must not be empty")
    private String title;

    @Min(message = "Score must be >=1 & <=5", value = 1)
    @Max(message = "Score must be >=1 & <=5", value = 5)
    @NotNull(message = "Price must not be empty")
    private Integer score;
}
