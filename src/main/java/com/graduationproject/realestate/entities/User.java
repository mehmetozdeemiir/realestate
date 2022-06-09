package com.graduationproject.realestate.entities;

import com.graduationproject.realestate.request.UserRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    private Set<ForRentOwner> forRentOwners;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<ForRentEstateAgent> forRentEstateAgents;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<ForSaleOwner> forSaleOwners;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private Set<ForSaleEstateAgent> forSaleEstateAgents;

    public User(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

}
