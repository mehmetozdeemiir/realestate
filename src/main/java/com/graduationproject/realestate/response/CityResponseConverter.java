package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.City;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class CityResponseConverter {

    public CityResponse from(City city){
        return CityResponse.builder()
                .cityName(city.getCityName())
                .district(city.getDistrict())
                .build();
    }
    public List<CityResponse> fromList(List<City> fromList){
        return fromList.stream().map(city -> new CityResponse(city.getCityName(),city.getDistrict())).toList();
    }

}
