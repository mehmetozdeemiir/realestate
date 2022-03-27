package com.graduationproject.realestate.request;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BigFilterRequest {
    private Long price;
    private int buildingAge;
    private Boolean balcony;
    private Boolean furnished;
    private String cityName;
    private String district;
}
