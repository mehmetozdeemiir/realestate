package com.graduationproject.realestate.entities;

import com.graduationproject.realestate.request.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ForRentOwner> forRentOwners;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ForRentEstateAgent> forRentEstateAgents;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ForSaleOwner> forSaleOwners;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ForSaleEstateAgent> forSaleEstateAgents;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    public static User from(UserRequest userRequest){
        return User.builder()
                .userName(userRequest.getUserName())
                .password(userRequest.getPassword())
                .build();
    }
}
