package com.graduationproject.realestate.business.abstracts;

import com.graduationproject.realestate.request.ForSaleEstateAgentRequest;
import com.graduationproject.realestate.response.ForSaleEstateAgentResponse;

import java.util.List;
import java.util.Optional;

public interface ForSaleEstateAgentService {
    ForSaleEstateAgentResponse addEstateAgentSale(ForSaleEstateAgentRequest forSaleEstateAgentRequest);
    ForSaleEstateAgentResponse updateEstateAgentSale(Long id, ForSaleEstateAgentRequest forSaleEstateAgentRequest);
    List<ForSaleEstateAgentResponse> getAllForSaleEstateAgentWithParam(Optional<Long> estateAgentId);
    void deleteSaleEstateAgent(Long id);
}
