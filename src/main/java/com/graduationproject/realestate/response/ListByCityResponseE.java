package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.ForRentEstateAgent;
import com.graduationproject.realestate.entities.ForSaleEstateAgent;
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

public class ListByCityResponseE {
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
    private Long cityId;
    private String companyName;

    public static ListByCityResponseE from(ForRentEstateAgent forCity){
        return ListByCityResponseE.builder()
                .listingDate(forCity.getListingDate())
                .advertTitle(forCity.getAdvertTitle())
                .price(forCity.getPrice())
                .immovablesTypes(forCity.getImmovablesTypes())
                .numberOfRooms(forCity.getNumberOfRooms())
                .buildingAge(forCity.getBuildingAge())
                .balcony(forCity.getBalcony())
                .furnished(forCity.getFurnished())
                .cityId(forCity.getCity().getId())
                .district(forCity.getCity().getDistrict())
                .cityName(forCity.getCity().getCityName())
                .companyName(forCity.getEstateAgent().getCompanyName())
                .build();
    }

    public static ListByCityResponseE froms(ForSaleEstateAgent forCity){
        return ListByCityResponseE.builder()
                .listingDate(forCity.getListingDate())
                .advertTitle(forCity.getAdvertTitle())
                .price(forCity.getPrice())
                .immovablesTypes(forCity.getImmovablesTypes())
                .numberOfRooms(forCity.getNumberOfRooms())
                .buildingAge(forCity.getBuildingAge())
                .balcony(forCity.getBalcony())
                .furnished(forCity.getFurnished())
                .cityId(forCity.getCity().getId())
                .district(forCity.getCity().getDistrict())
                .cityName(forCity.getCity().getCityName())
                .companyName(forCity.getEstateAgent().getCompanyName())
                .build();
    }

}
