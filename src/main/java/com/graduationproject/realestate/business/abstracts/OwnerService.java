package com.graduationproject.realestate.business.abstracts;
import com.graduationproject.realestate.request.OwnerRequest;
import java.util.List;

public interface OwnerService {
    OwnerRequest addOwner(OwnerRequest ownerRequest);
    OwnerRequest updateOwner(Long id , OwnerRequest ownerRequest);
    void deleteOwner(Long id);
    List<OwnerRequest> getAllOwner();
}
