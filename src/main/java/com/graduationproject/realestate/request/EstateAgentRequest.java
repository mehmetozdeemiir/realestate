package com.graduationproject.realestate.request;

import com.graduationproject.realestate.entities.EstateAgent;
import lombok.Data;

@Data
public class EstateAgentRequest {
    private Long id;
    private String companyName;
    private String contactNumber;
    private String cityName;

    public EstateAgentRequest(Long id, String companyName, String contactNumber) {
        this.id = id;
        this.companyName = companyName;
        this.contactNumber = contactNumber;
    }

    public static EstateAgentRequest convert(EstateAgent estateAgent){
        return new EstateAgentRequest(estateAgent.getId(), estateAgent.getCompanyName(), estateAgent.getContactNumber());
    }
}
