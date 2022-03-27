package com.graduationproject.realestate.entities;

import com.graduationproject.realestate.request.EstateAgentRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

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
    private Set<ForRentEstateAgent> forRentEstateAgents;

    @OneToMany(mappedBy = "estateAgent", fetch = FetchType.LAZY)
    private Set<ForSaleEstateAgent> forSaleEstateAgents;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private City city;

    //dışarıdan ilk istek atıldığında elinde request var. akış bununla başlıyor. sen bunu dbye kaydetmek istiyorsun ama db'nin anladığı request değil entity.
    // yani reqeusti entity'e çevirmen lazım. şimdi elimizde entity de var. daha sonra iş bitince geriye bir şey return etmen lazım. burda da entity'i
    // response a çevirip onu return ediyorsun
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
