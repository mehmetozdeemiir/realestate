package com.graduationproject.realestate.business.concretes;

import com.graduationproject.realestate.business.abstracts.EstateAgentService;
import com.graduationproject.realestate.entities.City;
import com.graduationproject.realestate.entities.EstateAgent;
import com.graduationproject.realestate.exceptions.ApiRequestException;
import com.graduationproject.realestate.repository.CityRepository;
import com.graduationproject.realestate.repository.EstateAgentRepository;
import com.graduationproject.realestate.request.EstateAgentRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EstateAgentManager implements EstateAgentService {

    private final EstateAgentRepository estateAgentRepository;
    private final CityRepository cityRepository;

    @Override
    public EstateAgentRequest addEstateAgent(EstateAgentRequest estateAgentRequest) {
        City cityName=  cityRepository.findByCityName(estateAgentRequest.getCityName());
        EstateAgent estateAgent = estateAgentRepository.save(new EstateAgent(estateAgentRequest.getId(), estateAgentRequest.getCompanyName(), estateAgentRequest.getContactNumber(),cityName));
        return EstateAgentRequest.convert(estateAgent);
    }

    @Override
    public EstateAgentRequest updateEstateAgent(Long id, EstateAgentRequest estateAgentRequest) {
        EstateAgent estateAgent=estateAgentRepository.findById(id).orElseThrow(()->new ApiRequestException("Güncellenemedi. İlgili kayıt bulunamadı"));;
        estateAgent.setId(estateAgentRequest.getId());
        estateAgent.setCompanyName(estateAgentRequest.getCompanyName());
        estateAgent.setContactNumber(estateAgentRequest.getContactNumber());
        EstateAgent updatedEstateAgent= estateAgentRepository.save(estateAgent);
        return EstateAgentRequest.convert(updatedEstateAgent);
    }

    @Override
    public void deleteEstateAgent(Long id) {
        EstateAgent estateAgent=estateAgentRepository.findById(id).orElseThrow(()->new ApiRequestException("Silinemedi. İlgili kayıt bulunamadı"));;
        estateAgentRepository.deleteById(estateAgent.getId());
    }

    @Override
    public List<EstateAgentRequest> getAllEstateAgent() {
        return estateAgentRepository.findAll().stream().map(EstateAgentRequest::convert).collect(Collectors.toList());
    }
}
