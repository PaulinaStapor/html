package p.stapor.userservice.holidays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p.stapor.userservice.model.UserEntity;
import p.stapor.userservice.repository.UserRepository;

import java.text.ParseException;
import java.util.List;

@Service
public class HolidaysService {

    private HolidaysRepository holidaysRepository;
    private HolidaysDtoToHolidaysEntityBuilder holidaysDtoToHolidaysEntityBuilder;
    private UserRepository userRepository;

    @Autowired
    public HolidaysService(HolidaysRepository holidaysRepository, HolidaysDtoToHolidaysEntityBuilder holidaysDtoToHolidaysEntityBuilder, UserRepository userRepository) {
        this.holidaysRepository = holidaysRepository;
        this.holidaysDtoToHolidaysEntityBuilder = holidaysDtoToHolidaysEntityBuilder;
        this.userRepository = userRepository;
    }

    public void addHolidays(HolidaysDto holidaysDto, String query) throws ParseException {
        Holidays holidays = holidaysDtoToHolidaysEntityBuilder.builder(holidaysDto);

        UserEntity user = userRepository.findByEmail(query);
        holidays.setUserEntity(user);
        holidaysRepository.save(holidays);
    }

    public List<Holidays> allHolidays() {

        return holidaysRepository.findAll();
    }
}
