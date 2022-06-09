package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.OwnerService;
import com.graduationproject.realestate.entities.EstateAgent;
import com.graduationproject.realestate.entities.Owner;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.OwnerRepository;
import com.graduationproject.realestate.request.OwnerRequest;
import com.graduationproject.realestate.response.OwnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OwnerManager implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public OwnerResponse addOwner(OwnerRequest ownerRequest) {
        Owner owner = ownerRepository.save(new Owner(ownerRequest.getFirstName(), ownerRequest.getLastName(), ownerRequest.getContactNumber()));
        return OwnerResponse.from(owner);
    }

    @Override
    public OwnerResponse updateOwner(Long id, OwnerRequest ownerRequest) {
        Owner owner= ownerRepository.getById(id);
        owner.setId(id);
        owner.setFirstName(ownerRequest.getFirstName());
        owner.setLastName(ownerRequest.getLastName());
        owner.setContactNumber(ownerRequest.getContactNumber());
        Owner updatedOwner=ownerRepository.save(owner);
        return OwnerResponse.from(updatedOwner);
    }

    @Override
    public void deleteOwner(Long id) {
        Owner owner= ownerRepository.getById(id);
        ownerRepository.deleteById(owner.getId());
    }

    @Override
    public List<OwnerResponse> getAllOwner() {
        return ownerRepository
                .findAll()
                .stream()
                .map(OwnerResponse::from)
                .toList();
    }

    protected Owner findById(Long id){
        return ownerRepository.findById(id).orElseThrow(()->new ApiRequestException("No records found"));
    }
}
