package com.graduationproject.realestate.business.concretes;
import com.graduationproject.realestate.business.abstracts.ForSaleEstateAgentService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.entities.EstateAgent;
import com.graduationproject.realestate.entities.ForSaleEstateAgent;
import com.graduationproject.realestate.entities.ProductType;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.ForSaleEstateAgentRepository;
import com.graduationproject.realestate.request.ForSaleEstateAgentRequest;
import com.graduationproject.realestate.response.ForSaleEstateAgentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ForSaleEstateAgentManager implements ForSaleEstateAgentService {

    private final ForSaleEstateAgentRepository forSaleEstateAgentRepository;
    private final CityManager cityManager;
    private final EstateAgentManager estateAgentManager;

    @Override
    public ForSaleEstateAgentResponse addEstateAgentSale(ForSaleEstateAgentRequest forSaleEstateAgentRequest) {
        EstateAgent estateAgent = estateAgentManager.findById(forSaleEstateAgentRequest.getEstateAgentId());
        City city =cityManager.findByCityNameAndDistrict(forSaleEstateAgentRequest.getCityName(), forSaleEstateAgentRequest.getDistrict());
        ForSaleEstateAgent forSale=forSaleEstateAgentRepository.save(new ForSaleEstateAgent( forSaleEstateAgentRequest.getListingDate(),
                forSaleEstateAgentRequest.getAdvertTitle(), forSaleEstateAgentRequest.getPrice(),
                forSaleEstateAgentRequest.getProductType(), forSaleEstateAgentRequest.getNumberOfRooms(),
                forSaleEstateAgentRequest.getBuildingAge(), forSaleEstateAgentRequest.getBalcony(),
                forSaleEstateAgentRequest.getFurnished(),estateAgent,city));
        return ForSaleEstateAgentResponse.from(forSale);
    }

    @Override
    public ForSaleEstateAgentResponse updateEstateAgentSale(Long id, ForSaleEstateAgentRequest forSaleEstateAgentRequest) {
        ForSaleEstateAgent forSale =forSaleEstateAgentRepository.findById(id).orElseThrow(()->new ApiRequestException("Could not be updated . No data found"));
        forSale.setListingDate(forSaleEstateAgentRequest.getListingDate());
        forSale.setAdvertTitle(forSaleEstateAgentRequest.getAdvertTitle());
        forSale.setPrice(forSaleEstateAgentRequest.getPrice());
        forSale.setProductType(forSaleEstateAgentRequest.getProductType());
        forSale.setNumberOfRooms(forSaleEstateAgentRequest.getNumberOfRooms());
        forSale.setBuildingAge(forSaleEstateAgentRequest.getBuildingAge());
        forSale.setBalcony(forSaleEstateAgentRequest.getBalcony());
        forSale.setFurnished(forSaleEstateAgentRequest.getFurnished());
        ForSaleEstateAgent updatedSale= forSaleEstateAgentRepository.save(forSale);
        return ForSaleEstateAgentResponse.from(updatedSale);
    }

    @Override
    public List<ForSaleEstateAgentResponse> getAllForSaleEstateAgentWithParam(Optional<Long> estateAgentId) {
        return estateAgentId.map(forSaleEstateAgentRepository::findByEstateAgentId)
                .orElseGet(forSaleEstateAgentRepository::findAll)
                .stream()
                .map(ForSaleEstateAgentResponse::from)
                .toList();
        /*
        List<ForSale> forSales;
        if(estateAgentId.isPresent()){
            forSales=forSaleRepository.findByEstateAgentId(estateAgentId.get());
        }else
            forSales=forSaleRepository.findAll();
        return forSales.stream().map(f->new ForSaleEstateAgentResponse(f)).collect(Collectors.toList());
         */
    }

    @Override
    public List<ForSaleEstateAgentResponse> getAllByCityName(String cityName ,Pageable pageable) {
        return forSaleEstateAgentRepository
                .findAllByCity_CityName(cityName,pageable)
                .stream()
                .map(ForSaleEstateAgentResponse::from)
                .toList();
    }

    @Override
    public List<ForSaleEstateAgentResponse> getAllByPrice(Long price,Pageable pageable) {
        return forSaleEstateAgentRepository
                .findAllByPriceLessThanEqual(price,pageable)
                .stream()
                .map(ForSaleEstateAgentResponse::from)
                .toList();
    }

    @Override
    public List<ForSaleEstateAgentResponse> getAllCheapHouseByCityName(String cityName,Pageable pageable) {
        return forSaleEstateAgentRepository
                .getAllCheapHouseByCityName(cityName,pageable)
                .stream()
                .map(ForSaleEstateAgentResponse::from)
                .toList();
    }

    @Override
    public List<ForSaleEstateAgentResponse> findAllByProductType(ProductType productType, Pageable pageable) {
        return forSaleEstateAgentRepository
                .findAllByProductType(productType,pageable)
                .stream()
                .map(ForSaleEstateAgentResponse::from)
                .toList();
    }

    @Override
    public List<ForSaleEstateAgentResponse> getAllByListingDate(LocalDate listingDate,Pageable pageable) {
        return forSaleEstateAgentRepository
                .findAllByListingDateLessThanEqual(listingDate,pageable)
                .stream()
                .map(ForSaleEstateAgentResponse::from)
                .toList();
    }

    @Override
    public List<ForSaleEstateAgentResponse> findByCityNameAndDistrictAndPrice(String cityName, String district, Long price,Pageable pageable) {
       return forSaleEstateAgentRepository
               .findAllByPriceLessThanEqualAndCity_CityNameAndCity_District(price,cityName,district,pageable)
               .stream()
               .map(ForSaleEstateAgentResponse::from)
               .toList();
    }

    @Override
    public List<ForSaleEstateAgentResponse> bigFilter(Long price, int buildingAge, Boolean balcony, Boolean furnished, String cityName, String district,Pageable pageable) {
        return forSaleEstateAgentRepository
                .findAllByPriceLessThanEqualAndBuildingAgeIsLessThanEqualAndBalconyAndFurnishedAndCity_CityNameAndCity_District(price, buildingAge, balcony, furnished, cityName, district,pageable)
                .stream()
                .map(ForSaleEstateAgentResponse::from)
                .toList();
    }

    @Override
    public void deleteSaleEstateAgent(Long id) {
        ForSaleEstateAgent forSale=forSaleEstateAgentRepository.getById(id);
        forSaleEstateAgentRepository.deleteById(forSale.getId());
    }
}
