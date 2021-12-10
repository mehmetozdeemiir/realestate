package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private String userName;
    private String password;

    public static UserResponse from(User user){
        return builder()
                .userName(user.getUserName())
                .password(user.getPassword())
                .build();
    }

}
