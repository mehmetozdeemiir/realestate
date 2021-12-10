package com.graduationproject.realestate.business.concretes;
import com.graduationproject.realestate.business.abstracts.ForSaleEstateAgentService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.entities.EstateAgent;
import com.graduationproject.realestate.entities.ForSaleEstateAgent;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.repository.EstateAgentRepository;
import com.graduationproject.realestate.repository.ForSaleEstateAgentRepository;
import com.graduationproject.realestate.request.ForSaleEstateAgentRequest;
import com.graduationproject.realestate.response.ForSaleEstateAgentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForSaleEstateAgentManager implements ForSaleEstateAgentService {

    private final ForSaleEstateAgentRepository forSaleEstateAgentRepository;
    private final CityRepository cityRepository;
    private final EstateAgentRepository estateAgentRepository;
    @Override
    public ForSaleEstateAgentResponse addEstateAgentSale(ForSaleEstateAgentRequest forSaleEstateAgentRequest) {
        EstateAgent estateAgent = estateAgentRepository.findById(forSaleEstateAgentRequest.getEstateAgentId()).get();
        City city =cityRepository.findByCityNameAndDistrict(forSaleEstateAgentRequest.getCityName(), forSaleEstateAgentRequest.getDistrict());
        ForSaleEstateAgent forSale=forSaleEstateAgentRepository.save(new ForSaleEstateAgent( forSaleEstateAgentRequest.getListingDate(),
                forSaleEstateAgentRequest.getAdvertTitle(), forSaleEstateAgentRequest.getPrice(),
                forSaleEstateAgentRequest.getImmovablesTypes(), forSaleEstateAgentRequest.getNumberOfRooms(),
                forSaleEstateAgentRequest.getBuildingAge(), forSaleEstateAgentRequest.getBalcony(),
                forSaleEstateAgentRequest.getFurnished(),estateAgent,city));
        return ForSaleEstateAgentResponse.from(forSale);
    }

    @Override
    public ForSaleEstateAgentResponse updateEstateAgentSale(Long id, ForSaleEstateAgentRequest forSaleEstateAgentRequest) {
        ForSaleEstateAgent forSale =forSaleEstateAgentRepository.findById(id).orElseThrow(()->new ApiRequestException("Güncellenemedi. İlgili kayıt bulunamadı"));
        forSale.setListingDate(forSaleEstateAgentRequest.getListingDate());
        forSale.setAdvertTitle(forSaleEstateAgentRequest.getAdvertTitle());
        forSale.setPrice(forSaleEstateAgentRequest.getPrice());
        forSale.setImmovablesTypes(forSaleEstateAgentRequest.getImmovablesTypes());
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
                .collect(Collectors.toList());
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
    public void deleteSaleEstateAgent(Long id) {
        ForSaleEstateAgent forSale=forSaleEstateAgentRepository.getById(id);
        forSaleEstateAgentRepository.deleteById(forSale.getId());
    }
}
