package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.UserService;
import com.graduationproject.realestate.entities.User;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.UserRepository;
import com.graduationproject.realestate.request.UserRequest;
import com.graduationproject.realestate.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;
@Service
@RequiredArgsConstructor

public class UserManager implements UserService {
    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public UserResponse getUserById(Long id) {
        final User user=  userRepository.findById(id).orElseThrow(() -> new ApiRequestException("bulunamadı"));
        return UserResponse.from(user);
    }

    @Override
    @Transactional
    public UserResponse createUser(UserRequest userRequest) {

        if (this.userRepository.findByUserNameEquals(userRequest.getUserName()) != null) {
            throw new ApiRequestException("kullanılan bir kulanıcı adı girdiniz.");
        }
        final User user = userRepository.save(new User(userRequest.getUserName(), userRequest.getPassword())); //userRequest eliimizde var user a esliyoz
        return UserResponse.from(user);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserResponse> slice(Pageable pageable) {
        return userRepository.findAll(pageable).stream().map(UserResponse::from).collect(Collectors.toList());
    }

    @Override
    public User getOneUserByUserName(String userName) {
        return userRepository.findByUserName(userName);
    }

    @Override
    public User saveOneUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    @Transactional
    public UserResponse updateUser(Long id, UserRequest userRequest) {
        if (this.userRepository.findByUserNameEquals(userRequest.getUserName())!=null){
            throw new ApiRequestException("Kullanılan bir kullanıcı adını güncelleyemezsiniz..");
        }
        final User user= userRepository.findById(id).orElseThrow(()-> new ApiRequestException("güncellenemedi"));
        user.setUserName(userRequest.getUserName());
        user.setPassword(userRequest.getPassword());
        final User updatedUser=userRepository.save(user);
        return UserResponse.from(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user= userRepository.findById(id).orElseThrow(()->new ApiRequestException("kaldırılamadı"));
        userRepository.deleteById(user.getId());
    }
}
