package com.graduationproject.realestate.response;

import com.graduationproject.realestate.entities.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class OwnerResponse {
    private String firstName;
    private String lastName;
    private String contactNumber;

    public static OwnerResponse from(Owner owner){
        return OwnerResponse.builder()
                .firstName(owner.getFirstName())
                .lastName(owner.getLastName())
                .contactNumber(owner.getContactNumber())
                .build();
    }
}
