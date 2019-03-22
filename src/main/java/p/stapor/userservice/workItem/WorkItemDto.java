package p.stapor.userservice.workItem;

import lombok.Getter;
import lombok.Setter;
import p.stapor.userservice.model.UserEntity;

import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class WorkItemDto {


    @NotBlank
    private String subject;
    @NotBlank
    private String description;
    @ManyToOne
    private UserEntity userEntity;
}
