package p.stapor.userservice.workItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p.stapor.userservice.model.UserEntity;
import p.stapor.userservice.repository.UserRepository;

import java.util.List;

@Service
public class WorkItemService {
    private WorkItemDtoToWorkItemEntityBuilder workItemDtoToWorkItemEntityBuilder;
    private UserRepository userRepository;
    private WorkItemRepository workItemRepository;

    @Autowired
    public WorkItemService(WorkItemDtoToWorkItemEntityBuilder workItemDtoToWorkItemEntityBuilder, UserRepository userRepository, WorkItemRepository workItemRepository) {
        this.workItemDtoToWorkItemEntityBuilder = workItemDtoToWorkItemEntityBuilder;
        this.userRepository = userRepository;
        this.workItemRepository = workItemRepository;
    }

    public void addWorkItem(WorkItemDto workItemDto, String query) {
        WorkItem workItem = workItemDtoToWorkItemEntityBuilder.builder(workItemDto);

        UserEntity userEntity = userRepository.findByEmail(query);
        workItem.setUserEntity(userEntity);
        workItemRepository.save(workItem);
    }
    public List<WorkItem>showAllCases(){
        return workItemRepository.findAll();
    }
}
