package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.ForRentEstateAgentService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.entities.EstateAgent;
import com.graduationproject.realestate.entities.ForRentEstateAgent;
import com.graduationproject.realestate.entities.ImmovablesTypes;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.repository.EstateAgentRepository;
import com.graduationproject.realestate.repository.ForRentEstateAgentRepository;
import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import com.graduationproject.realestate.response.ForRentEstateAgentResponse;
import com.graduationproject.realestate.response.ListByCityResponseE;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class ForRentEstateAgentManager implements ForRentEstateAgentService {
   //private final RedisTemplate<String,List<ForRentEstateAgentResponse>> redisTemplate;
    private final ForRentEstateAgentRepository forRentEstateAgentRepository;
    private final CityRepository cityRepository;
    private final EstateAgentRepository estateAgentRepository;

    @Override
    public ForRentEstateAgentResponse addEstateAgentRent(ForRentEstateAgentRequest forRentEstateAgentRequest) {
        EstateAgent estateAgent = estateAgentRepository.findById(forRentEstateAgentRequest.getEstateAgentId()).get();
        City city =cityRepository.findByCityNameAndDistrict(forRentEstateAgentRequest.getCityName(), forRentEstateAgentRequest.getDistrict());
        ForRentEstateAgent forRent=forRentEstateAgentRepository.save(new ForRentEstateAgent( forRentEstateAgentRequest.getListingDate(),
                forRentEstateAgentRequest.getAdvertTitle(), forRentEstateAgentRequest.getPrice(),
                forRentEstateAgentRequest.getImmovablesTypes(), forRentEstateAgentRequest.getNumberOfRooms(),
                forRentEstateAgentRequest.getBuildingAge(), forRentEstateAgentRequest.getBalcony(),
                forRentEstateAgentRequest.getFurnished(),estateAgent,city));
        return ForRentEstateAgentResponse.from(forRent);
    }

    @Override
    public ForRentEstateAgentResponse updateEstateAgentRent(Long id, ForRentEstateAgentRequest forRentEstateAgentRequest) {
        ForRentEstateAgent forRent =forRentEstateAgentRepository.findById(id).orElseThrow(()->new ApiRequestException("Güncellenemedi. İlgili kayıt bulunamadı"+ id));
        forRent.setListingDate(forRentEstateAgentRequest.getListingDate());
        forRent.setAdvertTitle(forRentEstateAgentRequest.getAdvertTitle());
        forRent.setPrice(forRentEstateAgentRequest.getPrice());
        forRent.setImmovablesTypes(forRentEstateAgentRequest.getImmovablesTypes());
        forRent.setNumberOfRooms(forRentEstateAgentRequest.getNumberOfRooms());
        forRent.setBuildingAge(forRentEstateAgentRequest.getBuildingAge());
        forRent.setBalcony(forRentEstateAgentRequest.getBalcony());
        forRent.setFurnished(forRentEstateAgentRequest.getFurnished());
        ForRentEstateAgent updatedRent= forRentEstateAgentRepository.save(forRent);
        return ForRentEstateAgentResponse.from(updatedRent);
    }

    @Override
    public List<ForRentEstateAgentResponse> getAllForRentEstateAgentWithParam(Optional<Long> estateAgentId) {
        return estateAgentId.map(forRentEstateAgentRepository::findByEstateAgentId)
                .orElseGet(forRentEstateAgentRepository::findAll)
                .stream()
                .map(ForRentEstateAgentResponse::from)
                .toList();
    }

    @Override
    public List<ListByCityResponseE> getAllForRentCityWithParam(Optional<Long> cityId) {
        return cityId.map(forRentEstateAgentRepository::findByCityId)
                .orElseThrow(()->new ApiRequestException(" İlgili kayıt bulunamadı "+ cityId))
                .stream()
                .map(ListByCityResponseE::from)
                .toList();
    }

    /*@Override //@Cachable araştır
    public List<ForRentEstateAgentResponse> getAllByCityName(Optional<String> cityName) {
        List<ForRentEstateAgentResponse> result = redisTemplate.opsForValue().get(cityName.get());
        if (CollectionUtils.isEmpty(result)){
            result=forRentEstateAgentRepository.getAllByCityName(cityName).stream().map(ForRentEstateAgentResponse::from).collect(Collectors.toList());
            redisTemplate.opsForValue().set(cityName.get(),result);
            log.info("Cache update");
        }
        return result;
    }*/
    @Override
    public List<ForRentEstateAgentResponse> getAllByPrice(Long price,int no,int size) {
        Pageable pageables= PageRequest.of(no,size);//pagination icin bir yöntem
        return forRentEstateAgentRepository
                .findAllByPriceLessThanEqual(price,pageables)
                .stream()
                .map(ForRentEstateAgentResponse::from)
                .toList();
    }

    @Override
    public List<ForRentEstateAgentResponse> findAllByImmovablesTypes(ImmovablesTypes immovablesTypes, Pageable pageable) {
        return forRentEstateAgentRepository
                .findAllByImmovablesTypes(immovablesTypes,pageable)
                .stream()
                .map(ForRentEstateAgentResponse::from)
                .toList();
    }

    @Override
    public List<ForRentEstateAgentResponse> getAllByListingDate(LocalDate listingDate, Pageable pageable) {
        return forRentEstateAgentRepository
                .findAllByListingDateLessThanEqual(listingDate,pageable)
                .stream()
                .map(ForRentEstateAgentResponse::from)
                .toList();
    }

    @Override
    public List<ForRentEstateAgentResponse> findByCityNameAndDistrictAndPrice(Long price,String cityName, String district,  Pageable pageable) {
        return forRentEstateAgentRepository
                .findAllByPriceLessThanEqualAndCity_CityNameAndCity_District(price,cityName, district, pageable)
                .stream()
                .map(ForRentEstateAgentResponse::from)
                .toList();
    }

    @Override
    public List<ForRentEstateAgentResponse> bigFilter(Long price, int buildingAge, Boolean balcony, Boolean furnished, String cityName, String district, Pageable pageable) {
        return forRentEstateAgentRepository
                .findAllByPriceLessThanEqualAndBuildingAgeIsLessThanEqualAndBalconyAndFurnishedAndCity_CityNameAndCity_District(price, buildingAge, balcony, furnished, cityName, district,pageable)
                .stream()
                .map(ForRentEstateAgentResponse::from)
                .toList();
    }

    @Override
    public void deleteRent(Long id) {
        ForRentEstateAgent forRent=forRentEstateAgentRepository.getById(id);
        forRentEstateAgentRepository.deleteById(forRent.getId());
    }


}
