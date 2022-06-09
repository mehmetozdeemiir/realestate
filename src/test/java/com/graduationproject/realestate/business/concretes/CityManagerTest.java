package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.TestSupport;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.request.CityRequest;
import com.graduationproject.realestate.response.CityResponseConverter;
import com.graduationproject.realestate.response.CityResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;


public class CityManagerTest extends TestSupport {

    private CityRepository cityRepository;
    private CityResponseConverter cityResponseConverter;
    private CityManager cityManager;


    @BeforeEach
    public void setUp() {
        cityRepository= Mockito.mock(CityRepository.class);
        cityResponseConverter = Mockito.mock(CityResponseConverter.class);
        cityManager=new CityManager(cityRepository, cityResponseConverter);
    }

    @Test
    public void testCreateCity_itShouldReturnCityResponse() {

        //given

        CityRequest request= cityRequestSupport;
        City city=entitySupport;
        City savedCity = new City(1L,"cityName","district");
        CityResponse cityResponse= cityResponseSupport;

        //when

        when(cityRepository.save(city)).thenReturn(savedCity);
        when(cityResponseConverter.from(savedCity)).thenReturn(cityResponse);

        //then

        CityResponse result= cityManager.addCity(request);
        Assertions.assertEquals(cityResponse,result);

        verify(cityRepository).save(city);
        verify(cityResponseConverter).from(savedCity);

    }

    @Test
    public void testUpdateCity_whenCityIdExist_itShouldReturnUpdatedCityResponse() {
        Long id=1L;
        CityRequest request= new CityRequest("cityName2","district2");
        City city=new City(1L,"cityName","district");
        City savedCity = new City(1L,"cityName2","district2");
        CityResponse cityResponse= new CityResponse("cityName2","district2");

        when(cityRepository.findById(id)).thenReturn(Optional.of(city));
        when(cityRepository.save(city)).thenReturn(savedCity);
        when(cityResponseConverter.from(savedCity)).thenReturn(cityResponse);

        CityResponse result = cityManager.updateCity(id,request);
        Assertions.assertEquals(cityResponse,result);

        verify(cityRepository).findById(id);
        verify(cityRepository).save(city);
        verify(cityResponseConverter).from(savedCity);


    }

    @Test
   public void testDeleteCity_whenCityIdExist_itShouldDeleteCity() {

        City city = new City(id,"cityName","district");

        when(cityRepository.findById(id)).thenReturn(Optional.of(city));

        cityManager.deleteCity(id);

        verify(cityRepository).findById(id);
        verify(cityRepository).deleteById(id);

    }

    @Test
    public void testDeleteCity_whenCityDoesNotIdExist_itShouldDeleteCity() {

        when(cityRepository.findById(id)).thenReturn(Optional.empty());

        Assertions.assertThrows(ApiRequestException.class, () ->
                cityManager.deleteCity(id) );

        verify(cityRepository).findById(id);
        verifyNoMoreInteractions(cityRepository);
    }

    @Test
    public void testGetAllCities_itShouldReturnCitiesResponseList() {

        List<City> cities= generateListCity();
        List<CityResponse> cityResponsesList = generateCitiesResponseList(cities);

        when(cityRepository.findAll()).thenReturn(cities);
        when(cityResponseConverter.fromList(cities)).thenReturn(cityResponsesList);

        List<CityResponse> result =cityManager.getAllCity();

        Assertions.assertEquals(result,cityResponsesList);

        verify(cityRepository).findAll();
        verify(cityResponseConverter).fromList(cities);
    }
}