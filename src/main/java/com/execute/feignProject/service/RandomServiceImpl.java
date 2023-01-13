package com.execute.feignProject.service;

import com.execute.feignProject.threads.ThreadForRandom;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class RandomServiceImpl implements RandomService {
    private final ThreadForRandom runThreadA;

    private final ThreadForRandom runThreadB;

    @Override
    public int executeRandomsSimultaneously(HashMap<String, String> dataToTreat) {

        ThreadForRandom.entryValues = dataToTreat.values().stream().collect(toList());

        runThreadA.setThreadName("threadForRandomA");
        runThreadB.setThreadName("threadForRandomB");

        Thread threadForRandomA = new Thread(runThreadA);
        Thread threadForRandomB = new Thread(runThreadB);

        threadForRandomA.start();
        threadForRandomB.start();

        return ThreadForRandom.finalResult;
    }
}
