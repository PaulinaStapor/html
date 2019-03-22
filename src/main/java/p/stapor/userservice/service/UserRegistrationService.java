package p.stapor.userservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import p.stapor.userservice.RegisterForm;
import p.stapor.userservice.model.Role;
import p.stapor.userservice.model.UserEntity;
import p.stapor.userservice.repository.RoleRepository;
import p.stapor.userservice.repository.UserRepository;

import java.util.HashSet;

@Service
public class UserRegistrationService {
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private RoleRepository roleRepository;
    private UserRepository userRepository;

    @Autowired
    public UserRegistrationService(BCryptPasswordEncoder bCryptPasswordEncoder,RoleRepository roleRepository, UserRepository userRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    public UserEntity rewriteFromFormToUser(RegisterForm registerForm) {
        UserEntity userEntity = new UserEntity();

        userEntity.setFirstName(registerForm.getUserName());
        userEntity.setLastName(registerForm.getUserLastName());
        userEntity.setUserUniqueNumber(registerForm.getUserUniqueNumber());
        userEntity.setEmail(registerForm.getUserEmail());
        userEntity.setPassword(bCryptPasswordEncoder.encode(registerForm.getPassword()));
        return userEntity;
    }

    public void registerUser(RegisterForm registerForm) {
        UserEntity userEntity = rewriteFromFormToUser(registerForm);
        Role userRole = roleRepository.findRoleByRoleName("ROLE_USER");
        if (userRole == null) {
            userRole = new Role(Role.ROLE_USER);
            roleRepository.save(userRole);
        }
        userEntity.setRoles(new HashSet<>());
        userEntity.getRoles().add(userRole);
        userRepository.save(userEntity);
    }

}
