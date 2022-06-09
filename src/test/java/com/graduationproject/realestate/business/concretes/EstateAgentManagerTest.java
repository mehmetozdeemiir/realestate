package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.TestSupport;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.entities.EstateAgent;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.repository.EstateAgentRepository;
import com.graduationproject.realestate.request.EstateAgentRequest;
import com.graduationproject.realestate.response.EstateAgentConverter;
import com.graduationproject.realestate.response.EstateAgentResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class EstateAgentManagerTest extends TestSupport {

    private EstateAgentManager estateAgentManager;
    private EstateAgentRepository estateAgentRepository;
    private CityManager cityManager;
    private EstateAgentConverter estateAgentConverter;


    @BeforeEach
    public void setUp() {
        estateAgentRepository = mock(EstateAgentRepository.class);
        cityManager = mock(CityManager.class);
        estateAgentConverter = mock(EstateAgentConverter.class);
        estateAgentManager = new EstateAgentManager(estateAgentRepository, cityManager, estateAgentConverter);
    }

    @Test
    void testDeleteEstateAgent() {
        EstateAgent estateAgent = new EstateAgent("test", "test");
        estateAgent.setId(1L);
        when(estateAgentRepository.findById(1L)).thenReturn(Optional.of(estateAgent));
        estateAgentManager.deleteEstateAgent(1L);
        verify(estateAgentRepository, times(1)).deleteById(1L);
    }

    /*@Test
    void testDeleteEstateAgentNotFound() {
        when(estateAgentRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(ApiRequestException.class, () -> estateAgentManager.deleteEstateAgent(1L));
    }

     */

    @Test
    public void testCreateEstateAgent_itShouldReturnEstateAgentResponse() {

        EstateAgentRequest estateAgentRequest=estateAgentRequestSupport;
        City city=cityGenerate;
        EstateAgent estateAgent= estateAgentEntity;
        EstateAgent savedEstateAgent = new EstateAgent(1L,"emlaks","05548688280",city);
        EstateAgentResponse estateAgentResponse= estateAgentResponseSupport;
        when(cityManager.findByCityName("İstanbul")).thenReturn(city);
        when(estateAgentRepository.save(estateAgent)).thenReturn(savedEstateAgent);
        when(estateAgentConverter.from(savedEstateAgent)).thenReturn(estateAgentResponse);

        EstateAgentResponse result= estateAgentManager.addEstateAgent(estateAgentRequest);
        Assertions.assertEquals(result,estateAgentResponse);

        verify(cityManager).findByCityName("İstanbul");
        verify(estateAgentRepository).save(estateAgent);
        verify(estateAgentConverter).from(savedEstateAgent);

       /* EstateAgentRequest estateAgentRequest = new EstateAgentRequest("companyName","contactNumber","cityName");
        City city=new City("cityName");
        EstateAgent estateAgent1 = new EstateAgent("companyName","contactNumber");
        EstateAgent savedEstateAgent = new EstateAgent(1L,"companyName","contactNumber",estateAgent1.getCity());
        EstateAgentResponse estateAgentResponse = new EstateAgentResponse("companyName","contactNumber",estateAgent1.getCity().getCityName());
        Mockito.when(cityRepository.findByCityName(estateAgentRequest.getCityName())).thenReturn(city);
        Mockito.when(estateAgentRepository.save(estateAgent1)).thenReturn(savedEstateAgent);
        Mockito.when(estateAgentResponse.from(savedEstateAgent)).thenReturn(estateAgentResponse);
        EstateAgentResponse result= estateAgentManager.addEstateAgent(estateAgentRequest);
        Assertions.assertEquals(result,estateAgentResponse);
        Mockito.verify(cityRepository).findByCityName(cityName);
        Mockito.verify(estateAgentRepository).save(estateAgent1);
        Mockito.verify(estateAgentResponse).from(savedEstateAgent);
        */
    }

    @Test
    public void testCreateEstateAgent_whenCityCityNameDoesNotExist_itShouldThrowApiRequestException(){

        EstateAgentRequest estateAgentRequest=estateAgentRequestSupport;

        when(cityManager.findByCityName("İstanbul")).thenReturn(null);

        Assertions.assertThrows(ApiRequestException.class,()->
                estateAgentManager.addEstateAgent(estateAgentRequest));

        verify(cityManager).findByCityName("İstanbul");
        verifyNoMoreInteractions(cityManager);
        verifyNoInteractions(estateAgentConverter);
    }

    @Test
    public void testUpdateEstateAgent_whenEstateAgentIdExist_itShouldUpdateEstateAgentResponse() {

        Long id=1L;
        City city=generateCity();
        EstateAgentRequest estateAgentRequest=new EstateAgentRequest("emlaks1","05548688281","Ankara");
        EstateAgent estateAgent=estateAgentEntity;
        EstateAgent savedEstateAgent= new EstateAgent(1L,"emlaks1","05548688281",city);
        EstateAgentResponse estateAgentResponse=EstateAgentResponse.builder()
                .companyName("emlaks1")
                .contactNumber("05548688281")
                .cityName("Ankara")
                .build();

        Mockito.when(estateAgentRepository.findById(id)).thenReturn(Optional.of(estateAgent));
        Mockito.when(estateAgentRepository.save(estateAgent)).thenReturn(savedEstateAgent);
        Mockito.when(estateAgentConverter.from(savedEstateAgent)).thenReturn(estateAgentResponse);

        EstateAgentResponse result = estateAgentManager.updateEstateAgent(id,estateAgentRequest);
        Assertions.assertEquals(estateAgentResponse,result);

        verify(estateAgentRepository).findById(id);
        verify(estateAgentRepository).save(estateAgent);
        verify(estateAgentConverter).from(savedEstateAgent);
    }

    @Test
    public void testUpdateEstateAgent_whenEstateAgentIdDoesNotExist_itShouldUpdateThrowApiRequestException() {

        EstateAgentRequest estateAgentRequest= estateAgentRequestSupport;

        when(estateAgentRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ApiRequestException.class,()->
                estateAgentManager.updateEstateAgent(id,estateAgentRequest));

        verify(estateAgentRepository).findById(id);
        verifyNoMoreInteractions(estateAgentRepository);
        verifyNoInteractions(estateAgentConverter);
    }

    @Test
    public void testDeleteEstateAgent_whenCityIdExist_itShouldDeleteEstateAgent() {
        EstateAgent estateAgent =estateAgentNotRequest;

        Mockito.when(estateAgentRepository.findById(id)).thenReturn(Optional.of(estateAgent));

        estateAgentManager.deleteEstateAgent(id);

        verify(estateAgentRepository).findById(id);
        verify(estateAgentRepository).deleteById(id);

    }

    @Test
    public void testDeleteEstateAgent_whenCityIdDoesNotExist_itShouldDeleteEstateAgent() {
        EstateAgent estateAgent= estateAgentEntity;

        Mockito.when(estateAgentRepository.findById(id)).thenReturn(Optional.empty());

        assertThrows(ApiRequestException.class,()->
                estateAgentManager.deleteEstateAgent(id));

        verify(estateAgentRepository).findById(id);
        verifyNoMoreInteractions(estateAgentRepository);
    }

    @Test
    public void testGetAllEstateAgents_itShouldReturnEstateAgentResponseList() {

        List<EstateAgent> estateAgentList=generateListEstateAgent();
        List<EstateAgentResponse> estateAgentResponseList=generateEstateAgentsResponseList(estateAgentList);

        Mockito.when(estateAgentRepository.findAll()).thenReturn(estateAgentList);
        Mockito.when(estateAgentConverter.fromList(estateAgentList)).thenReturn(estateAgentResponseList);

        List<EstateAgentResponse> result=estateAgentManager.getAllEstateAgent();

        Assertions.assertEquals(result,estateAgentResponseList);

        verify(estateAgentRepository).findAll();
        verify(estateAgentConverter).fromList(estateAgentList);
    }
}