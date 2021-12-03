package com.graduationproject.realestate.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "estateagent")
@NoArgsConstructor

public class EstateAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String contactNumber;


    @OneToMany(mappedBy = "estateAgent", fetch = FetchType.LAZY)
    private List<ForRent> forRents;

    @OneToMany(mappedBy = "estateAgent", fetch = FetchType.LAZY)
    private List<ForSale> forSales;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    public EstateAgent(Long id, String companyName, String contactNumber, City city) {
        this.id = id;
        this.companyName = companyName;
        this.contactNumber = contactNumber;
        this.city = city;
    }
}
