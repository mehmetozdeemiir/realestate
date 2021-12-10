package com.graduationproject.realestate.entities;

import com.graduationproject.realestate.request.ForSaleEstateAgentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "forsaleestateagent")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForSaleEstateAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate listingDate;

    private String advertTitle;

    private Long price;

    @Enumerated(EnumType.STRING)
    private ImmovablesTypes immovablesTypes;

    private String numberOfRooms;

    private int buildingAge;

    private Boolean balcony;

    private Boolean furnished;

    public ForSaleEstateAgent( LocalDate listingDate, String advertTitle, Long price, ImmovablesTypes immovablesTypes, String numberOfRooms, int buildingAge, Boolean balcony, Boolean furnished, EstateAgent estateAgent, City city) {
        this.listingDate = listingDate;
        this.advertTitle = advertTitle;
        this.price = price;
        this.immovablesTypes = immovablesTypes;
        this.numberOfRooms = numberOfRooms;
        this.buildingAge = buildingAge;
        this.balcony = balcony;
        this.furnished = furnished;
        this.estateAgent=estateAgent;
        this.city = city;
    }

    public static ForSaleEstateAgent from(ForSaleEstateAgentRequest estateAgentSaleRequest) {
        return ForSaleEstateAgent.builder()
                .listingDate(estateAgentSaleRequest.getListingDate())
                .advertTitle(estateAgentSaleRequest.getAdvertTitle())
                .price(estateAgentSaleRequest.getPrice())
                .immovablesTypes(estateAgentSaleRequest.getImmovablesTypes())
                .numberOfRooms(estateAgentSaleRequest.getNumberOfRooms())
                .buildingAge(estateAgentSaleRequest.getBuildingAge())
                .balcony(estateAgentSaleRequest.getBalcony())
                .furnished(estateAgentSaleRequest.getFurnished())
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
