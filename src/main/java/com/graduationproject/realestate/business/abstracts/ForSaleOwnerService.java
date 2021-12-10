package com.graduationproject.realestate.business.abstracts;

import com.graduationproject.realestate.request.ForSaleOwnerRequest;
import com.graduationproject.realestate.response.ForSaleOwnerResponse;

import java.util.List;
import java.util.Optional;

public interface ForSaleOwnerService {
    ForSaleOwnerResponse addOwnerSale(ForSaleOwnerRequest forSaleOwnerRequest);
    ForSaleOwnerResponse updateOwnerSale(Long id, ForSaleOwnerRequest forSaleOwnerRequest);
    List<ForSaleOwnerResponse> getAllForSaleOwnerWithParam(Optional<Long> ownerId);
    void deleteSaleOwner(Long id);
}
