package com.graduationproject.realestate.controller;

import com.graduationproject.realestate.business.abstracts.ForSaleOwnerService;
import com.graduationproject.realestate.request.ForSaleOwnerRequest;
import com.graduationproject.realestate.response.ForSaleOwnerResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ForSaleOwnerController {

    private final ForSaleOwnerService forSaleOwnerService;

    @PostMapping("/forsaleowners")
    @ResponseStatus(HttpStatus.CREATED)
    public ForSaleOwnerResponse addOwnerSale(@Valid @RequestBody ForSaleOwnerRequest forSaleOwnerRequest){
        return forSaleOwnerService.addOwnerSale(forSaleOwnerRequest);
    }

    @PutMapping("/forsaleowners")
    public ForSaleOwnerResponse updateOwnerSale(@Valid @PathVariable Long id, @RequestBody ForSaleOwnerRequest forSaleOwnerRequest){
        return forSaleOwnerService.updateOwnerSale(id,forSaleOwnerRequest);
    }

    @GetMapping("/forsaleowners")
    public List<ForSaleOwnerResponse> getAllForSaleOwnerWithParam(@RequestParam Optional<Long> ownerId){
        return forSaleOwnerService.getAllForSaleOwnerWithParam(ownerId);
    }

    @DeleteMapping("/for-sale-owners/{id}")
    public void deleteSale(@PathVariable Long id){
        forSaleOwnerService.deleteSaleOwner(id);
    }
}
