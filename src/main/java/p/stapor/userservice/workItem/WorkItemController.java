package p.stapor.userservice.workItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import p.stapor.userservice.model.UserEntity;
import p.stapor.userservice.repository.UserRepository;

import javax.validation.Valid;
import java.util.Date;


@Controller
public class WorkItemController {
    private WorkItemService workItemService;
    private WorkItemRepository workItemRepository;
    private UserRepository userRepository;
    private RegisterToCaseRepository registerToCaseRepository;
    private RegisterToCase registerToCase;

    @Autowired
    public WorkItemController(WorkItemService workItemService,
                              WorkItemRepository workItemRepository,
                              UserRepository userRepository,
                              RegisterToCaseRepository registerToCaseRepository) {
        this.workItemService = workItemService;
        this.workItemRepository = workItemRepository;
        this.userRepository = userRepository;
        this.registerToCaseRepository = registerToCaseRepository;
    }


    //wyswietlanie strony z formularzem dodawania sprawy
    @GetMapping(value = "/addcase")
    public String showWorkItemForm(Model model) {
        model.addAttribute("addcase", new WorkItemDto());
        return "addcase";
    }

    @PostMapping(value = "/addcase")
    public String processingWorkItemForm(@ModelAttribute @Valid WorkItemDto workItemDto,
                                         BindingResult bindingResult,
                                         Authentication authentication) {
        if (bindingResult.hasErrors()) {
            return "addcase";
        }
        String email = authentication.getName();
        workItemService.addWorkItem(workItemDto, email);
        return "index";
    }

    //pokazuje wszystkie sprawy
    @GetMapping(value = "/allcases")
    public String showCases(Model model) {
        model.addAttribute("caseList", workItemService.showAllCases());
        return "cases";
    }

//    @GetMapping(value = "/case/{id}")
//    public String showOneCase(@PathVariable String id, Model model) {
//
//        model.addAttribute("workItem", workItemRepository.findOneById(Integer.valueOf(id)));
//        return "showOneCase";
//
//    }

    //przypisanie użytkownika do sprawy
    @PostMapping(value = "/case/{id}/register")
    public String registerUserToCase(@PathVariable String id,
                                     RedirectAttributes redirectAttributes,
                                     Authentication authentication) {
        RegisterToCase registerToCase = new RegisterToCase();
        registerToCase.setDateOfRegistration(new Date());
        UserEntity user = userRepository.findByEmail(authentication.getName());
        registerToCase.setUser(user);
        registerToCase.setWorkItem(workItemRepository.findOneById(Integer.valueOf(id)));

        if (registerToCaseRepository.findFirstByUserEmail(user.getEmail()) != null) {
            redirectAttributes.addFlashAttribute("msg", "You have already joined to this case");
            return "redirect:/case/" + id;
        }
        registerToCaseRepository.save(registerToCase);
        redirectAttributes.addFlashAttribute("msg", "You joined to this case");
        return "redirect:/case/" + id;

    }

    @PostMapping(value = "case/{id}/leave")
    public String deleteRegisterUserToCase(@PathVariable String id,
                                           Authentication authentication,
                                           RedirectAttributes redirectAttributes) {
        registerToCase = registerToCaseRepository.findFirstByWorkItemIdAndUserEmail(Integer.valueOf(id), authentication.getName());
        if(registerToCase!=null){
            registerToCaseRepository.delete(registerToCase);
            redirectAttributes.addFlashAttribute("msg", "You left this case.");
            return "redirect:/case/" + id;
        }
        else{
            return "redirect:/case/"+id;
        }



    }

}
