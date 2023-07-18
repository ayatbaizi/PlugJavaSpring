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

@RestController
public class MainController {

    private Logger log = LoggerFactory.getLogger(MainController.class);

    ObjectMapper mapper = new ObjectMapper();

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

            if (firstDigit == '8') {
                maxLimit = new BigDecimal(2000.00);
            } else if (firstDigit == '9') {
                maxLimit = new BigDecimal(1000.00);
            } else {
                maxLimit = new BigDecimal(10000.00);
            }

            String RqUID = requestDTO.getRqUID();

            ResponseDTO responseDTO = new ResponseDTO();

            responseDTO.setRqUID(RqUID);
            responseDTO.setClientId(clienId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency("RUB");
            responseDTO.setBalance("900");
            responseDTO.setMaxLimit(maxLimit);

            log.error("***** Запрос *****" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("***** Запрос *****" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));
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

            if (firstDigit == '8') {
                maxLimit = new BigDecimal(2000.00);
            } else if (firstDigit == '9') {
                maxLimit = new BigDecimal(1000.00);
            } else {
                maxLimit = new BigDecimal(10000.00);
            }

            String RqUID = requestDTO.getRqUID();

            ResponseDTO responseDTO = new ResponseDTO();

            responseDTO.setRqUID(RqUID);
            responseDTO.setClientId(clienId);
            responseDTO.setAccount(requestDTO.getAccount());
            responseDTO.setCurrency("RUB");
            responseDTO.setBalance("900");
            responseDTO.setMaxLimit(maxLimit);

            log.error("***** Запрос *****" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(requestDTO));
            log.error("***** Запрос *****" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(responseDTO));
            return responseDTO;

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }

    }
}
