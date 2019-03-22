package p.stapor.userservice.workItem;

import lombok.Getter;
import lombok.Setter;
import p.stapor.userservice.model.UserEntity;

import javax.persistence.*;
import java.util.Date;
@Getter
@Setter
@Entity

public class RegisterToCase {
    @Id
    @GeneratedValue
    public Integer id;
    @Temporal(TemporalType.DATE)
    public Date dateOfRegistration;
    @ManyToOne
    public UserEntity user;
    @ManyToOne
    public WorkItem workItem;

}
