package com.graduationproject.realestate.entities;

import com.graduationproject.realestate.request.OwnerRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "owner")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Owner {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String contactNumber;

    public Owner( String firstName, String lastName, String contactNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.contactNumber = contactNumber;
    }

    public static Owner from(OwnerRequest ownerRequest){
        return Owner.builder()
                .firstName(ownerRequest.getFirstName())
                .lastName(ownerRequest.getLastName())
                .contactNumber(ownerRequest.getContactNumber())
                .build();
    }

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<ForRentOwner> forRentOwners;


    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<ForSaleOwner> forSaleOwners;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;
}
