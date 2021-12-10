package com.graduationproject.realestate.entities;

import com.graduationproject.realestate.request.EstateAgentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "estateagent")
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EstateAgent {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String companyName;
    private String contactNumber;


    @OneToMany(mappedBy = "estateAgent", fetch = FetchType.LAZY)
    private List<ForRentEstateAgent> forRentEstateAgents;

    @OneToMany(mappedBy = "estateAgent", fetch = FetchType.LAZY)
    private List<ForSaleEstateAgent> forSaleEstateAgents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    public static EstateAgent from(EstateAgentRequest estateAgentRequest){
        return EstateAgent.builder()
                .companyName(estateAgentRequest.getCompanyName())
                .contactNumber(estateAgentRequest.getContactNumber())
                .build();
    }

    public EstateAgent(String companyName, String contactNumber, City city) {
        this.companyName = companyName;
        this.contactNumber = contactNumber;
        this.city = city;
    }
}
