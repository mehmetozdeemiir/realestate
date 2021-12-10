package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.City;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CityResponse {
    private String cityName;
    private String district;

    public static CityResponse from(City city){
        return CityResponse.builder()
                .cityName(city.getCityName())
                .district(city.getDistrict())
                .build();
    }

}
