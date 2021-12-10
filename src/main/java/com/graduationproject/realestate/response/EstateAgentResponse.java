package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.EstateAgent;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstateAgentResponse {
    private String companyName;
    private String contactNumber;
    private String cityName;

    public static EstateAgentResponse from(EstateAgent estateAgent){
        return EstateAgentResponse.builder()
                .companyName(estateAgent.getCompanyName())
                .contactNumber(estateAgent.getContactNumber())
                .cityName(estateAgent.getCity().getCityName())
                .build();
    }
}
