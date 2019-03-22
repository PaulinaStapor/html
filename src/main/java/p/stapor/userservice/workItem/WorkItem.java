package p.stapor.userservice.workItem;

import lombok.Getter;
import lombok.Setter;
import p.stapor.userservice.model.UserEntity;

import javax.persistence.*;

@Getter
@Setter
@Entity
public class WorkItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String subject;
    private String description;
    @ManyToOne
    private UserEntity userEntity;

}
