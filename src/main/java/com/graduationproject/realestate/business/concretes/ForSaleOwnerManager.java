package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.ForSaleOwnerService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.entities.ForSaleOwner;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import com.graduationproject.realestate.entities.Owner;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.repository.ForSaleOwnerRepository;
import com.graduationproject.realestate.repository.OwnerRepository;
import com.graduationproject.realestate.request.BigFilterRequest;
import com.graduationproject.realestate.request.ForSaleOwnerRequest;
import com.graduationproject.realestate.response.ForSaleOwnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForSaleOwnerManager implements ForSaleOwnerService {
    private final ForSaleOwnerRepository forSaleOwnerRepository;
    private final OwnerRepository ownerRepository;
    private final CityRepository cityRepository;

    @Override
    public ForSaleOwnerResponse addOwnerSale(ForSaleOwnerRequest forSaleOwnerRequest) {
        Owner owner = ownerRepository.findById(forSaleOwnerRequest.getOwnerId()).get();
        City city =cityRepository.findByCityNameAndDistrict(forSaleOwnerRequest.getCityName(), forSaleOwnerRequest.getDistrict());
        ForSaleOwner forSale=forSaleOwnerRepository.save(new ForSaleOwner( forSaleOwnerRequest.getListingDate(),
                forSaleOwnerRequest.getAdvertTitle(), forSaleOwnerRequest.getPrice(),
                forSaleOwnerRequest.getImmovablesTypes(), forSaleOwnerRequest.getNumberOfRooms(),
                forSaleOwnerRequest.getBuildingAge(), forSaleOwnerRequest.getBalcony(),
                forSaleOwnerRequest.getFurnished(),owner,city));
        return ForSaleOwnerResponse.from(forSale);
    }

    @Override
    public ForSaleOwnerResponse updateOwnerSale(Long id, ForSaleOwnerRequest forSaleOwnerRequest) {
        ForSaleOwner forSale =forSaleOwnerRepository.findById(id).orElseThrow(()->new ApiRequestException("Güncellenemedi. İlgili kayıt bulunamadı"));
        forSale.setListingDate(forSaleOwnerRequest.getListingDate());
        forSale.setAdvertTitle(forSaleOwnerRequest.getAdvertTitle());
        forSale.setPrice(forSaleOwnerRequest.getPrice());
        forSale.setImmovablesTypes(forSaleOwnerRequest.getImmovablesTypes());
        forSale.setNumberOfRooms(forSaleOwnerRequest.getNumberOfRooms());
        forSale.setBuildingAge(forSaleOwnerRequest.getBuildingAge());
        forSale.setBalcony(forSaleOwnerRequest.getBalcony());
        forSale.setFurnished(forSaleOwnerRequest.getFurnished());
        ForSaleOwner updatedSale= forSaleOwnerRepository.save(forSale);
        return ForSaleOwnerResponse.from(updatedSale);
    }

    @Override
    public List<ForSaleOwnerResponse> under300thousand(Pageable pageable) {
        return forSaleOwnerRepository
                .under300thousand(pageable)
                .stream()
                .map(ForSaleOwnerResponse::from).toList();
    }

    @Override
    public List<ForSaleOwnerResponse> findAllByPrice(Long price,Pageable pageable) {
        return forSaleOwnerRepository
                .findAllByPriceLessThanEqual(price,pageable)
                .stream()
                .map(ForSaleOwnerResponse::from)
                .toList();
    }

    @Override
    public List<ForSaleOwnerResponse> findByCityName(String cityName,Pageable pageable) {
        return forSaleOwnerRepository
                .findAllByCity_CityName(cityName,pageable)
                .stream()
                .map(ForSaleOwnerResponse::from)
                .toList();
    }

    @Override
    public List<ForSaleOwnerResponse> getAllCheapHouseByCityName(String cityName) {
        return forSaleOwnerRepository
                .getAllCheapHouseByCityName(cityName)
                .stream().map(ForSaleOwnerResponse::from)
                .collect(Collectors.toList()); //toList kullanıcam kısa versiyonu yeni java feature ı
    }

    @Override
    public List<ForSaleOwnerResponse> findAllByImmovablesTypes(ImmovablesTypes immovablesTypes,Pageable pageable) {
        return forSaleOwnerRepository
                .findAllByImmovablesTypes(immovablesTypes,pageable)
                .stream()
                .map(ForSaleOwnerResponse::from)
                .toList();
    }

    @Override
    public List<ForSaleOwnerResponse> getAllByListingDate(LocalDate listingDate,Pageable pageable) {
        return forSaleOwnerRepository
                .findAllByListingDateLessThanEqual(listingDate,pageable)
                .stream()
                .map(ForSaleOwnerResponse::from)
                .toList();
    }

    @Override
    public List<ForSaleOwnerResponse> findByCityNameAndPrice(Long price, String cityName, Pageable pageable) {
        return forSaleOwnerRepository
                .findAllByPriceLessThanEqualAndCity_CityName(price,cityName,pageable)
                .stream()
                .map(ForSaleOwnerResponse::from)
                .toList();
    }

    @Override
    public List<ForSaleOwnerResponse> findByCityNameAndDistrictAndPrice(Long price, String cityName, String district, Pageable pageable) {
        return forSaleOwnerRepository
                .findAllByPriceLessThanEqualAndCity_CityNameAndCity_District(price,cityName, district,pageable)
                .stream()
                .map(ForSaleOwnerResponse::from)
                .toList();
    }

    @Override
    public List<ForSaleOwnerResponse> bigFilter(BigFilterRequest bigFilterRequest, Pageable pageable) {
        return forSaleOwnerRepository
                .findAllByPriceLessThanEqualAndBuildingAgeIsLessThanEqualAndBalconyAndFurnishedAndCity_CityNameAndCity_District(bigFilterRequest.getPrice(), bigFilterRequest.getBuildingAge(), bigFilterRequest.getBalcony(), bigFilterRequest.getFurnished(), bigFilterRequest.getCityName(), bigFilterRequest.getDistrict(), pageable)
                .stream()
                .map(ForSaleOwnerResponse::from)
                .toList();
    }

    @Override
    public void deleteSaleOwner(Long id) {
        ForSaleOwner forSale=forSaleOwnerRepository.getById(id);
        forSaleOwnerRepository.deleteById(forSale.getId());
    }

/*
    @Override
    public List<ForSaleOwnerResponse> getAllForSaleOwnerWithParam(Optional<Long> ownerId) {
        return ownerId.map(forSaleOwnerRepository::findByOwnerId)
                .orElseGet(forSaleOwnerRepository::findAll)
                .stream()
                .map(ForSaleOwnerResponse::from)
                .toList();
    }

    @Override
    public List<ListByCityResponseO> getAllForSaleCityWithParam(Optional<Long> cityId) {
        return cityId.map(forSaleOwnerRepository::findByCityId)
                .orElseGet(forSaleOwnerRepository::findAll)
                .stream()
                .map(ListByCityResponseO::from)
                .toList();
    }

 */

}
