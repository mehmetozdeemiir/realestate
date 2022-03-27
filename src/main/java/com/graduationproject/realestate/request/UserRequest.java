package com.graduationproject.realestate.request;

import com.graduationproject.realestate.validation.ValidPassword;
import lombok.Data;
import javax.validation.constraints.Size;

@Data
public class UserRequest {

    @Size(min = 3,max=26 ,message = "Maksimum veya Minimum karakter boyutunu aştınız.")
    private String userName;

    @ValidPassword
    private String password;
}
