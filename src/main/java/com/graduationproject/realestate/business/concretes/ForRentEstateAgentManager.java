package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.ForRentEstateAgentService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.entities.EstateAgent;
import com.graduationproject.realestate.entities.ForRentEstateAgent;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.repository.EstateAgentRepository;
import com.graduationproject.realestate.repository.ForRentEstateAgentRepository;
import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import com.graduationproject.realestate.response.ForRentCityResponse;
import com.graduationproject.realestate.response.ForRentEstateAgentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForRentEstateAgentManager implements ForRentEstateAgentService {

    private final ForRentEstateAgentRepository forRentEstateAgentRepository;
    private final CityRepository cityRepository;
    private final EstateAgentRepository estateAgentRepository;


    @Override
    public ForRentEstateAgentResponse addEstateAgentRent(ForRentEstateAgentRequest forRentEstateAgentRequest) {
        EstateAgent estateAgent = estateAgentRepository.findById(forRentEstateAgentRequest.getEstateAgentId()).get();
        City city =cityRepository.findByCityNameAndDistrict(forRentEstateAgentRequest.getCityName(), forRentEstateAgentRequest.getDistrict());
        ForRentEstateAgent forRent=forRentEstateAgentRepository.save(new ForRentEstateAgent( forRentEstateAgentRequest.getListingDate(),
                forRentEstateAgentRequest.getAdvertTitle(), forRentEstateAgentRequest.getPrice(),
                forRentEstateAgentRequest.getImmovablesTypes(), forRentEstateAgentRequest.getNumberOfRooms(),
                forRentEstateAgentRequest.getBuildingAge(), forRentEstateAgentRequest.getBalcony(),
                forRentEstateAgentRequest.getFurnished(),estateAgent,city));
        return ForRentEstateAgentResponse.from(forRent);
    }

    @Override
    public ForRentEstateAgentResponse updateEstateAgentRent(Long id, ForRentEstateAgentRequest forRentEstateAgentRequest) {
        ForRentEstateAgent forRent =forRentEstateAgentRepository.findById(id).orElseThrow(()->new ApiRequestException("Güncellenemedi. İlgili kayıt bulunamadı"));
        forRent.setListingDate(forRentEstateAgentRequest.getListingDate());
        forRent.setAdvertTitle(forRentEstateAgentRequest.getAdvertTitle());
        forRent.setPrice(forRentEstateAgentRequest.getPrice());
        forRent.setImmovablesTypes(forRentEstateAgentRequest.getImmovablesTypes());
        forRent.setNumberOfRooms(forRentEstateAgentRequest.getNumberOfRooms());
        forRent.setBuildingAge(forRentEstateAgentRequest.getBuildingAge());
        forRent.setBalcony(forRentEstateAgentRequest.getBalcony());
        forRent.setFurnished(forRentEstateAgentRequest.getFurnished());
        ForRentEstateAgent updatedRent= forRentEstateAgentRepository.save(forRent);
        return ForRentEstateAgentResponse.from(updatedRent);
    }

    /*@Override
    public List<ForRentCityResponse> getAllForRentCityWithParam(Optional<Long> cityId) {
        return cityId.map(forRentEstateAgentRepository::findByCityId)
                .orElseGet(forRentEstateAgentRepository::findAll)
                .stream()
                .map(ForRentCityResponse::from)
                .collect(Collectors.toList());
    }

     */

    @Override
    public List<ForRentEstateAgentResponse> getAllForRentEstateAgentWithParam(Optional<Long> estateAgentId) {
        return estateAgentId.map(forRentEstateAgentRepository::findByEstateAgentId)
                .orElseGet(forRentEstateAgentRepository::findAll)
                .stream()
                .map(ForRentEstateAgentResponse::from)
                .collect(Collectors.toList());
    }
    @Override
    public void deleteRent(Long id) {
        ForRentEstateAgent forRent=forRentEstateAgentRepository.getById(id);
        forRentEstateAgentRepository.deleteById(forRent.getId());
    }


}
