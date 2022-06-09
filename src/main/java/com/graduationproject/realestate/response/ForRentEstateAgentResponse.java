package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.ProductType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ForRentEstateAgentResponse {
    private LocalDate listingDate;
    private String advertTitle;
    private Long price;
    private ProductType productType;
    private String numberOfRooms;
    private int buildingAge;
    private Boolean balcony;
    private Boolean furnished;
    private String district;
    private String cityName;
    private Long estateAgentId;




}
