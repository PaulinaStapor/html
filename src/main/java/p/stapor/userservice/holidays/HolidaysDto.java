package p.stapor.userservice.holidays;

import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;
import p.stapor.userservice.model.UserEntity;

import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;

@Getter
@Setter
public class HolidaysDto {
    @Future
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private String fromDate;
    @Future
    @DateTimeFormat(pattern = "MM/dd/yyyy")
    private String toDate;
    @ManyToOne
    private UserEntity userEntity;
}
