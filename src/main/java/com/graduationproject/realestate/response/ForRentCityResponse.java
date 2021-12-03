package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.ForRent;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import lombok.Data;

import java.util.Date;
@Data
public class ForRentCityResponse {
    private Long id;
    private Date listingDate;
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

    public ForRentCityResponse(ForRent forRent) {
        this.id = forRent.getId();
        this.listingDate = forRent.getListingDate();
        this.advertTitle = forRent.getAdvertTitle();
        this.price = forRent.getPrice();
        this.immovablesTypes = forRent.getImmovablesTypes();
        this.numberOfRooms = forRent.getNumberOfRooms();
        this.buildingAge = forRent.getBuildingAge();
        this.balcony = forRent.getBalcony();
        this.furnished = forRent.getFurnished();
        this.cityId=forRent.getCity().getId();
        this.district = forRent.getCity().getDistrict();
        this.cityName = forRent.getCity().getCityName();
    }
}
