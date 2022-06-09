package com.graduationproject.realestate;

import com.graduationproject.realestate.entities.*;
import com.graduationproject.realestate.request.CityRequest;
import com.graduationproject.realestate.request.EstateAgentRequest;
import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import com.graduationproject.realestate.response.CityResponse;
import com.graduationproject.realestate.response.EstateAgentResponse;
import com.graduationproject.realestate.response.ForRentEstateAgentResponse;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestSupport {
/*
                                     City Test Support
 */
    public static Long id=100L;

    public static City generateCity(){
        return new City(id,
                "cityName" + id,
                "district" + id
                );
    }

    public static List<City> generateListCity(){
        return IntStream.range(0,5).mapToObj(i-> new City(
                "cityName"+ i,
                "district"+ i)
        ).collect(Collectors.toList());
    }

    public static List<CityResponse> generateCitiesResponseList(List<City> cityList){
        return cityList.stream().map(from-> new CityResponse( from.getCityName(),
                from.getDistrict())).collect(Collectors.toList());
    }

    public static CityRequest cityRequestSupport=new CityRequest("İstanbul","Beşiktaş");

    public static City entitySupport=City.builder()
            .cityName(cityRequestSupport.getCityName())
            .district(cityRequestSupport.getDistrict())
            .build();

    public static CityResponse cityResponseSupport=CityResponse.builder()
            .cityName("İstanbul")
            .district("Beşiktaş")
            .build();

/*
                     ESTATE AGENT TEST SUPPORT
 */
   public static City cityGenerate=City.builder()
            .id(11L)
            .district("Şirinevler")
            .cityName("İstanbul")
            .build();

    public static EstateAgentResponse estateAgentResponseSupport=EstateAgentResponse.builder()
            .companyName("emlaks")
            .contactNumber("05548688280")
            .cityName("İstanbul")
            .build();

    public static EstateAgentRequest estateAgentRequestSupport=new EstateAgentRequest("emlaks","05548688280","İstanbul");

   public static EstateAgent estateAgentEntity= EstateAgent.builder()
            .companyName(estateAgentRequestSupport.getCompanyName())
            .contactNumber(estateAgentRequestSupport.getContactNumber())
            .city(cityGenerate)
            .build();

    public static EstateAgent estateAgentNotRequest =new EstateAgent(id,"İstanbul","05548688280",cityGenerate);
    public static EstateAgent estateagentlazim=new EstateAgent(id,"İstanbul","05548688280");

    public static List<EstateAgent> generateListEstateAgent(){
        return IntStream.range(0,5).mapToObj(i-> new EstateAgent(
                "companyName"+ i,
                "contactNumber"+ i,
                 cityGenerate)
        ).collect(Collectors.toList());
    }

    public static List<EstateAgentResponse> generateEstateAgentsResponseList(List<EstateAgent> estateAgentList){
        return estateAgentList.stream().map(from-> new EstateAgentResponse( from.getCompanyName(),
                from.getContactNumber(),from.getCity().getCityName())).collect(Collectors.toList());
    }

    public static ForRentEstateAgentRequest forRentEstateAgentRequestSupport=new ForRentEstateAgentRequest(LocalDate.now(),"Güzel ev",1L, ProductType.EV,"3+1",5,true,true,"Şirinevler","İstanbul",1L);

    public static ForRentEstateAgentResponse forRentEstateAgentResponseSupport=ForRentEstateAgentResponse.builder()
            .listingDate(LocalDate.now())
            .advertTitle("Güzel ev")
            .price(1L)
            .productType(ProductType.EV)
            .numberOfRooms("3+1")
            .buildingAge(5)
            .balcony(true)
            .furnished(true)
            .district("Şirinevler")
            .cityName("İstanbul")
            .estateAgentId(1L)
            .build();


    public static ForRentEstateAgent forRentEstateAgentEntity= ForRentEstateAgent.builder()
            .listingDate(forRentEstateAgentRequestSupport.getListingDate())
            .advertTitle(forRentEstateAgentResponseSupport.getAdvertTitle())
            .price(forRentEstateAgentRequestSupport.getPrice())
            .productType(forRentEstateAgentRequestSupport.getProductType())
            .numberOfRooms(forRentEstateAgentRequestSupport.getNumberOfRooms())
            .buildingAge(forRentEstateAgentRequestSupport.getBuildingAge())
            .balcony(forRentEstateAgentRequestSupport.getBalcony())
            .furnished(forRentEstateAgentRequestSupport.getFurnished())
            .city(cityGenerate)
            .estateAgent(estateAgentNotRequest)
            .build();
}


