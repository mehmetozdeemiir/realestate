package com.graduationproject.realestate.response;
import com.graduationproject.realestate.entities.EstateAgent;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class EstateAgentConverter {
    public EstateAgentResponse from(EstateAgent estateAgent){
        return EstateAgentResponse.builder()
                .companyName(estateAgent.getCompanyName())
                .contactNumber(estateAgent.getContactNumber())
                .cityName(estateAgent.getCity().getCityName())
                .build();
    }
    public List<EstateAgentResponse> fromList(List<EstateAgent> fromList){
        return fromList.stream().map(estateAgent -> new EstateAgentResponse(
                estateAgent.getCompanyName(),
                estateAgent.getContactNumber(),
                estateAgent.getCity().getCityName())).collect(Collectors.toList());
    }

}
