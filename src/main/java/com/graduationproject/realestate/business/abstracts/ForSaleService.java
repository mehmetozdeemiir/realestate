package com.graduationproject.realestate.business.abstracts;

import com.graduationproject.realestate.request.ForSaleEstateAgentRequest;
import com.graduationproject.realestate.request.ForSaleOwnerRequest;
import com.graduationproject.realestate.response.ForSaleCityResponse;
import com.graduationproject.realestate.response.ForSaleEstateAgentResponse;
import com.graduationproject.realestate.response.ForSaleOwnerResponse;

import java.util.List;
import java.util.Optional;

public interface ForSaleService {
    ForSaleOwnerRequest addOwnerSale(ForSaleOwnerRequest forSaleOwnerRequest);
    ForSaleOwnerRequest updateOwnerSale(Long id, ForSaleOwnerRequest forSaleOwnerRequest);

    ForSaleEstateAgentRequest addEstateAgentSale(ForSaleEstateAgentRequest forSaleEstateAgentRequest);
    ForSaleEstateAgentRequest updateEstateAgentSale(Long id, ForSaleEstateAgentRequest forSaleEstateAgentRequest);

    List<ForSaleOwnerResponse> getAllForSaleOwnerWithParam(Optional<Long> ownerId);
    List<ForSaleEstateAgentResponse> getAllForSaleEstateAgentWithParam(Optional<Long> estateAgentId);
    List<ForSaleCityResponse> getAllForSaleCityWithParam(Optional<Long> cityId);

    void delete(Long id);
}
