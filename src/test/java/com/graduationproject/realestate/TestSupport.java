package com.graduationproject.realestate;

import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.response.CityResponse;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {

    public static Long id=100L;

    public static City generateCity(){
        return new City(id,
                "cityName" + id,
                "district" + id
                );
    }
/*
    public static CityResponse generateCityResponse(){
        return new CityResponse(id,"cityName" + id,
                "district" + id);
    }


 */
    public static List<City> generateListCity(){
        return IntStream.range(0,5).mapToObj(i-> new City(
                "cityName"+ i,
                "district"+ i)
        ).collect(Collectors.toList());
    }
    public static List<CityResponse> generateCitiesResponseList(List<City> cityList){
        return cityList.stream().map(from-> new CityResponse( from.getCityName(),
                from.getDistrict())).collect(Collectors.toList());
    }
}
