package com.graduationproject.realestate.request;

import com.graduationproject.realestate.entities.ImmovablesTypes;
import lombok.Data;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
public class ForSaleEstateAgentRequest {
    @NotNull(message = "Oluşturulma tarihi alanı boş bırakılamaz")
    private LocalDate listingDate;

    @NotNull(message = "İlan başlığı boş bırakılamaz")
    private String advertTitle;

    @NotNull(message = "Fiyat alanı boş bırakılamaz")
    private Long price;

    @Enumerated(EnumType.STRING)
    private ImmovablesTypes immovablesTypes;

    @NotNull(message = "Oda sayısı alanı boş bırakılmaz")
    private String numberOfRooms;


    private int buildingAge;

    private Boolean balcony;

    private Boolean furnished;

    private String district;

    private String cityName;

    private Long estateAgentId;

    public ForSaleEstateAgentRequest( LocalDate listingDate, String advertTitle, Long price, ImmovablesTypes immovablesTypes, String numberOfRooms, int buildingAge, Boolean balcony, Boolean furnished) {
        this.listingDate = listingDate;
        this.advertTitle = advertTitle;
        this.price = price;
        this.immovablesTypes = immovablesTypes;
        this.numberOfRooms = numberOfRooms;
        this.buildingAge = buildingAge;
        this.balcony = balcony;
        this.furnished = furnished;
    }

}
