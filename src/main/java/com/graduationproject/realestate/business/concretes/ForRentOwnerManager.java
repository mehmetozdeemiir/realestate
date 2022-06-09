package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.ForRentOwnerService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.entities.ForRentOwner;
import com.graduationproject.realestate.entities.ProductType;
import com.graduationproject.realestate.entities.Owner;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.ForRentOwnerRepository;
import com.graduationproject.realestate.request.ForRentOwnerRequest;
import com.graduationproject.realestate.response.ForRentOwnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


@Service
@RequiredArgsConstructor
public class ForRentOwnerManager implements ForRentOwnerService {

    private final ForRentOwnerRepository forRentOwnerRepository;
    private final OwnerManager ownerManager;
    private final CityManager cityManager;

    @Override
    public ForRentOwnerResponse addOwnerRent(ForRentOwnerRequest forRentOwnerRequest) {
        Owner owner = ownerManager.findById(forRentOwnerRequest.getOwnerId());
        City city =cityManager.findByCityNameAndDistrict(forRentOwnerRequest.getCityName(), forRentOwnerRequest.getDistrict());
        ForRentOwner forRent=forRentOwnerRepository.save(new ForRentOwner(forRentOwnerRequest.getListingDate(),
                forRentOwnerRequest.getAdvertTitle(), forRentOwnerRequest.getPrice(),
                forRentOwnerRequest.getProductType(), forRentOwnerRequest.getNumberOfRooms(),
                forRentOwnerRequest.getBuildingAge(), forRentOwnerRequest.getBalcony(),
                forRentOwnerRequest.getFurnished(), owner,city));
        return ForRentOwnerResponse.from(forRent);
    }

    @Override
    public ForRentOwnerResponse updateOwnerRent(Long id, ForRentOwnerRequest forRentOwnerRequest) {
        ForRentOwner forRent =forRentOwnerRepository.findById(id).orElseThrow(()->new ApiRequestException("Could not be updated . No data found"+id));
        forRent.setListingDate(forRentOwnerRequest.getListingDate());
        forRent.setAdvertTitle(forRentOwnerRequest.getAdvertTitle());
        forRent.setPrice(forRentOwnerRequest.getPrice());
        forRent.setProductType(forRentOwnerRequest.getProductType());
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
                .toList();
    }

    @Override
    public List<ForRentOwnerResponse> getAllByCityName(String cityName, Pageable pageable) {
        return forRentOwnerRepository.
                findAllByCity_CityName(cityName,pageable)
                .stream()
                .map(ForRentOwnerResponse::from)
                .toList();
    }

    @Override
    public List<ForRentOwnerResponse> getAllByPrice(Long price, Pageable pageable) {
        return forRentOwnerRepository
                .findAllByPriceLessThanEqual(price,pageable)
                .stream()
                .map(ForRentOwnerResponse::from)
                .toList();
    }

    @Override
    public List<ForRentOwnerResponse> getAllByListingDate(LocalDate listingDate, Pageable pageable) {
        return forRentOwnerRepository
                .findAllByListingDateLessThanEqual(listingDate,pageable)
                .stream()
                .map(ForRentOwnerResponse::from)
                .toList();
    }

    @Override
    public List<ForRentOwnerResponse> findAllByProductType(ProductType productType, Pageable pageable) {
        return forRentOwnerRepository
                .findAllByProductType(productType,pageable)
                .stream()
                .map(ForRentOwnerResponse::from)
                .toList();
    }

    @Override
    public List<ForRentOwnerResponse> findByCityNameAndDistrictAndPrice(Long price,String cityName, String district,  Pageable pageable) {
        return forRentOwnerRepository
                .findAllByPriceLessThanEqualAndCity_CityNameAndCity_District(price,cityName, district, pageable)
                .stream()
                .map(ForRentOwnerResponse::from)
                .toList();
    }

    @Override
    public List<ForRentOwnerResponse> bigFilter(Long price, int buildingAge, Boolean balcony, Boolean furnished, String cityName, String district, Pageable pageable) {
        return forRentOwnerRepository
                .findAllByPriceLessThanEqualAndBuildingAgeIsLessThanEqualAndBalconyAndFurnishedAndCity_CityNameAndCity_District(price, buildingAge, balcony, furnished, cityName, district,pageable)
                .stream()
                .map(ForRentOwnerResponse::from)
                .toList();
    }

    @Override
    public void deleteRent(Long id) {
        ForRentOwner forRent=forRentOwnerRepository.getById(id);
        forRentOwnerRepository.deleteById(forRent.getId());
    }
}
