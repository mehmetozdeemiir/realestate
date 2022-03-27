package com.graduationproject.realestate.business.abstracts;

import com.graduationproject.realestate.entities.User;
import com.graduationproject.realestate.request.UserRequest;
import com.graduationproject.realestate.response.UserResponse;
import org.springframework.data.domain.Pageable;
import java.util.List;

public interface UserService {
    UserResponse getUserById(Long id);

    UserResponse createUser(UserRequest userCreateDTO);

    UserResponse updateUser(Long id, UserRequest userUpdateDTO);

    void deleteUser(Long id);

    List<User> getAll();

    List<UserResponse> slice(Pageable pageable);

    User getOneUserByUserName(String userName);

    User saveOneUser(User newUser);
}
