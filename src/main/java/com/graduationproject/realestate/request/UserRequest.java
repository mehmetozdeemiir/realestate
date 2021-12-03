package com.graduationproject.realestate.request;

import com.graduationproject.realestate.entities.User;
import lombok.Data;

@Data
public class UserRequest {
    private Long id;
    private String userName;
    private String password;

    public UserRequest(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }

    public static UserRequest convert(User user){
        return new UserRequest(user.getId(), user.getUserName(), user.getPassword());
    }
}
