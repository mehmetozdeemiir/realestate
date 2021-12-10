package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.ForRentOwnerService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.entities.ForRentOwner;
import com.graduationproject.realestate.entities.Owner;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.repository.ForRentOwnerRepository;
import com.graduationproject.realestate.repository.OwnerRepository;
import com.graduationproject.realestate.request.ForRentOwnerRequest;
import com.graduationproject.realestate.response.ForRentOwnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForRentOwnerManager implements ForRentOwnerService {
    private final ForRentOwnerRepository forRentOwnerRepository;
    private final OwnerRepository ownerRepository;
    private final CityRepository cityRepository;

    @Override
    public ForRentOwnerResponse addOwnerRent(ForRentOwnerRequest forRentOwnerRequest) {
        Owner owner = ownerRepository.findById(forRentOwnerRequest.getOwnerId()).get();
        City city =cityRepository.findByCityNameAndDistrict(forRentOwnerRequest.getCityName(), forRentOwnerRequest.getDistrict());
        ForRentOwner forRent=forRentOwnerRepository.save(new ForRentOwner(forRentOwnerRequest.getListingDate(),
                forRentOwnerRequest.getAdvertTitle(), forRentOwnerRequest.getPrice(),
                forRentOwnerRequest.getImmovablesTypes(), forRentOwnerRequest.getNumberOfRooms(),
                forRentOwnerRequest.getBuildingAge(), forRentOwnerRequest.getBalcony(),
                forRentOwnerRequest.getFurnished(), owner,city));
        return ForRentOwnerResponse.from(forRent);
    }

    @Override
    public ForRentOwnerResponse updateOwnerRent(Long id, ForRentOwnerRequest forRentOwnerRequest) {
        ForRentOwner forRent =forRentOwnerRepository.findById(id).orElseThrow(()->new ApiRequestException("Güncellenemedi. İlgili kayıt bulunamadı"));
        forRent.setListingDate(forRentOwnerRequest.getListingDate());
        forRent.setAdvertTitle(forRentOwnerRequest.getAdvertTitle());
        forRent.setPrice(forRentOwnerRequest.getPrice());
        forRent.setImmovablesTypes(forRentOwnerRequest.getImmovablesTypes());
        forRent.setNumberOfRooms(forRentOwnerRequest.getNumberOfRooms());
        forRent.setBuildingAge(forRentOwnerRequest.getBuildingAge());
        forRent.setBalcony(forRentOwnerRequest.getBalcony());
        forRent.setFurnished(forRentOwnerRequest.getFurnished());
        ForRentOwner updatedRent= forRentOwnerRepository.save(forRent);
        return ForRentOwnerResponse.from(updatedRent);
    }
/*
    @Override
    public List<ForRentCityResponse> getAllForRentCityWithParam(Optional<Long> cityId) {
        return cityId.map(forRentOwnerRepository::findByCityId)
                .orElseGet(forRentOwnerRepository::findAll)
                .stream()
                .map(ForRentCityResponse::from)
                .collect(Collectors.toList());
    }

 */
    @Override
    public List<ForRentOwnerResponse> getAllForRentOwnerWithParam(Optional<Long> ownerId) {
        return ownerId.map(forRentOwnerRepository::findByOwnerId)
                .orElseGet(forRentOwnerRepository::findAll)
                .stream()
                .map(ForRentOwnerResponse::from)
                .collect(Collectors.toList());
    }


    @Override
    public void deleteRent(Long id) {
        ForRentOwner forRent=forRentOwnerRepository.getById(id);
        forRentOwnerRepository.deleteById(forRent.getId());
    }
}
