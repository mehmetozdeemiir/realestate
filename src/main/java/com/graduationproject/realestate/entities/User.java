package com.graduationproject.realestate.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "users")
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    private String password;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ForRent> forRents;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<ForSale> forSales;

    public User(Long id, String userName, String password) {
        this.id = id;
        this.userName = userName;
        this.password = password;
    }
}
