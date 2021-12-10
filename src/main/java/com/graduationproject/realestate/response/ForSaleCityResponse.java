/*package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.ImmovablesTypes;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForSaleCityResponse {
    private LocalDate listingDate;
    private String advertTitle;
    private Long price;
    private ImmovablesTypes immovablesTypes;
    private String numberOfRooms;
    private int buildingAge;
    private Boolean balcony;
    private Boolean furnished;
    private Long cityId;
    private String district;
    private String cityName;

    public static ForSaleCityResponse from(ForSaleOwnerResponse forSale){
        return ForSaleCityResponse.builder()
                .listingDate(forSale.getListingDate())
                .advertTitle(forSale.getAdvertTitle())
                .price(forSale.getPrice())
                .immovablesTypes(forSale.getImmovablesTypes())
                .numberOfRooms(forSale.getNumberOfRooms())
                .buildingAge(forSale.getBuildingAge())
                .balcony(forSale.getBalcony())
                .furnished(forSale.getFurnished())
                .cityId(forSale.getCity().getId())
                .district(forSale.getCity().getDistrict())
                .cityName(forSale.getCity().getCityName())
                .build();
    }

}

 */
