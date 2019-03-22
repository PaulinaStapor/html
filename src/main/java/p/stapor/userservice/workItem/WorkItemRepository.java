package p.stapor.userservice.workItem;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkItemRepository extends JpaRepository <WorkItem, Integer> {

    @Query("select w from WorkItem w")
    List<WorkItem> findAll();
    @Query("select w from WorkItem w where w.id =:id")
    WorkItem findOneById(Integer id);
}
