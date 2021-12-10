package com.graduationproject.realestate.business.abstracts;

import com.graduationproject.realestate.request.ForRentOwnerRequest;
import com.graduationproject.realestate.response.ForRentOwnerResponse;

import java.util.List;
import java.util.Optional;

public interface ForRentOwnerService {
    ForRentOwnerResponse addOwnerRent(ForRentOwnerRequest forRentOwnerRequest);
    ForRentOwnerResponse updateOwnerRent(Long id, ForRentOwnerRequest forRentOwnerRequest);
    List<ForRentOwnerResponse> getAllForRentOwnerWithParam(Optional<Long> ownerId);
    void deleteRent(Long id);

}
