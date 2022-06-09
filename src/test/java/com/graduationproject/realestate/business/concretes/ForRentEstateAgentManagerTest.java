package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.TestSupport;
import com.graduationproject.realestate.entities.*;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.repository.EstateAgentRepository;
import com.graduationproject.realestate.repository.ForRentEstateAgentRepository;
import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import com.graduationproject.realestate.response.ForRentEstateAgentConverter;
import com.graduationproject.realestate.response.ForRentEstateAgentResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verify;

public class ForRentEstateAgentManagerTest extends TestSupport {

    private ForRentEstateAgentManager forRentEstateAgentManager;
    private ForRentEstateAgentRepository forRentEstateAgentRepository;
    private CityManager cityManager;
    private EstateAgentManager estateAgentManager;
    private ForRentEstateAgentConverter forRentEstateAgentConverter;

    @BeforeEach
    public void setUp() {
        estateAgentManager = mock(EstateAgentManager.class);
        cityManager = mock(CityManager.class);
        forRentEstateAgentRepository = mock(ForRentEstateAgentRepository.class);
        forRentEstateAgentConverter = mock(ForRentEstateAgentConverter.class);
        forRentEstateAgentManager = new ForRentEstateAgentManager(forRentEstateAgentRepository, cityManager, estateAgentManager, forRentEstateAgentConverter);
    }

    @Test
    public void testAddEstateAgentRent() {
        ForRentEstateAgentRequest forRentEstateAgentRequest = ForRentEstateAgentRequest.builder()
                .listingDate(LocalDate.now())
                .advertTitle("test")
                .price(100L)
                .productType(ProductType.EV)
                .numberOfRooms("3")
                .buildingAge(2)
                .balcony(true)
                .furnished(true)
                .estateAgentId(1L)
                .cityName("Ankara")
                .district("Çankaya")
                .build();

        ForRentEstateAgentResponse forRentEstateAgentResponse = ForRentEstateAgentResponse.builder()
                .listingDate(LocalDate.now())
                .advertTitle("test")
                .price(100L)
                .productType(ProductType.EV)
                .numberOfRooms("3")
                .buildingAge(2)
                .balcony(true)
                .furnished(true)
                .estateAgentId(1L)
                .cityName("Ankara")
                .district("Çankaya")
                .build();

        when(forRentEstateAgentRepository.save(any())).thenReturn(forRentEstateAgentResponse);

        ForRentEstateAgentResponse result = forRentEstateAgentManager.addEstateAgentRent(forRentEstateAgentRequest);

        assertEquals(forRentEstateAgentResponse, result);

    }

    @Test
        public void testCreateForRentEstateAgent_itShouldReturnEstateAgentResponse() {
            ForRentEstateAgentRequest forRentEstateAgentRequest=forRentEstateAgentRequestSupport;
            ForRentEstateAgent forRentEstateAgent=forRentEstateAgentEntity;
            City city=cityGenerate;
            EstateAgent estateAgent=estateAgentNotRequest;
            ForRentEstateAgent savedForRentEstateAgent = new ForRentEstateAgent(1L, LocalDate.now(),"Güzel ev",1L, ProductType.EV,"3+1",5,true,true,estateAgent,city);
            ForRentEstateAgentResponse forRentEstateAgentResponse=forRentEstateAgentResponseSupport;

        when(estateAgentManager.findById(id)).thenReturn(estateAgent);
        when(cityManager.findByCityNameAndDistrict("İstanbul","Şirinevler")).thenReturn(city);
        when(forRentEstateAgentRepository.save(forRentEstateAgent)).thenReturn(savedForRentEstateAgent);
        when(forRentEstateAgentConverter.from(savedForRentEstateAgent)).thenReturn(forRentEstateAgentResponse);

        ForRentEstateAgentResponse result = forRentEstateAgentManager.addEstateAgentRent(forRentEstateAgentRequest);
        Assertions.assertEquals(forRentEstateAgentResponse,result);

        verify(cityManager).findByCityNameAndDistrict("İstanbul","Şirinevler");
        verify(estateAgentManager).findById(id);
        verify(forRentEstateAgentRepository).save(forRentEstateAgent);
        verify(forRentEstateAgentConverter).from(savedForRentEstateAgent);
        }

    @Test
    void updateEstateAgentRent() {
    }

    @Test
    void getAllForRentEstateAgentWithParam() {
    }

    @Test
    void getAllForRentCityWithParam() {
    }

    @Test
    void getAllByPrice() {
    }

    @Test
    void findAllByImmovablesTypes() {
    }

    @Test
    void getAllByListingDate() {
    }

    @Test
    void findByCityNameAndDistrictAndPrice() {
    }

    @Test
    void bigFilter() {
    }

    @Test
    void deleteRent() {
    }
}