package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.UserService;
import com.graduationproject.realestate.entities.User;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.UserRepository;
import com.graduationproject.realestate.request.UserRequest;
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
    public UserRequest getUserById(Long id) {
        final User user=  userRepository.findById(id).orElseThrow(() -> new ApiRequestException("bulunamadı"));
        return UserRequest.convert(user);
    }


    @Override
    @Transactional
    public UserRequest createUser(UserRequest userCreateDTO) {

        if (this.userRepository.findByUserNameEquals(userCreateDTO.getUserName()) != null) {
            throw new ApiRequestException("kullanılan bir kulanıcı adı girdiniz.");
        }
        final User user = userRepository.save(new User(userCreateDTO.getId(), userCreateDTO.getUserName(), userCreateDTO.getPassword()));
        return UserRequest.convert(user);
    }

    @Override
    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public List<UserRequest> slice(Pageable pageable) {
        return userRepository.findAll(pageable).stream().map(UserRequest::convert).collect(Collectors.toList());
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
    public UserRequest updateUser(Long id, UserRequest userUpdateDTO) {
        if (this.userRepository.findByUserNameEquals(userUpdateDTO.getUserName())!=null){
            throw new ApiRequestException("Kullanılan bir kullanıcı adını güncelleyemezsiniz..");
        }
        final User user= userRepository.findById(id).orElseThrow(()-> new ApiRequestException("güncellenemedi"));
        user.setId(userUpdateDTO.getId());
        user.setUserName(userUpdateDTO.getUserName());
        user.setPassword(userUpdateDTO.getPassword());
        final User updatedUser=userRepository.save(user);
        return UserRequest.convert(updatedUser);
    }

    @Override
    public void deleteUser(Long id) {
        User user= userRepository.findById(id).orElseThrow(()->new ApiRequestException("kaldırılamadı"));
        userRepository.deleteById(user.getId());
    }
}
