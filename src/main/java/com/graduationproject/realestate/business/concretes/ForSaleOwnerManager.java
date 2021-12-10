package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.ForSaleOwnerService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.entities.ForSaleOwner;
import com.graduationproject.realestate.entities.Owner;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.repository.ForSaleOwnerRepository;
import com.graduationproject.realestate.repository.OwnerRepository;
import com.graduationproject.realestate.request.ForSaleOwnerRequest;
import com.graduationproject.realestate.response.ForSaleOwnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ForSaleOwnerManager implements ForSaleOwnerService {
    private final ForSaleOwnerRepository forSaleOwnerRepository;
    private final OwnerRepository ownerRepository;
    private final CityRepository cityRepository;

    @Override
    public ForSaleOwnerResponse addOwnerSale(ForSaleOwnerRequest forSaleOwnerRequest) {
        Owner owner = ownerRepository.findById(forSaleOwnerRequest.getOwnerId()).get();
        City city =cityRepository.findByCityNameAndDistrict(forSaleOwnerRequest.getCityName(), forSaleOwnerRequest.getDistrict());
        ForSaleOwner forSale=forSaleOwnerRepository.save(new ForSaleOwner( forSaleOwnerRequest.getListingDate(),
                forSaleOwnerRequest.getAdvertTitle(), forSaleOwnerRequest.getPrice(),
                forSaleOwnerRequest.getImmovablesTypes(), forSaleOwnerRequest.getNumberOfRooms(),
                forSaleOwnerRequest.getBuildingAge(), forSaleOwnerRequest.getBalcony(),
                forSaleOwnerRequest.getFurnished(),owner,city));
        return ForSaleOwnerResponse.from(forSale);
    }

    @Override
    public ForSaleOwnerResponse updateOwnerSale(Long id, ForSaleOwnerRequest forSaleOwnerRequest) {
        ForSaleOwner forSale =forSaleOwnerRepository.findById(id).orElseThrow(()->new ApiRequestException("Güncellenemedi. İlgili kayıt bulunamadı"));
        forSale.setListingDate(forSaleOwnerRequest.getListingDate());
        forSale.setAdvertTitle(forSaleOwnerRequest.getAdvertTitle());
        forSale.setPrice(forSaleOwnerRequest.getPrice());
        forSale.setImmovablesTypes(forSaleOwnerRequest.getImmovablesTypes());
        forSale.setNumberOfRooms(forSaleOwnerRequest.getNumberOfRooms());
        forSale.setBuildingAge(forSaleOwnerRequest.getBuildingAge());
        forSale.setBalcony(forSaleOwnerRequest.getBalcony());
        forSale.setFurnished(forSaleOwnerRequest.getFurnished());
        ForSaleOwner updatedSale= forSaleOwnerRepository.save(forSale);
        return ForSaleOwnerResponse.from(updatedSale);
    }
    @Override
    public List<ForSaleOwnerResponse> getAllForSaleOwnerWithParam(Optional<Long> ownerId) {
        return ownerId.map(forSaleOwnerRepository::findByOwnerId)
                .orElseGet(forSaleOwnerRepository::findAll)
                .stream()
                .map(ForSaleOwnerResponse::from)
                .collect(Collectors.toList());//BUNU ANLA BASKA CAREN YOK
    }
    @Override
    public void deleteSaleOwner(Long id) {
        ForSaleOwner forSale=forSaleOwnerRepository.getById(id);
        forSaleOwnerRepository.deleteById(forSale.getId());
    }

}
