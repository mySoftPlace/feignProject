package com.execute.feignProject.controller;

import com.execute.feignProject.service.RandomService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ExecuteRandomController {

    private final RandomService randomService;

    @PostMapping(value = "/execute/randoms")
    ResponseEntity<Integer> executeRandomAB(@RequestBody HashMap<String, String> dataToTreat) {

        var result = randomService.executeRandomsSimultaneously(dataToTreat);
        return ResponseEntity.ok(result);
    }
}
