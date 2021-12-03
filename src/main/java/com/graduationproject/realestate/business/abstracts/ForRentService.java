package com.graduationproject.realestate.business.abstracts;

import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import com.graduationproject.realestate.request.ForRentOwnerRequest;
import com.graduationproject.realestate.response.ForRentCityResponse;
import com.graduationproject.realestate.response.ForRentEstateAgentResponse;
import com.graduationproject.realestate.response.ForRentOwnerResponse;
import java.util.List;
import java.util.Optional;

public interface ForRentService {
    ForRentOwnerRequest addOwnerRent(ForRentOwnerRequest forRentOwnerRequest);
    ForRentOwnerRequest updateOwnerRent(Long id, ForRentOwnerRequest forRentOwnerRequest);

    ForRentEstateAgentRequest addEstateAgentRent(ForRentEstateAgentRequest forRentEmlakciRequest);
    ForRentEstateAgentRequest updateEstateAgentRent(Long id, ForRentEstateAgentRequest forRentEmlakciRequest);


    List<ForRentCityResponse> getAllForRentCityWithParam(Optional<Long> cityId);
    List<ForRentOwnerResponse> getAllForRentOwnerWithParam(Optional<Long> ownerId);
    List<ForRentEstateAgentResponse> getAllForRentEstateAgentWithParam(Optional<Long> estateAgentId);

    void delete(Long id);//patladım
}
