package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.CityService;
import com.graduationproject.realestate.business.abstracts.EstateAgentService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.entities.EstateAgent;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.repository.EstateAgentRepository;
import com.graduationproject.realestate.request.EstateAgentRequest;
import com.graduationproject.realestate.request.ForRentEstateAgentRequest;
import com.graduationproject.realestate.response.EstateAgentConverter;
import com.graduationproject.realestate.response.EstateAgentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EstateAgentManager implements EstateAgentService {

    private final EstateAgentRepository estateAgentRepository;
    private final CityManager cityManager;
    private final EstateAgentConverter estateAgentConverter;

    @Override
    public EstateAgentResponse addEstateAgent(EstateAgentRequest estateAgentRequest) {
        City cityName=  cityManager.findByCityName(estateAgentRequest.getCityName()); //repodan çağırmıyorum service de bu iş yapılıyor tekrardan repoya indirgenmemeli
        EstateAgent estateAgent = estateAgentRepository.save(new EstateAgent(estateAgentRequest.getCompanyName(), estateAgentRequest.getContactNumber(),cityName));
        return estateAgentConverter.from(estateAgent);
    }

    @Override
    public EstateAgentResponse updateEstateAgent(Long id, EstateAgentRequest estateAgentRequest) {
        EstateAgent estateAgent=findById(id);
        estateAgent.setId(id);
        estateAgent.setCompanyName(estateAgentRequest.getCompanyName());
        estateAgent.setContactNumber(estateAgentRequest.getContactNumber());
        EstateAgent updatedEstateAgent= estateAgentRepository.save(estateAgent);
        return estateAgentConverter.from(updatedEstateAgent);
    }

    @Override
    public void deleteEstateAgent(Long id) {
        EstateAgent estateAgent=findById(id);
        estateAgentRepository.deleteById(estateAgent.getId());
    }

    @Override
    public List<EstateAgentResponse> getAllEstateAgent() {
        return estateAgentConverter.fromList(estateAgentRepository.findAll());
    }

    protected EstateAgent findById(Long id){
        return estateAgentRepository.findById(id).orElseThrow(()->new ApiRequestException("No records found"));
    }






}
