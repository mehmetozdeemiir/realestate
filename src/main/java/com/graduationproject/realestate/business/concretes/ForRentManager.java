package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.ForRentService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.entities.EstateAgent;
import com.graduationproject.realestate.entities.ForRent;
import com.graduationproject.realestate.entities.Owner;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.repository.EstateAgentRepository;
import com.graduationproject.realestate.repository.ForRentRepository;
import com.graduationproject.realestate.repository.OwnerRepository;
import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import com.graduationproject.realestate.request.ForRentOwnerRequest;
import com.graduationproject.realestate.response.ForRentCityResponse;
import com.graduationproject.realestate.response.ForRentEstateAgentResponse;
import com.graduationproject.realestate.response.ForRentOwnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForRentManager implements ForRentService {

   private final ForRentRepository forRentRepository;
   private final OwnerRepository ownerRepository;
   private final CityRepository cityRepository;
   private final EstateAgentRepository estateAgentRepository;

    @Override
    public ForRentOwnerRequest addOwnerRent(ForRentOwnerRequest forRentOwnerRequest) {
        Owner owner = ownerRepository.findById(forRentOwnerRequest.getOwnerId()).get();
        City city =cityRepository.findByCityNameAndDistrict(forRentOwnerRequest.getCityName(), forRentOwnerRequest.getDistrict());
        ForRent forRent=forRentRepository.save(new ForRent(forRentOwnerRequest.getId(), forRentOwnerRequest.getListingDate(),
                forRentOwnerRequest.getAdvertTitle(), forRentOwnerRequest.getPrice(),
                forRentOwnerRequest.getImmovablesTypes(), forRentOwnerRequest.getNumberOfRooms(),
                forRentOwnerRequest.getBuildingAge(), forRentOwnerRequest.getBalcony(),
                forRentOwnerRequest.getFurnished(),owner,city));
        return ForRentOwnerRequest.convert(forRent);
    }

    @Override
    public ForRentOwnerRequest updateOwnerRent(Long id, ForRentOwnerRequest forRentOwnerRequest) {
        ForRent forRent =forRentRepository.findById(id).orElseThrow(()->new ApiRequestException("Güncellenemedi. İlgili kayıt bulunamadı"));
        forRent.setListingDate(forRentOwnerRequest.getListingDate());
        forRent.setAdvertTitle(forRentOwnerRequest.getAdvertTitle());
        forRent.setPrice(forRentOwnerRequest.getPrice());
        forRent.setImmovablesTypes(forRentOwnerRequest.getImmovablesTypes());
        forRent.setNumberOfRooms(forRentOwnerRequest.getNumberOfRooms());
        forRent.setBuildingAge(forRentOwnerRequest.getBuildingAge());
        forRent.setBalcony(forRentOwnerRequest.getBalcony());
        forRent.setFurnished(forRentOwnerRequest.getFurnished());
        ForRent updatedRent= forRentRepository.save(forRent);
        return ForRentOwnerRequest.convert(updatedRent);
    }

    @Override
    public ForRentEstateAgentRequest addEstateAgentRent(ForRentEstateAgentRequest forRentEstateAgentRequest) {
        EstateAgent estateAgent = estateAgentRepository.findById(forRentEstateAgentRequest.getEstateAgentId()).get();
        City city =cityRepository.findByCityNameAndDistrict(forRentEstateAgentRequest.getCityName(), forRentEstateAgentRequest.getDistrict());
        ForRent forRent=forRentRepository.save(new ForRent(forRentEstateAgentRequest.getId(), forRentEstateAgentRequest.getListingDate(),
                forRentEstateAgentRequest.getAdvertTitle(), forRentEstateAgentRequest.getPrice(),
                forRentEstateAgentRequest.getImmovablesTypes(), forRentEstateAgentRequest.getNumberOfRooms(),
                forRentEstateAgentRequest.getBuildingAge(), forRentEstateAgentRequest.getBalcony(),
                forRentEstateAgentRequest.getFurnished(),estateAgent,city));
        return ForRentEstateAgentRequest.convert(forRent);
    }

    @Override
    public ForRentEstateAgentRequest updateEstateAgentRent(Long id, ForRentEstateAgentRequest forRentEstateAgentRequest) {
        ForRent forRent =forRentRepository.findById(id).orElseThrow(()->new ApiRequestException("Güncellenemedi. İlgili kayıt bulunamadı"));
        forRent.setListingDate(forRentEstateAgentRequest.getListingDate());
        forRent.setAdvertTitle(forRentEstateAgentRequest.getAdvertTitle());
        forRent.setPrice(forRentEstateAgentRequest.getPrice());
        forRent.setImmovablesTypes(forRentEstateAgentRequest.getImmovablesTypes());
        forRent.setNumberOfRooms(forRentEstateAgentRequest.getNumberOfRooms());
        forRent.setBuildingAge(forRentEstateAgentRequest.getBuildingAge());
        forRent.setBalcony(forRentEstateAgentRequest.getBalcony());
        forRent.setFurnished(forRentEstateAgentRequest.getFurnished());
        ForRent updatedRent= forRentRepository.save(forRent);
        return ForRentEstateAgentRequest.convert(updatedRent);
    }

    @Override
    public List<ForRentCityResponse> getAllForRentCityWithParam(Optional<Long> cityId) {
        List<ForRent> forRents;
        if(cityId.isPresent()){
            forRents=forRentRepository.findByCityId(cityId.get());
        }
        else
            forRents=forRentRepository.findAll();
        return forRents.stream().map(f->new ForRentCityResponse(f)).collect(Collectors.toList());
    }

    @Override
    public List<ForRentOwnerResponse> getAllForRentOwnerWithParam(Optional<Long> ownerId) {
        List<ForRent> forRents;
        if(ownerId.isPresent()){
            forRents=forRentRepository.findByOwnerId(ownerId.get());
        }else
            forRents=forRentRepository.findAll();
        return forRents.stream().map(f->new ForRentOwnerResponse(f)).collect(Collectors.toList());
    }

    @Override
    public List<ForRentEstateAgentResponse> getAllForRentEstateAgentWithParam(Optional<Long> estateAgentId) {
        List<ForRent> forRents;
        if(estateAgentId.isPresent()){
            forRents=forRentRepository.findByEstateAgentId(estateAgentId.get());
        }else
            forRents=forRentRepository.findAll();
        return forRents.stream().map(f->new ForRentEstateAgentResponse(f)).collect(Collectors.toList());
    }

    //DELETE İSLEMİNİ SOR İKİ
    @Override
    public void delete(Long id) {
        ForRent forRent=forRentRepository.getById(id);
        forRentRepository.deleteById(forRent.getId());
    }
}
