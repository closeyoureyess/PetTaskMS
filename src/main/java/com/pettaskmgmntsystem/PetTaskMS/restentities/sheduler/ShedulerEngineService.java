package com.pettaskmgmntsystem.PetTaskMS.restentities.sheduler;

import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import com.pettaskmgmntsystem.PetTaskMS.restentities.CaseDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.Random;

@Service
@Slf4j
public class ShedulerEngineService {

    @Scheduled(fixedDelay = 10000)
    public void restCreateCase() {
        RestTemplate restTemplate = new RestTemplate();
        String createCaseController = "http://localhost:8081/api/v2/case/update-case";
        CaseDto caseDto = new CaseDto(randomPrimaryKey(), cycleGenerateWord(), LocalDateTime.now());
        CaseDto caseDtoResult =
                restTemplate.patchForObject(createCaseController, caseDto, CaseDto.class);
        if (caseDtoResult != null){
            log.info(caseDtoResult.getId() + ConstantsClass.LINE_FEED
                    + caseDtoResult.getName() + ConstantsClass.LINE_FEED + caseDtoResult.getDateOfCreate() + ConstantsClass.LINE_FEED);
        } else {
            log.info(null);
        }
    }

    private ResponseEntity<Long> getCase() {
        RestTemplate restTemplate = new RestTemplate();
        String getCase = "http://localhost:8081/api/v2/case/all-entity/counts";
        Random random = new Random();
        ResponseEntity<Long> responseGetCaseDto = restTemplate.getForEntity(getCase, Long.class);
        return responseGetCaseDto;
    }

    private String cycleGenerateWord(){
        Random random = new Random();
        int a = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (a < 27) {
            stringBuilder.append((char) random.nextInt(96, 123));
            a++;
        }
        return stringBuilder.toString();
    }

    private int randomPrimaryKey(){
        Random random = new Random();
        return random.nextInt(ConstantsClass.REGIME_OVERWRITING, getCase().getBody().intValue() + ConstantsClass.REGIME_RECORD);
    }

}
