package com.graduationproject.realestate.business.abstracts;

import com.graduationproject.realestate.request.EstateAgentRequest;

import java.util.List;

public interface EstateAgentService  {

    EstateAgentRequest addEstateAgent(EstateAgentRequest estateAgentRequest);
    EstateAgentRequest updateEstateAgent(Long id , EstateAgentRequest estateAgentRequest);
    void deleteEstateAgent(Long id);
    List<EstateAgentRequest> getAllEstateAgent();
}
