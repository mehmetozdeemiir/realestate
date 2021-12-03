package com.graduationproject.realestate.business.abstracts;

import com.graduationproject.realestate.request.CityRequest;

import java.util.List;

public interface CityService {
    CityRequest addCity(CityRequest cityRequest);
    CityRequest updateCity(Long id,CityRequest cityRequest);
    void deleteCity(Long id);
    List<CityRequest> getAllCity();
}
