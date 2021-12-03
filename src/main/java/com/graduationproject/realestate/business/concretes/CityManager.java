package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.CityService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.request.CityRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CityManager implements CityService {

    private final CityRepository cityRepository;

    @Override
    public CityRequest addCity(CityRequest cityRequest) {
        City city = cityRepository.save(new City(cityRequest.getId(),cityRequest.getCityName(), cityRequest.getDistrict()));
        return CityRequest.convert(city);
    }

    @Override
    public CityRequest updateCity(Long id, CityRequest cityRequest) {
        City city=cityRepository.findById(id).orElseThrow(()->new ApiRequestException("Güncellenemedi. İlgili kayıt bulunamadı"));
        city.setId(cityRequest.getId());
        city.setCityName(city.getCityName());
        city.setDistrict(cityRequest.getDistrict());
        City updatedCity = cityRepository.save(city);
        return CityRequest.convert(updatedCity);
    }

    @Override
    public void deleteCity(Long id) {
        City city = cityRepository.findById(id).orElseThrow(()->new ApiRequestException("Silinemedi"));
        cityRepository.deleteById(city.getId());
    }

    @Override
    public List<CityRequest> getAllCity() {
        return cityRepository.findAll().stream().map(CityRequest::convert).collect(Collectors.toList());
    }
}
