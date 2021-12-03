package com.graduationproject.realestate.business.abstracts;

import com.graduationproject.realestate.entities.User;
import com.graduationproject.realestate.request.UserRequest;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface UserService {
    UserRequest getUserById(Long id);
    UserRequest createUser(UserRequest userCreateDTO);
    UserRequest updateUser(Long id, UserRequest userUpdateDTO);
    void deleteUser(Long id);
    List<User> getAll();
    List<UserRequest> slice(Pageable pageable);
    User getOneUserByUserName(String userName);
    User saveOneUser(User newUser);
}
