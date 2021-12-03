package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.ForRent;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import lombok.Data;

import java.util.Date;

@Data
public class ForRentOwnerResponse {
    private Long id;
    private Date listingDate;
    private String advertTitle;
    private Long price;
    private ImmovablesTypes immovablesTypes;
    private String numberOfRooms;
    private int buildingAge;
    private Boolean balcony;
    private Boolean furnished;
    private String district;
    private String cityName;
    private Long ownerId;
    private String firstName;
    private String lastName;
    private String contactNumber;

    public ForRentOwnerResponse(ForRent forRent){
        this.id=forRent.getId();
        this.listingDate=forRent.getListingDate();
        this.advertTitle=forRent.getAdvertTitle();
        this.price=forRent.getPrice();
        this.immovablesTypes=forRent.getImmovablesTypes();
        this.numberOfRooms= forRent.getNumberOfRooms();
        this.buildingAge=forRent.getBuildingAge();
        this.balcony=forRent.getBalcony();
        this.furnished=forRent.getFurnished();
        this.district=forRent.getCity().getDistrict();
        this.cityName=forRent.getCity().getCityName();
        this.ownerId=forRent.getOwner().getId();
        this.firstName=forRent.getOwner().getFirstName();
        this.lastName=forRent.getOwner().getLastName();
        this.contactNumber=forRent.getOwner().getContactNumber();
    }
}
