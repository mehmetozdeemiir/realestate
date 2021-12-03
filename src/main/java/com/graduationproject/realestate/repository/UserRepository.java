package com.graduationproject.realestate.repository;

import com.graduationproject.realestate.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUserName(String userName);

    User findByUserNameEquals(String userName);
}
