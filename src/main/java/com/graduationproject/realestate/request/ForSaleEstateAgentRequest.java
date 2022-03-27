package com.graduationproject.realestate.request;

import com.graduationproject.realestate.entities.ImmovablesTypes;
import lombok.Data;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ForSaleEstateAgentRequest {
    @NotNull(message = "Oluşturulma tarihi alanı boş bırakılamaz")
    private LocalDate listingDate;

    @NotNull(message = "İlan başlığı boş bırakılamaz")
    private String advertTitle;

    @NotNull(message = "Fiyat alanı boş bırakılamaz")
    private Long price;

    @Enumerated(EnumType.STRING)//?? enumerated olmadan calısıyor mu
    private ImmovablesTypes immovablesTypes;

    @NotNull(message = "Oda sayısı alanı boş bırakılmaz")
    private String numberOfRooms;

    @NotNull(message = "Bina Yaşı alanı boş bırakılmaz")
    private int buildingAge;

    private Boolean balcony;

    private Boolean furnished;

    private String district;

    private String cityName;

    private Long estateAgentId;


}
