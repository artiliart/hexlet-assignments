package exercise.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

@Setter
@Getter
public class GuestCreateDTO {
    @NotNull
    private String name;

    @Email
    private String email;

    @Length(min = 11, max = 13)
    @Pattern(regexp = "\\+?\\d+")
    private String phoneNumber;

    @Size(min = 4 , max = 4)
    private String clubCard;

    @Future
    private LocalDate cardValidUntil;
}

