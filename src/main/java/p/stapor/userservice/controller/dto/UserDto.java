package p.stapor.userservice.controller.dto;

import lombok.*;

@Getter
@Setter
@Data
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private Long userUniqueNumber;
}
