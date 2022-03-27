package com.graduationproject.realestate.business.abstracts;

import com.graduationproject.realestate.entities.ImmovablesTypes;
import com.graduationproject.realestate.request.BigFilterRequest;
import com.graduationproject.realestate.request.ForSaleOwnerRequest;
import com.graduationproject.realestate.response.ForSaleOwnerResponse;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;

public interface ForSaleOwnerService {

    ForSaleOwnerResponse addOwnerSale(ForSaleOwnerRequest forSaleOwnerRequest);

    ForSaleOwnerResponse updateOwnerSale(Long id, ForSaleOwnerRequest forSaleOwnerRequest);

    List<ForSaleOwnerResponse> under300thousand(Pageable pageable);

    List<ForSaleOwnerResponse> findAllByPrice(Long price,Pageable pageable);

    List<ForSaleOwnerResponse> findByCityName(String cityName, Pageable pageable);

    List<ForSaleOwnerResponse> getAllCheapHouseByCityName(String cityName);

    List<ForSaleOwnerResponse> findAllByImmovablesTypes(ImmovablesTypes immovablesTypes,Pageable pageable);

    List<ForSaleOwnerResponse> getAllByListingDate(LocalDate listingDate , Pageable pageable);

    List<ForSaleOwnerResponse> findByCityNameAndPrice(Long price ,String cityName, Pageable pageable);

    List<ForSaleOwnerResponse> findByCityNameAndDistrictAndPrice(Long price,String cityName, String district, Pageable pageable);

    List<ForSaleOwnerResponse> bigFilter(BigFilterRequest bigFilterRequest, Pageable pageable);

    void deleteSaleOwner(Long id);
}
