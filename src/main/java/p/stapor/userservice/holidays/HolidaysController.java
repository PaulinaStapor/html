package p.stapor.userservice.holidays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import p.stapor.userservice.repository.UserRepository;

import javax.validation.Valid;
import java.text.ParseException;

@Controller
public class HolidaysController {

    private HolidaysService holidaysService;
    private HolidaysRepository holidaysRepository;
    private UserRepository userRepository;

    @Autowired
    public HolidaysController(HolidaysService holidaysService, HolidaysRepository holidaysRepository, UserRepository userRepository) {
        this.holidaysService = holidaysService;
        this.holidaysRepository = holidaysRepository;
        this.userRepository = userRepository;
    }
@GetMapping(value = "/addholidays")
    public String showHolidayForm(Model model){
        model.addAttribute("holidays", new HolidaysDto());
        return "addholidays";
}
@PostMapping(value = "/addholidays")
    public String processingHolidayForm (@ModelAttribute("holidays") @Valid HolidaysDto holidaysDto,
                                         BindingResult bindingResult,
                                         Authentication authentication) throws ParseException {
        if(bindingResult.hasErrors()){
            return "addholidays";
        }
        String email=authentication.getName();
        holidaysService.addHolidays(holidaysDto, email);
        return "holidaysResult";
}
@GetMapping(value = "/allholidays")
    public String showHolidays (Model model){
        model.addAttribute("holidaysList", holidaysService.allHolidays());
        return "holidays";
}
}
