package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.ForRentEstateAgentService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.entities.EstateAgent;
import com.graduationproject.realestate.entities.ForRentEstateAgent;
import com.graduationproject.realestate.entities.ProductType;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.ForRentEstateAgentRepository;
import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import com.graduationproject.realestate.response.ForRentEstateAgentConverter;
import com.graduationproject.realestate.response.ForRentEstateAgentResponse;
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
    private final CityManager cityManager;
    private final EstateAgentManager estateAgentManager;
    private final ForRentEstateAgentConverter forRentEstateAgentConverter;

    @Override
    public ForRentEstateAgentResponse addEstateAgentRent(ForRentEstateAgentRequest forRentEstateAgentRequest) {
        EstateAgent estateAgent = estateAgentManager.findById(forRentEstateAgentRequest.getEstateAgentId());//repodan çağırmıyorum service de bu iş yapılıyor tekrardan repoya indirgenmemeli
        City city =cityManager.findByCityNameAndDistrict(forRentEstateAgentRequest.getCityName(), forRentEstateAgentRequest.getDistrict());
        ForRentEstateAgent forRent=forRentEstateAgentRepository.save(new ForRentEstateAgent( forRentEstateAgentRequest.getListingDate(),
                forRentEstateAgentRequest.getAdvertTitle(), forRentEstateAgentRequest.getPrice(),
                forRentEstateAgentRequest.getProductType(), forRentEstateAgentRequest.getNumberOfRooms(),
                forRentEstateAgentRequest.getBuildingAge(), forRentEstateAgentRequest.getBalcony(),
                forRentEstateAgentRequest.getFurnished(),estateAgent,city));
        return forRentEstateAgentConverter.from(forRent);
    }

    @Override
    public ForRentEstateAgentResponse updateEstateAgentRent(Long id, ForRentEstateAgentRequest forRentEstateAgentRequest) {
        ForRentEstateAgent forRent =forRentEstateAgentRepository.findById(id).orElseThrow(()->new ApiRequestException("Could not be updated . No data found"+ id));
        forRent.setListingDate(forRentEstateAgentRequest.getListingDate());
        forRent.setAdvertTitle(forRentEstateAgentRequest.getAdvertTitle());
        forRent.setPrice(forRentEstateAgentRequest.getPrice());
        forRent.setProductType(forRentEstateAgentRequest.getProductType());
        forRent.setNumberOfRooms(forRentEstateAgentRequest.getNumberOfRooms());
        forRent.setBuildingAge(forRentEstateAgentRequest.getBuildingAge());
        forRent.setBalcony(forRentEstateAgentRequest.getBalcony());
        forRent.setFurnished(forRentEstateAgentRequest.getFurnished());
        ForRentEstateAgent updatedRent= forRentEstateAgentRepository.save(forRent);
        return forRentEstateAgentConverter.from(updatedRent);
    }

    @Override
    public List<ForRentEstateAgentResponse> getAllForRentEstateAgentWithParam(Optional<Long> estateAgentId) {
        return estateAgentId.map(forRentEstateAgentRepository::findByEstateAgentId)
                .orElseGet(forRentEstateAgentRepository::findAll)
                .stream()
                .map(forRentEstateAgentConverter::from)
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
                .map(forRentEstateAgentConverter::from)
                .toList();
    }

    @Override
    public List<ForRentEstateAgentResponse> findAllByProductType(ProductType productType, Pageable pageable) {
        return forRentEstateAgentRepository
                .findAllByProductType(productType,pageable)
                .stream()
                .map(forRentEstateAgentConverter::from)
                .toList();
    }

    @Override
    public List<ForRentEstateAgentResponse> getAllByListingDate(LocalDate listingDate, Pageable pageable) {
        return forRentEstateAgentRepository
                .findAllByListingDateLessThanEqual(listingDate,pageable)
                .stream()
                .map(forRentEstateAgentConverter::from)
                .toList();
    }

    @Override
    public List<ForRentEstateAgentResponse> findByCityNameAndDistrictAndPrice(Long price,String cityName, String district,  Pageable pageable) {
        return forRentEstateAgentRepository
                .findAllByPriceLessThanEqualAndCity_CityNameAndCity_District(price,cityName, district, pageable)
                .stream()
                .map(forRentEstateAgentConverter::from)
                .toList();
    }

    @Override
    public List<ForRentEstateAgentResponse> bigFilter(Long price, int buildingAge, Boolean balcony, Boolean furnished, String cityName, String district, Pageable pageable) {
        return forRentEstateAgentRepository
                .findAllByPriceLessThanEqualAndBuildingAgeIsLessThanEqualAndBalconyAndFurnishedAndCity_CityNameAndCity_District(price, buildingAge, balcony, furnished, cityName, district,pageable)
                .stream()
                .map(forRentEstateAgentConverter::from)
                .toList();
    }

    @Override
    public void deleteRent(Long id) {
        ForRentEstateAgent forRent=forRentEstateAgentRepository.getById(id);
        forRentEstateAgentRepository.deleteById(forRent.getId());
    }




}
