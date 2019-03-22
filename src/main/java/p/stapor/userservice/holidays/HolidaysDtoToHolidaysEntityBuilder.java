package p.stapor.userservice.holidays;

import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class HolidaysDtoToHolidaysEntityBuilder {
public Holidays builder(HolidaysDto holidaysDto) throws ParseException {
    Holidays holidays=new Holidays();
    String dtoFromDate=holidaysDto.getFromDate();
    SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd");
    Date entityFromData=formatter.parse(dtoFromDate);
    holidays.setFromDate(entityFromData);
    String dtoToDate=holidaysDto.getToDate();
    Date entityToDate=formatter.parse(dtoToDate);
    holidays.setToDate(entityToDate);
    return holidays;
}
}
