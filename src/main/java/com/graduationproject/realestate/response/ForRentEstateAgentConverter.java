package com.graduationproject.realestate.response;
import com.graduationproject.realestate.entities.ForRentEstateAgent;
import lombok.Data;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
@Data
public class ForRentEstateAgentConverter {

    public ForRentEstateAgentResponse from(ForRentEstateAgent forRent){
        return ForRentEstateAgentResponse.builder()
                .listingDate(forRent.getListingDate())
                .advertTitle(forRent.getAdvertTitle())
                .price(forRent.getPrice())
                .productType(forRent.getProductType())
                .numberOfRooms(forRent.getNumberOfRooms())
                .buildingAge(forRent.getBuildingAge())
                .balcony(forRent.getBalcony())
                .furnished(forRent.getFurnished())
                .district(forRent.getCity().getDistrict())
                .cityName(forRent.getCity().getCityName())
                .estateAgentId(forRent.getEstateAgent().getId())
                .build();
    }

    public List<ForRentEstateAgentResponse> fromList(List<ForRentEstateAgent> fromList){
        return fromList.stream().map(forRentEstateAgent -> new ForRentEstateAgentResponse(
                forRentEstateAgent.getListingDate(),
                forRentEstateAgent.getAdvertTitle(),
                forRentEstateAgent.getPrice(),
                forRentEstateAgent.getProductType(),
                forRentEstateAgent.getNumberOfRooms(),
                forRentEstateAgent.getBuildingAge(),
                forRentEstateAgent.getBalcony(),
                forRentEstateAgent.getFurnished(),
                forRentEstateAgent.getCity().getDistrict(),
                forRentEstateAgent.getCity().getCityName(),
                forRentEstateAgent.getEstateAgent().getId()
               )).collect(Collectors.toList());
    }

}
