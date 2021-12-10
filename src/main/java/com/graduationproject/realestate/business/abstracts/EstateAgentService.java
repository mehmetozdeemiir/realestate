package com.graduationproject.realestate.business.abstracts;

import com.graduationproject.realestate.request.EstateAgentRequest;
import com.graduationproject.realestate.response.EstateAgentResponse;

import java.util.List;

public interface EstateAgentService  {

    EstateAgentResponse addEstateAgent(EstateAgentRequest estateAgentRequest);
    EstateAgentResponse updateEstateAgent(Long id , EstateAgentRequest estateAgentRequest);
    void deleteEstateAgent(Long id);
    List<EstateAgentResponse> getAllEstateAgent();
}
