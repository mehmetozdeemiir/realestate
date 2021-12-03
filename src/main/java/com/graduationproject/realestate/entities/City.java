package com.graduationproject.realestate.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="city")
@Data
@NoArgsConstructor

public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String district;

    private String cityName;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<ForRent> forRents;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<ForSale> forSales;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Owner> owners;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<EstateAgent> estateAgents;

    public City(Long id, String cityName, String district) {
        this.id = id;
        this.cityName = cityName;
        this.district = district;
    }
}
