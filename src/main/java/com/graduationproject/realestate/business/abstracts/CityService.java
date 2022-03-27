package com.graduationproject.realestate.business.abstracts;

import com.graduationproject.realestate.request.CityRequest;
import com.graduationproject.realestate.response.CityResponse;
import java.util.List;

public interface CityService {

    CityResponse addCity(CityRequest cityRequest);

    CityResponse updateCity(Long id, CityRequest cityRequest);

    void deleteCity(Long id);

    List<CityResponse> getAllCity();
}
