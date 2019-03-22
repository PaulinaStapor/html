package p.stapor.userservice.holidays;

import lombok.Getter;
import lombok.Setter;
import p.stapor.userservice.model.UserEntity;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Holidays {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Date fromDate;

    private Date toDate;

    @ManyToOne
    private UserEntity userEntity;

}
