package com.graduationproject.realestate.request;

import com.graduationproject.realestate.entities.City;
import lombok.Data;

@Data
public class CityRequest {
    private Long id;
    private String cityName;
    private String district;


    public CityRequest(Long id, String cityName, String district) {
        this.id = id;
        this.cityName = cityName;
        this.district = district;
    }

    public static CityRequest convert(City city){
        return new CityRequest(city.getId(),city.getCityName(),city.getDistrict());
    }
}
