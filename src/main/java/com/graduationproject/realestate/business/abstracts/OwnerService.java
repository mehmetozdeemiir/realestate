package com.graduationproject.realestate.business.abstracts;
import com.graduationproject.realestate.request.OwnerRequest;
import com.graduationproject.realestate.response.OwnerResponse;

import java.util.List;

public interface OwnerService {
    OwnerResponse addOwner(OwnerRequest ownerRequest);
    OwnerResponse updateOwner(Long id , OwnerRequest ownerRequest);
    void deleteOwner(Long id);
    List<OwnerResponse> getAllOwner();
}
