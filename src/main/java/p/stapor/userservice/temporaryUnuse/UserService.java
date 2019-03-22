//package p.stapor.userservice.service;
//
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import p.stapor.userservice.controller.dto.UserDto;
//import p.stapor.userservice.controller.exception.UserNotExistException;
//import p.stapor.userservice.model.UserEntity;
//import p.stapor.userservice.temporaryUnuse.mapper.UserDTOtoUserEntityBuilder;
//import p.stapor.userservice.repository.UserRepository;
//
//import java.util.List;
//import java.util.Optional;
//
//@Service
//public class UserService {
//    private final UserRepository userRepository;
//    private final UserDTOtoUserEntityBuilder userDTOtoUserEntityBuilder;
//
//    @Autowired
//    public UserService(UserRepository userRepository, UserDTOtoUserEntityBuilder userDTOtoUserEntityBuilder) {
//        this.userRepository = userRepository;
//        this.userDTOtoUserEntityBuilder = userDTOtoUserEntityBuilder;
//    }
//
//    public void addUser(UserDto userDTO) {
//        UserEntity userEntity = userDTOtoUserEntityBuilder.rewriteUserDTOtoUserEntity(userDTO);
//        if (checkIfUserExist(userDTO.getUserUniqueNumber())) {
//            throw new UserNotExistException("User with number: " + userDTO.getUserUniqueNumber() + " already exist");
//        }
//        userRepository.save(userEntity);
//    }
//
//    private boolean checkIfUserExist(Long userUniqueNumber) {
//        userRepository.findUserSByUserUniqueNumber(userUniqueNumber);
//        return true;
//    }
//
//    public void deleteUser(Long id) {
//        Optional<UserEntity> userById = userRepository.findById(id);
//        UserEntity userEntity;
//        if (userById.isPresent()) {
//            userEntity = userById.get();
//            userRepository.delete(userEntity);
//        } else {
//            throw new UserNotExistException("User does not exist");
//        }
//    }
//
//    public List<UserEntity> getAllUsers() {
//
//        return userRepository.getAllUsers();
//    }
//
//    private boolean checkIfUserExistById(Long id) {
//
//        return userRepository.findById(id).isPresent();
//    }
//
//    public void modifyUser(Long id, UserDto userDto) {
//        UserEntity userToModify = userRepository.findOneUserById(id);
//        if (checkIfUserExistById(userToModify.getId())) {
//            userToModify.setFirstName(userDto.getFirstName());
//            userToModify.setLastName(userDto.getLastName());
//            userRepository.save(userToModify);
//        } else {
//            throw new UserNotExistException("User not found");
//        }
//    }
//
//    public UserEntity getUserById(Long id) {
//        Optional<UserEntity> userById = userRepository.findById(id);
//        if (userById.isPresent()) {
//            return userById.get();
//        } else {
//            throw new UserNotExistException("Can not find user with id: " + id);
//        }
//    }
//
//    public List<UserEntity> getUsersByLastName(String lastName) {
//        List<UserEntity> userByName = userRepository.findAllByLastName(lastName);
//        if (userByName.isEmpty()) {
//            throw new UserNotExistException("Can not find user with name: " + lastName);
//        } else {
//            return userByName;
//        }
//    }
//}
