package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.ForSaleEstateAgent;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ForSaleEstateAgentResponse {
    private LocalDate listingDate;
    private String advertTitle;
    private Long price;
    private ImmovablesTypes immovablesTypes;
    private String numberOfRooms;
    private int buildingAge;
    private Boolean balcony;
    private Boolean furnished;
    private String district;
    private String cityName;
    private Long estateAgentId;
    private String companyName;
    private String contactNumber;


    public static ForSaleEstateAgentResponse from(ForSaleEstateAgent forSale){
        return ForSaleEstateAgentResponse.builder()
                .listingDate(forSale.getListingDate())
                .advertTitle(forSale.getAdvertTitle())
                .price(forSale.getPrice())
                .immovablesTypes(forSale.getImmovablesTypes())
                .numberOfRooms(forSale.getNumberOfRooms())
                .buildingAge(forSale.getBuildingAge())
                .balcony(forSale.getBalcony())
                .furnished(forSale.getFurnished())
                .district(forSale.getCity().getDistrict())
                .cityName(forSale.getCity().getCityName())
                .estateAgentId(forSale.getEstateAgent().getId())
                .companyName(forSale.getEstateAgent().getCompanyName())
                .contactNumber(forSale.getEstateAgent().getContactNumber())
                .build();
    }
}
