package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.UserService;
import com.graduationproject.realestate.entities.User;
import com.graduationproject.realestate.request.UserRequest;
import com.graduationproject.realestate.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<User> getAll(){
        return userService.getAll();
    }

    @GetMapping("/{id}")
    public UserResponse getUserById(@PathVariable Long id){
        return userService.getUserById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.ACCEPTED)
    public UserResponse createUser(@Valid @RequestBody UserRequest userRequest){
        return userService.createUser(userRequest);
    }

    @PutMapping("/{id}")
    public UserResponse updateUser( @PathVariable Long id,@Valid @RequestBody UserRequest userRequest) {
        return userService.updateUser(id,userRequest);
    }

    @DeleteMapping("/{id}")
    public void deleteUser( @PathVariable Long id){
        userService.deleteUser(id);
    }

    @GetMapping("/slice")
    public List<UserResponse> slice(Pageable pageable){
        return userService.slice(pageable);
    }
}