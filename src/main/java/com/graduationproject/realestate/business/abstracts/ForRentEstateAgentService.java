package com.graduationproject.realestate.business.abstracts;
import com.graduationproject.realestate.entities.ProductType;
import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import com.graduationproject.realestate.response.ForRentEstateAgentResponse;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ForRentEstateAgentService {
    ForRentEstateAgentResponse addEstateAgentRent(ForRentEstateAgentRequest forRentEstateAgentRequest);

    ForRentEstateAgentResponse updateEstateAgentRent(Long id, ForRentEstateAgentRequest forRentEstateAgentRequest);

    List<ForRentEstateAgentResponse> getAllForRentEstateAgentWithParam(Optional<Long> estateAgentId);

    List<ForRentEstateAgentResponse> getAllByPrice(Long price,int no , int size);

    List<ForRentEstateAgentResponse> findAllByProductType(ProductType productType, Pageable pageable);

    List<ForRentEstateAgentResponse> getAllByListingDate(LocalDate listingDate,Pageable pageable);

    List<ForRentEstateAgentResponse> findByCityNameAndDistrictAndPrice (Long price,String cityName, String district, Pageable pageable);

    List<ForRentEstateAgentResponse> bigFilter(Long price, int buildingAge, Boolean balcony, Boolean furnished, String cityName, String district,Pageable pageable);

    void deleteRent(Long id);
}
