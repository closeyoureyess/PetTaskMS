package com.pettaskmgmntsystem.PetTaskMS.restentities.sheduler;

import com.pettaskmgmntsystem.PetTaskMS.restentities.CaseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Random;

@Service
public class ShedulerEngineService {

    @Autowired
    private RestTemplate restTemplate;

    @Scheduled(fixedDelay = 5000)
    public void restCreateCase(){
        ResponseEntity<CaseDto> responseCaseDto = new ResponseEntity<>(null);
        String createCaseController = "http://localhost:8080/api/v2/case/create";
        while (responseCaseDto != null) {
            responseCaseDto = restTemplate.postForEntity(createCaseController, )
        }
    }

    public void getCase(){
        ResponseEntity<CaseDto> responseGetCaseDto = new ResponseEntity<>(null);;
        String getCase = "http://localhost:8080/api/v2/gen-info/{id}";
        Random random = new Random();
        responseGetCaseDto = restTemplate.getForEntity(getCase, CaseDto.class, random.nextInt());
    }

}
