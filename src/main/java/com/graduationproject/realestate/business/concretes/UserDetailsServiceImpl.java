package com.graduationproject.realestate.business.concretes;
import com.graduationproject.realestate.entities.User;
import com.graduationproject.realestate.repository.UserRepository;
import com.graduationproject.realestate.security.JwtUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;
    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    //user ı al userdetails olarak dön
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user=userRepository.findByUserName(username);
        return JwtUserDetails.create(user);
    }
    //user ı al userdetails olarak dön
    public UserDetails loadUserById(Long id){
        User user =userRepository.findById(id).get();
        return JwtUserDetails.create(user);
    }
}
