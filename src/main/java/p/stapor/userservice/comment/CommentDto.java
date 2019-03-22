package p.stapor.userservice.comment;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class CommentDto {
    @Size(max = 500)
    @NotBlank
    private String comment;

}
