package p.stapor.userservice;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
public class RegisterForm {
    @Size(min = 4, max = 50, message = "Wrong name, try again.")
    @NotBlank
    private String userName;
    private String userLastName;
    @Email
    @NotBlank
    private String userEmail;
    @Size(min = 2, max = 30, message = "Wrong password, try again")
    private String password;
    @NotNull
    private Long userUniqueNumber;
}
