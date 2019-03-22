package p.stapor.userservice.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import p.stapor.userservice.model.UserEntity;
import p.stapor.userservice.repository.UserRepository;
import p.stapor.userservice.workItem.WorkItemRepository;

import java.util.Date;

@Controller
public class CommentController {
    private WorkItemRepository workItemRepository;
    private UserRepository userRepository;
    private CommentRepository commentRepository;

    @Autowired
    public CommentController(WorkItemRepository workItemRepository, UserRepository userRepository, CommentRepository commentRepository) {
        this.workItemRepository = workItemRepository;
        this.userRepository = userRepository;
        this.commentRepository = commentRepository;
    }

    @GetMapping(value = "/case/{id}/comment")
    public String addCommentToCase(Model model) {
        model.addAttribute("comment", new CommentDto());
        return "showOneCase";
    }

    @PostMapping(value = "/case/{id}/comment")
    public String sddComment(@PathVariable String id,
                             @RequestParam String comment,
                             Authentication authentication) {
        Comment commentEntity = new Comment();
        commentEntity.setComment(comment);
        commentEntity.setWorkItem(workItemRepository.findOneById(Integer.valueOf(id)));
        UserEntity user = userRepository.findByEmail(authentication.getName());
        commentEntity.setUser(user);
        commentEntity.setDate(new Date());
        commentRepository.save(commentEntity);

        return "redirect:/case/" + id;
    }

    @GetMapping(value = "/case/{id}")
    public String showCaseComments (@PathVariable String id,
                                    Model model){
        model.addAttribute("workItem", workItemRepository.findOneById(Integer.valueOf(id)));
        model.addAttribute("comment", new CommentDto());
        model.addAttribute("caseComments", commentRepository.findCommentByWorkItemIdOrderByDate(Integer.valueOf(id)));
        return "showOneCase";
    }
}
