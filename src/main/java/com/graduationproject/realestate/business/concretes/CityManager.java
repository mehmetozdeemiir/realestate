package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.CityService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.request.CityRequest;
import com.graduationproject.realestate.request.EstateAgentRequest;
import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import com.graduationproject.realestate.response.CityResponseConverter;
import com.graduationproject.realestate.response.CityResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CityManager implements CityService {

    private final CityRepository cityRepository;
    private final CityResponseConverter cityResponseConverter;

    @Override
    public CityResponse addCity(CityRequest cityRequest) {
            City city = cityRepository.save(new City(cityRequest.getCityName(), cityRequest.getDistrict()));
        return cityResponseConverter.from(city);
    }

    @Override
    public CityResponse updateCity(Long id, CityRequest cityRequest) {
        City city=findById(id);
        City updatedCity= new City(city.getId(),
                city.getCityName(),
                city.getDistrict());
        return cityResponseConverter.from(cityRepository.save(updatedCity));
    }

    @Override
    public void deleteCity(Long id) {
        City city = findById(id);
        cityRepository.deleteById(city.getId());
    }

    @Override
    public List<CityResponse> getAllCity() {
        return cityResponseConverter.fromList(cityRepository.findAll());
    }

    private City findById(Long id){ //tek bir yerden çağırmak için tekrara düsmemek icin private metod
        return cityRepository.findById(id).orElseThrow(()->new ApiRequestException("No records found"));
    }


    protected City findByCityName(String cityName){
        return cityRepository.findByCityName(cityName).orElseThrow(()->new ApiRequestException("No records found"));
    }
    protected City findByCityNameAndDistrict(String cityName,String district){
        return cityRepository.findByCityNameAndDistrict(cityName,district);
    }
}