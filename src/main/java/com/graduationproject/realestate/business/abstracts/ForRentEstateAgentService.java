package com.graduationproject.realestate.business.abstracts;

import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import com.graduationproject.realestate.response.ForRentCityResponse;
import com.graduationproject.realestate.response.ForRentEstateAgentResponse;

import java.util.List;
import java.util.Optional;

public interface ForRentEstateAgentService {

    ForRentEstateAgentResponse addEstateAgentRent(ForRentEstateAgentRequest forRentEstateAgentRequest);
    ForRentEstateAgentResponse updateEstateAgentRent(Long id, ForRentEstateAgentRequest forRentEstateAgentRequest);

    List<ForRentEstateAgentResponse> getAllForRentEstateAgentWithParam(Optional<Long> estateAgentId);
    void deleteRent(Long id);
}
