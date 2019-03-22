package p.stapor.userservice.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;
    @Column
    private String firstName;
    @Column
    private String lastName;
    @Column(unique = true)
    @NotNull
    private Long userUniqueNumber;
    @Column(unique = true)
    private String email;
    private String password;

    @ManyToMany
    @JoinTable(name = "user_role")
    private Set<Role> roles;
}
