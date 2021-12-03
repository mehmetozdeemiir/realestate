package com.graduationproject.realestate.request;

import com.graduationproject.realestate.entities.Owner;
import lombok.Data;

@Data
public class OwnerRequest {
    private Long id;
    private String firstName;
    private String lastName;
    private String contactNumber;
    //şehir ismine gerek yok satan veya kiralayan hangi şehirde olduğu çok önemli değil gibi gerekirse eklenir

    public OwnerRequest(Long id, String firstName, String lastName, String contactNumber) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }

    public static OwnerRequest convert(Owner owner){
        return new OwnerRequest(owner.getId(), owner.getFirstName(), owner.getLastName(), owner.getContactNumber());
    }
}
