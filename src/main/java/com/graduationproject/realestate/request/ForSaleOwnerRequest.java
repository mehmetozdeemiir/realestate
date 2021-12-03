package com.graduationproject.realestate.request;
import com.graduationproject.realestate.entities.ForSale;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.util.Date;

@Data
public class ForSaleOwnerRequest {
    private Long id;

    @DateTimeFormat(pattern = "dd.MM.yyyy")
    private Date listingDate;

    private String advertTitle;

    private Long price;

    @Enumerated(EnumType.STRING)
    private ImmovablesTypes immovablesTypes;

    private String numberOfRooms;

    private int buildingAge;

    private Boolean balcony;

    private Boolean furnished;

    private String district;

    private String cityName;

    private Long ownerId;

    public ForSaleOwnerRequest(Long id, Date listingDate, String advertTitle, Long price, ImmovablesTypes immovablesTypes, String numberOfRooms, int buildingAge, Boolean balcony, Boolean furnished) {
        this.id = id;
        this.listingDate = listingDate;
        this.advertTitle = advertTitle;
        this.price = price;
        this.immovablesTypes = immovablesTypes;
        this.numberOfRooms = numberOfRooms;
        this.buildingAge = buildingAge;
        this.balcony = balcony;
        this.furnished = furnished;
    }
    public static ForSaleOwnerRequest convert(ForSale forSale){
        return new ForSaleOwnerRequest(forSale.getId(), forSale.getListingDate(), forSale.getAdvertTitle(), forSale.getPrice(), forSale.getImmovablesTypes(), forSale.getNumberOfRooms(), forSale.getBuildingAge(), forSale.getBalcony(), forSale.getFurnished());
    }
}
