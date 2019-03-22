package p.stapor.userservice.workItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RegisterToCaseRepository extends JpaRepository<RegisterToCase,Integer> {

    RegisterToCase findFirstByUserEmail(String email);
    RegisterToCase findFirstByWorkItemIdAndUserEmail(Integer id, String email);


}
