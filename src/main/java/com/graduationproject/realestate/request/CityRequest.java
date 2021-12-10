package com.graduationproject.realestate.request;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Data
public class CityRequest {
    @NotEmpty(message = "Şehir alanı boş bırakılamaz")
    @Size(min = 2,max = 40)
    private String cityName;

    @NotEmpty(message = " Semt alanı boş bırakılamaz")
    @Size(min = 2,max = 40)
    private String district;

    public CityRequest( String cityName, String district) {
        this.cityName = cityName;
        this.district = district;
    }
}
