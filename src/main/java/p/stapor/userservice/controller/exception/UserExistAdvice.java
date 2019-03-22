package p.stapor.userservice.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UserExistAdvice {
@ResponseBody
@ExceptionHandler(UserNotExistException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
       String userNotExistHandler(UserNotExistException userNotExistException){
        return userNotExistException.getMessage();
    }
}
