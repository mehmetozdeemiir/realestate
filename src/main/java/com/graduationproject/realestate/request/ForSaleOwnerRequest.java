package com.graduationproject.realestate.request;
import com.graduationproject.realestate.entities.ProductType;
import lombok.Data;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Data
public class ForSaleOwnerRequest {
    @NotEmpty(message = "this field is required")
    private LocalDate listingDate;

    @NotEmpty(message = "this field is required")
    private String advertTitle;

    @NotEmpty(message = "this field is required")
    private Long price;

    @Enumerated(EnumType.STRING)
    private ProductType productType;

    @NotEmpty(message = "this field is required")
    private String numberOfRooms;

    @NotEmpty(message = "this field is required")
    private int buildingAge;

    private Boolean balcony;

    private Boolean furnished;

    private String district;

    private String cityName;

    private Long ownerId;
}
