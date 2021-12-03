package com.graduationproject.realestate.business.concretes;
import com.graduationproject.realestate.business.abstracts.ForSaleService;
import com.graduationproject.realestate.entities.*;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.*;
import com.graduationproject.realestate.request.ForSaleEstateAgentRequest;
import com.graduationproject.realestate.request.ForSaleOwnerRequest;
import com.graduationproject.realestate.response.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForSaleManager implements ForSaleService {
    private final ForSaleRepository forSaleRepository;
    private final OwnerRepository ownerRepository;
    private final CityRepository cityRepository;
    private final EstateAgentRepository estateAgentRepository;

    @Override
    public ForSaleOwnerRequest addOwnerSale(ForSaleOwnerRequest forSaleOwnerRequest) {
        Owner owner = ownerRepository.findById(forSaleOwnerRequest.getOwnerId()).get();
        City city =cityRepository.findByCityNameAndDistrict(forSaleOwnerRequest.getCityName(), forSaleOwnerRequest.getDistrict());
        ForSale forSale=forSaleRepository.save(new ForSale(forSaleOwnerRequest.getId(), forSaleOwnerRequest.getListingDate(),
                forSaleOwnerRequest.getAdvertTitle(), forSaleOwnerRequest.getPrice(),
                forSaleOwnerRequest.getImmovablesTypes(), forSaleOwnerRequest.getNumberOfRooms(),
                forSaleOwnerRequest.getBuildingAge(), forSaleOwnerRequest.getBalcony(),
                forSaleOwnerRequest.getFurnished(),owner,city));
        return ForSaleOwnerRequest.convert(forSale);
    }

    @Override
    public ForSaleOwnerRequest updateOwnerSale(Long id, ForSaleOwnerRequest forSaleOwnerRequest) {
        ForSale forSale =forSaleRepository.findById(id).orElseThrow(()->new ApiRequestException("Güncellenemedi. İlgili kayıt bulunamadı"));
        forSale.setListingDate(forSaleOwnerRequest.getListingDate());
        forSale.setAdvertTitle(forSaleOwnerRequest.getAdvertTitle());
        forSale.setPrice(forSaleOwnerRequest.getPrice());
        forSale.setImmovablesTypes(forSaleOwnerRequest.getImmovablesTypes());
        forSale.setNumberOfRooms(forSaleOwnerRequest.getNumberOfRooms());
        forSale.setBuildingAge(forSaleOwnerRequest.getBuildingAge());
        forSale.setBalcony(forSaleOwnerRequest.getBalcony());
        forSale.setFurnished(forSaleOwnerRequest.getFurnished());
        ForSale updatedSale= forSaleRepository.save(forSale);
        return ForSaleOwnerRequest.convert(updatedSale);
    }

    @Override
    public ForSaleEstateAgentRequest addEstateAgentSale(ForSaleEstateAgentRequest forSaleEstateAgentRequest) {
        EstateAgent estateAgent = estateAgentRepository.findById(forSaleEstateAgentRequest.getEstateAgentId()).get();
        City city =cityRepository.findByCityNameAndDistrict(forSaleEstateAgentRequest.getCityName(), forSaleEstateAgentRequest.getDistrict());
        ForSale forSale=forSaleRepository.save(new ForSale(forSaleEstateAgentRequest.getId(), forSaleEstateAgentRequest.getListingDate(),
                forSaleEstateAgentRequest.getAdvertTitle(), forSaleEstateAgentRequest.getPrice(),
                forSaleEstateAgentRequest.getImmovablesTypes(), forSaleEstateAgentRequest.getNumberOfRooms(),
                forSaleEstateAgentRequest.getBuildingAge(), forSaleEstateAgentRequest.getBalcony(),
                forSaleEstateAgentRequest.getFurnished(),estateAgent,city));
        return ForSaleEstateAgentRequest.convert(forSale);
    }

    @Override
    public ForSaleEstateAgentRequest updateEstateAgentSale(Long id, ForSaleEstateAgentRequest forSaleEstateAgentRequest) {
        ForSale forSale =forSaleRepository.findById(id).orElseThrow(()->new ApiRequestException("Güncellenemedi. İlgili kayıt bulunamadı")); //exceptionhandling yapınca duzelt
        forSale.setListingDate(forSaleEstateAgentRequest.getListingDate());
        forSale.setAdvertTitle(forSaleEstateAgentRequest.getAdvertTitle());
        forSale.setPrice(forSaleEstateAgentRequest.getPrice());
        forSale.setImmovablesTypes(forSaleEstateAgentRequest.getImmovablesTypes());
        forSale.setNumberOfRooms(forSaleEstateAgentRequest.getNumberOfRooms());
        forSale.setBuildingAge(forSaleEstateAgentRequest.getBuildingAge());
        forSale.setBalcony(forSaleEstateAgentRequest.getBalcony());
        forSale.setFurnished(forSaleEstateAgentRequest.getFurnished());
        ForSale updatedSale= forSaleRepository.save(forSale);
        return ForSaleEstateAgentRequest.convert(updatedSale);
    }

    @Override
    public List<ForSaleOwnerResponse> getAllForSaleOwnerWithParam(Optional<Long> ownerId) {
        List<ForSale> forSales;
        if(ownerId.isPresent()){
            forSales=forSaleRepository.findByOwnerId(ownerId.get());
        }else
            forSales=forSaleRepository.findAll();
        return forSales.stream().map(f->new ForSaleOwnerResponse(f)).collect(Collectors.toList());
    }

    @Override
    public List<ForSaleEstateAgentResponse> getAllForSaleEstateAgentWithParam(Optional<Long> estateAgentId) {
        List<ForSale> forSales;
        if(estateAgentId.isPresent()){
            forSales=forSaleRepository.findByEstateAgentId(estateAgentId.get());
        }else
            forSales=forSaleRepository.findAll();
        return forSales.stream().map(f->new ForSaleEstateAgentResponse(f)).collect(Collectors.toList());
    }

    @Override
    public List<ForSaleCityResponse> getAllForSaleCityWithParam(Optional<Long> cityId) {
        List<ForSale> forSales;
        if(cityId.isPresent()){
            forSales=forSaleRepository.findByCityId(cityId.get());
        }
        else
            forSales=forSaleRepository.findAll();
        return forSales.stream().map(f->new ForSaleCityResponse(f)).collect(Collectors.toList());
    }

    @Override
    public void delete(Long id) {
        ForSale forSale=forSaleRepository.getById(id);
        forSaleRepository.deleteById(forSale.getId());
    }
}
