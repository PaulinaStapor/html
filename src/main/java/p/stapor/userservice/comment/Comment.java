package p.stapor.userservice.comment;

import lombok.Getter;
import lombok.Setter;
import p.stapor.userservice.model.UserEntity;
import p.stapor.userservice.workItem.WorkItem;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(length = 500)
    private String comment;
    @ManyToOne
    private UserEntity user;
    @ManyToOne
    private WorkItem workItem;
    private Date date;
}
