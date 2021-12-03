package com.graduationproject.realestate.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "owner")
@NoArgsConstructor
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String contactNumber;

    public Owner(Long id, String firstName, String lastName, String contactNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<ForRent> forRents;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<ForSale> forSales;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;
}
