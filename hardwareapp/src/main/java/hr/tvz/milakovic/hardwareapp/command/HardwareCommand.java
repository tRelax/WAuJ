package hr.tvz.milakovic.hardwareapp.command;

import hr.tvz.milakovic.hardwareapp.enums.HardwareType;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
public class HardwareCommand {

    private Long id;

    @NotBlank(message = "Code must not be empty")
    private String code;

    @NotBlank(message = "Name must not be empty")
    private String name;

    @PositiveOrZero(message = "Price must be positive or zero")
    @NotNull(message = "Price must not be empty")
    private Double price;

    @NotNull(message = "HardwareType must not be empty")
    private HardwareType type;

    @PositiveOrZero(message = "Available must be positive or zero")
    @NotNull(message = "Available must be entered")
    private Integer available;

}
