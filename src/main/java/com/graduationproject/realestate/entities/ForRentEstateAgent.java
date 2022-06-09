package com.graduationproject.realestate.entities;

import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
@Entity
@Table(name = "forrentestateagent")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForRentEstateAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private LocalDate listingDate;

    private String advertTitle;

    private Long price;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    private String numberOfRooms;

    private int buildingAge;

    private Boolean balcony;

    private Boolean furnished;

    public ForRentEstateAgent(LocalDate listingDate, String advertTitle, Long price, ProductType productType, String numberOfRooms, int buildingAge, Boolean balcony, Boolean furnished, EstateAgent estateAgent, City city) {
        this.listingDate = listingDate;
        this.advertTitle = advertTitle;
        this.price = price;
        this.productType = productType;
        this.numberOfRooms = numberOfRooms;
        this.buildingAge = buildingAge;
        this.balcony = balcony;
        this.furnished = furnished;
        this.estateAgent=estateAgent;
        this.city = city;
    }

    public ForRentEstateAgent(Long id, LocalDate listingDate, String advertTitle, Long price, ProductType productType, String numberOfRooms, int buildingAge, Boolean balcony, Boolean furnished, EstateAgent estateAgent, City city) {
        this.id=id;
        this.listingDate = listingDate;
        this.advertTitle = advertTitle;
        this.price = price;
        this.productType = productType;
        this.numberOfRooms = numberOfRooms;
        this.buildingAge = buildingAge;
        this.balcony = balcony;
        this.furnished = furnished;
        this.estateAgent=estateAgent;
        this.city = city;
    }
    public static ForRentEstateAgent from(ForRentEstateAgentRequest estateAgentRentRequest) {
        return ForRentEstateAgent.builder()
                .listingDate(estateAgentRentRequest.getListingDate())
                .advertTitle(estateAgentRentRequest.getAdvertTitle())
                .price(estateAgentRentRequest.getPrice())
                .productType(estateAgentRentRequest.getProductType())
                .numberOfRooms(estateAgentRentRequest.getNumberOfRooms())
                .buildingAge(estateAgentRentRequest.getBuildingAge())
                .balcony(estateAgentRentRequest.getBalcony())
                .furnished(estateAgentRentRequest.getFurnished())
                .build();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estate_agent_id")
    private EstateAgent estateAgent;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;
}
