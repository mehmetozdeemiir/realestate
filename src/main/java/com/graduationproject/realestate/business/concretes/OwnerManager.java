package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.OwnerService;
import com.graduationproject.realestate.entities.Owner;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.repository.OwnerRepository;
import com.graduationproject.realestate.request.OwnerRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OwnerManager implements OwnerService {

    private final OwnerRepository ownerRepository;

    @Override
    public OwnerRequest addOwner(OwnerRequest ownerRequest) {
        Owner owner = ownerRepository.save(new Owner(ownerRequest.getId(), ownerRequest.getFirstName(), ownerRequest.getLastName(), ownerRequest.getContactNumber()));
        return OwnerRequest.convert(owner);
    }

    @Override
    public OwnerRequest updateOwner(Long id, OwnerRequest ownerRequest) {
        Owner owner= ownerRepository.getById(id);
        owner.setId(ownerRequest.getId());
        owner.setFirstName(ownerRequest.getFirstName());
        owner.setLastName(ownerRequest.getLastName());
        owner.setContactNumber(ownerRequest.getContactNumber());
        Owner updatedOwner=ownerRepository.save(owner);
        return OwnerRequest.convert(updatedOwner);
    }

    @Override
    public void deleteOwner(Long id) {
        Owner owner= ownerRepository.getById(id);
        ownerRepository.deleteById(owner.getId());
    }

    @Override
    public List<OwnerRequest> getAllOwner() {
        return ownerRepository.findAll().stream().map(OwnerRequest::convert).collect(Collectors.toList());
    }
}
