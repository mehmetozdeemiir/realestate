package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.CityService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.request.CityRequest;
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
        City city=cityRepository.findById(id).orElseThrow(()->new ApiRequestException("Güncellenemedi. İlgili kayıt bulunamadı"));
        City updatedCity= new City(city.getId(),
                city.getCityName(),
                city.getDistrict());
        return cityResponseConverter.from(cityRepository.save(updatedCity));
    }

    @Override
    public void deleteCity(Long id) {
        City city = cityRepository.findById(id).orElseThrow(()->new ApiRequestException("Silinemedi"));
        cityRepository.deleteById(city.getId());
    }

    @Override
    public List<CityResponse> getAllCity() {
        return cityResponseConverter.fromList(cityRepository.findAll());
    }
}