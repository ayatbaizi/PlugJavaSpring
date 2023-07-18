package org.example.newMock.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.newMock.model.RequestDTO;
import org.example.newMock.model.ResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.concurrent.ThreadLocalRandom;
import java.util.Random;

@RestController
public class MainController {

    private Logger log = LoggerFactory.getLogger(MainController.class);
    Random random = new Random();
    ObjectMapper mapper = new ObjectMapper();
    public long startTime = 0L;

    private static String generateRandomBalance(double bound) {
       /* Random random = new Random();
        return String.valueOf(random.nextDouble(bound) + 1);*/
        Random random = new Random();
        double value = random.nextDouble() * bound;
        return String.valueOf(new BigDecimal(value).setScale(2, BigDecimal.ROUND_HALF_UP));
    }
    @PostMapping(
            value = "/info/postBalances",
            produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE

    )
    public Object postBalances(@RequestBody RequestDTO requestDTO) {
        try {
            String clienId = requestDTO.getClientId();
            char firstDigit = clienId.charAt(0);
            BigDecimal maxLimit;
            String currency1;
            double bound1 = 0;
            //int randomValueBalance = random.nextDouble(bound) + 1;

            if (firstDigit == '8') {
                maxLimit = new BigDecimal(2000.00);
                currency1 = "US";
                bound1 = 2000.00;
            } else if (firstDigit == '9') {
                maxLimit = new BigDecimal(1000.00);
                currency1 = "EU";
                bound1 = 1000.00;
            } else {
                maxLimit = new BigDecimal(10000.00);
                currency1 = "RUB";
                bound1 = 10000.00;
            }

            String RqUID = requestDTO.getRqUID();

            ResponseDTO responseDTO = new ResponseDTO();

            responseDTO.setRqUID(RqUID);
            responseDTO.setClientId(clienId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency(currency1);
            responseDTO.setBalance(generateRandomBalance(bound1));
            responseDTO.setMaxLimit(maxLimit);

            log.error("***** Запрос *****" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("***** Запрос *****" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));

            long pacing = ThreadLocalRandom.current().nextLong(100, 500);
            long endTime = System.currentTimeMillis();
            if(endTime - startTime < pacing)
                Thread.sleep(pacing - (endTime - startTime));

            return responseDTO;

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping(
            value = "/info/getBalances",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Object getBalances(@RequestBody RequestDTO requestDTO) {
        try {
            String clienId = requestDTO.getClientId();
            char firstDigit = clienId.charAt(0);
            BigDecimal maxLimit;
            String currency2;
            double bound2 = 0;

            if (firstDigit == '8') {
                maxLimit = new BigDecimal(2000.00);
                currency2 = "US";
                bound2 = 2000.00;
            } else if (firstDigit == '9') {
                maxLimit = new BigDecimal(1000.00);
                currency2 = "EU";
                bound2 = 1000.00;
            } else {
                maxLimit = new BigDecimal(10000.00);
                currency2 = "RUB";
                bound2 = 10000.00;
            }

            String RqUID = requestDTO.getRqUID();

            ResponseDTO responseDTO = new ResponseDTO();

            responseDTO.setRqUID(RqUID);
            responseDTO.setClientId(clienId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency(currency2);
            responseDTO.setBalance(generateRandomBalance(bound2));
            responseDTO.setMaxLimit(maxLimit);

            log.error("***** Запрос *****" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("***** Запрос *****" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));
            return responseDTO;

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
