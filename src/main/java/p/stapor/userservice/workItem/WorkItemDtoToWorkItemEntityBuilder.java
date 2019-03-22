package p.stapor.userservice.workItem;

import org.springframework.stereotype.Service;

@Service
public class WorkItemDtoToWorkItemEntityBuilder {
public WorkItem builder(WorkItemDto workItemDto){
    WorkItem workItem=new WorkItem();
    workItem.setSubject(workItemDto.getSubject());
    workItem.setDescription(workItemDto.getDescription());
    return workItem;
}

}
