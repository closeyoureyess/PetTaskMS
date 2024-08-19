package com.pettaskmgmntsystem.PetTaskMS.restentities.sheduler;

import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import com.pettaskmgmntsystem.PetTaskMS.restentities.CaseDto;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@Slf4j
public class ShedulerEngineService {

   /*@Scheduled(fixedDelay = 10000)*/
    public void restCreateCase() {
        RestTemplate restTemplate = new RestTemplate();
        String createCaseController = "http://localhost:8081/api/v2/case/update-case";
        CaseDto caseDto = new CaseDto(randomPrimaryKey(), cycleGenerateWord(), LocalDateTime.now());
        HttpEntity<CaseDto> httpEntity = new HttpEntity<>(caseDto);
        ResponseEntity<CaseDto> caseDtoResult =
                restTemplate.exchange(createCaseController, HttpMethod.PUT, httpEntity, CaseDto.class);
        if (caseDtoResult.getBody() != null){
            log.info(ConstantsClass.LINE_FEED +"ID " + caseDtoResult.getBody().getId() + ConstantsClass.LINE_FEED + "Name "
                    + caseDtoResult.getBody().getName() + ConstantsClass.LINE_FEED +
                    "Дата создания " + caseDtoResult.getBody().getDateOfCreate() + ConstantsClass.LINE_FEED);
        } else {
            log.info(null);
        }
    }

    private ResponseEntity<List<CaseDto>> getCase() {
        RestTemplate restTemplate = new RestTemplate();
        String getCase = "http://localhost:8081/api/v2/case/all-entityes";
        Random random = new Random();
        ResponseEntity<List<CaseDto>> responseGetCaseDto
                = restTemplate.exchange(getCase, HttpMethod.GET, null, new ParameterizedTypeReference<>(){});
        return responseGetCaseDto;
    }

    private String cycleGenerateWord(){
        Random random = new Random();
        int a = 0;
        StringBuilder stringBuilder = new StringBuilder();
        while (a < 28) {
            stringBuilder.append((char) random.nextInt(96, 123));
            a++;
        }
        return stringBuilder.toString();
    }

    private Integer randomPrimaryKey(){
        Random random = new Random();
        if (getCase() != null) {
            int index = random.nextInt(getCase().getBody().size());
            return getCase().getBody().get(index).getId();
        }
        return null;
    }

}
