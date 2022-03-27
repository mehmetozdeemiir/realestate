package com.graduationproject.realestate.business.abstracts;

import com.graduationproject.realestate.entities.ForRentOwner;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import com.graduationproject.realestate.request.ForRentOwnerRequest;
import com.graduationproject.realestate.response.ForRentOwnerResponse;
import com.graduationproject.realestate.response.ListByCityResponseO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ForRentOwnerService {
    ForRentOwnerResponse addOwnerRent(ForRentOwnerRequest forRentOwnerRequest);

    ForRentOwnerResponse updateOwnerRent(Long id, ForRentOwnerRequest forRentOwnerRequest);

    List<ForRentOwnerResponse> getAllForRentOwnerWithParam(Optional<Long> ownerId);

    List<ListByCityResponseO> getAllForRentCityWithParam(Optional<Long> cityId);

    List<ForRentOwnerResponse> getAllByCityName(String cityName, Pageable pageable);

    List<ForRentOwnerResponse> getAllByPrice(Long price, Pageable pageable);

    List<ForRentOwnerResponse> getAllByListingDate(LocalDate listingDate, Pageable pageable);

    List<ForRentOwnerResponse> findAllByImmovablesTypes(ImmovablesTypes immovablesTypes, Pageable pageable);

    List<ForRentOwnerResponse> findByCityNameAndDistrictAndPrice(Long price,String cityName, String district,  Pageable pageable);

    List<ForRentOwnerResponse> bigFilter(Long price,int buildingAge,Boolean balcony,Boolean furnished,String cityName,String district, Pageable pageable);

    void deleteRent(Long id);

}
