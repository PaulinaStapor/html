package p.stapor.userservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import p.stapor.userservice.repository.UserRepository;

@Service
public class UserLoginDAO {
private UserRepository userRepository;
@Autowired
    public UserLoginDAO(UserRepository userRepository){
    this.userRepository=userRepository;
}

}
