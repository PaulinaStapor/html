package p.stapor.userservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import p.stapor.userservice.model.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    @Query("SELECT r from Role r where r.roleName = ?1")
    Role findRoleByRoleName(String roleName);
}
