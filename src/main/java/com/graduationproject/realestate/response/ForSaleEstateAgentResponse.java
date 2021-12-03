package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.ForRent;
import com.graduationproject.realestate.entities.ForSale;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import lombok.Data;

import java.util.Date;

@Data
public class ForSaleEstateAgentResponse {
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
    private Long estateAgentId;
    private String firstName;
    private String lastName;
    private String contactNumber;

    public ForSaleEstateAgentResponse(ForSale forSale){
        this.id=forSale.getId();
        this.listingDate=forSale.getListingDate();
        this.advertTitle=forSale.getAdvertTitle();
        this.price=forSale.getPrice();
        this.immovablesTypes=forSale.getImmovablesTypes();
        this.numberOfRooms= forSale.getNumberOfRooms();
        this.buildingAge=forSale.getBuildingAge();
        this.balcony=forSale.getBalcony();
        this.furnished=forSale.getFurnished();
        this.district=forSale.getCity().getDistrict();
        this.cityName=forSale.getCity().getCityName();
        this.estateAgentId=forSale.getEstateAgent().getId();
        this.firstName=forSale.getOwner().getFirstName();
        this.lastName=forSale.getOwner().getLastName();
        this.contactNumber=forSale.getOwner().getContactNumber();
    }
}
