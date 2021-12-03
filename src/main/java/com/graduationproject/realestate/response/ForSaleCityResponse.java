package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.ForSale;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import lombok.Data;

import java.util.Date;
@Data
public class ForSaleCityResponse {
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

    public ForSaleCityResponse(ForSale forSale) {
        this.id = forSale.getId();
        this.listingDate = forSale.getListingDate();
        this.advertTitle = forSale.getAdvertTitle();
        this.price = forSale.getPrice();
        this.immovablesTypes = forSale.getImmovablesTypes();
        this.numberOfRooms = forSale.getNumberOfRooms();
        this.buildingAge = forSale.getBuildingAge();
        this.balcony = forSale.getBalcony();
        this.furnished = forSale.getFurnished();
        this.cityId=forSale.getCity().getId();
        this.district = forSale.getCity().getDistrict();
        this.cityName = forSale.getCity().getCityName();
    }

}
