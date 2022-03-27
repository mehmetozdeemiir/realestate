package com.graduationproject.realestate.business.abstracts;

import com.graduationproject.realestate.entities.ImmovablesTypes;
import com.graduationproject.realestate.request.ForSaleEstateAgentRequest;
import com.graduationproject.realestate.response.ForSaleEstateAgentResponse;
import com.graduationproject.realestate.response.ListByCityResponseE;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ForSaleEstateAgentService {

    ForSaleEstateAgentResponse addEstateAgentSale(ForSaleEstateAgentRequest forSaleEstateAgentRequest);

    ForSaleEstateAgentResponse updateEstateAgentSale(Long id, ForSaleEstateAgentRequest forSaleEstateAgentRequest);

    List<ForSaleEstateAgentResponse> getAllForSaleEstateAgentWithParam(Optional<Long> estateAgentId);

    List<ListByCityResponseE> getAllForSaleCityWithParam(Optional<Long> cityId);

    List<ForSaleEstateAgentResponse> getAllByCityName(String cityName, Pageable pageable);

    List<ForSaleEstateAgentResponse> getAllByPrice(Long price,Pageable pageable);

    List<ForSaleEstateAgentResponse> getAllCheapHouseByCityName(String cityName,Pageable pageable);

    List<ForSaleEstateAgentResponse> findAllByImmovablesTypes(ImmovablesTypes immovablesTypes,Pageable pageable);

    List<ForSaleEstateAgentResponse> getAllByListingDate(LocalDate listingDate,Pageable pageable);

    List<ForSaleEstateAgentResponse> findByCityNameAndDistrictAndPrice(String cityName, String district, Long price,Pageable pageable);

    List<ForSaleEstateAgentResponse> bigFilter(Long price, int buildingAge, Boolean balcony, Boolean furnished, String cityName, String district,Pageable pageable);

    void deleteSaleEstateAgent(Long id);
}
