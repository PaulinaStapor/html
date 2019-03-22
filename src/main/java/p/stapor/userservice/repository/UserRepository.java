package p.stapor.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import p.stapor.userservice.model.UserEntity;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u from UserEntity u")
    List<UserEntity> getAllUsers();

    @Query("select u from UserEntity u where u.lastName like %?1%")
    List<UserEntity> findAllByLastName(String lastName);

    @Query("select u from UserEntity u where u.id =:id")
    UserEntity findOneUserById(Long id);

    @Query("select u from UserEntity u where u.userUniqueNumber = :userUniqueNumber")
    UserEntity findUserSByUserUniqueNumber(Long userUniqueNumber);

    @Query("select u from UserEntity u where u.email = :email")
    UserEntity findByEmail(String email);
    @Query("select u from UserEntity u where u.lastName= :query")
    List<UserEntity>findUserEntitiesByLastNameLike (String query);

}
