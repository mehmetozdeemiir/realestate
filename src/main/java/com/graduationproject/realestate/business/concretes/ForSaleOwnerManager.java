package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.ForSaleOwnerService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.entities.ForSaleOwner;
import com.graduationproject.realestate.entities.ProductType;
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
    private final OwnerManager ownerManager;
    private final CityManager cityManager;

    @Override
    public ForSaleOwnerResponse addOwnerSale(ForSaleOwnerRequest forSaleOwnerRequest) {
        Owner owner = ownerManager.findById(forSaleOwnerRequest.getOwnerId());
        City city =cityManager.findByCityNameAndDistrict(forSaleOwnerRequest.getCityName(), forSaleOwnerRequest.getDistrict());
        ForSaleOwner forSale=forSaleOwnerRepository.save(new ForSaleOwner( forSaleOwnerRequest.getListingDate(),
                forSaleOwnerRequest.getAdvertTitle(), forSaleOwnerRequest.getPrice(),
                forSaleOwnerRequest.getProductType(), forSaleOwnerRequest.getNumberOfRooms(),
                forSaleOwnerRequest.getBuildingAge(), forSaleOwnerRequest.getBalcony(),
                forSaleOwnerRequest.getFurnished(),owner,city));
        return ForSaleOwnerResponse.from(forSale);
    }

    @Override
    public ForSaleOwnerResponse updateOwnerSale(Long id, ForSaleOwnerRequest forSaleOwnerRequest) {
        ForSaleOwner forSale =forSaleOwnerRepository.findById(id).orElseThrow(()->new ApiRequestException("Could not be updated . No data found"));
        forSale.setListingDate(forSaleOwnerRequest.getListingDate());
        forSale.setAdvertTitle(forSaleOwnerRequest.getAdvertTitle());
        forSale.setPrice(forSaleOwnerRequest.getPrice());
        forSale.setProductType(forSaleOwnerRequest.getProductType());
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
    public List<ForSaleOwnerResponse> findAllByProductType(ProductType productType, Pageable pageable) {
        return forSaleOwnerRepository
                .findAllByProductType(productType,pageable)
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

}
