package com.graduationproject.realestate.entities;
import com.graduationproject.realestate.request.CityRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="city")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class City {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String district;

    private String cityName;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<ForRentOwner> forRentOwners;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<ForRentEstateAgent> forRentEstateAgents;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<ForSaleOwner> forSaleOwners;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<ForSaleEstateAgent> forSaleEstateAgents;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<Owner> owners;

    @OneToMany(mappedBy = "city", fetch = FetchType.LAZY)
    private List<EstateAgent> estateAgents;


    public City(String cityName, String district) {
        this.cityName = cityName;
        this.district = district;
    }

    public City(Long id,  String cityName,String district) {
        this.id = id;
        this.cityName = cityName;
        this.district = district;

    }

    public City(String cityName) {
        this.cityName = cityName;
    }
}
