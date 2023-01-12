package com.execute.feignProject.service;

import com.execute.feignProject.client.FeignForServerTest;
import com.execute.feignProject.threads.ThreadForRandomA;
import com.execute.feignProject.threads.ThreadForRandomB;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;

import static java.util.stream.Collectors.toList;

@Service
@RequiredArgsConstructor
public class RandomServiceImpl implements RandomService {
    private final ThreadForRandomA threadForRandomA;

    private final ThreadForRandomB threadForRandomB;

    @Override
    public int executeRandomsSimultaneously(HashMap<String, String> dataToTreat) {

        var values = dataToTreat.values().stream().collect(toList());

        int result = 0;

        for (String val : values) {

            // Thread for Random A
            var startTimeThreadA = System.nanoTime();
            threadForRandomA.setValueToTreat(val);
            threadForRandomA.run();
            var endTimeThreadA = System.nanoTime();
            var executionTimeThreadA = endTimeThreadA - startTimeThreadA;

            // Thread for Random B
            var startTimeThreadB = System.nanoTime();
            threadForRandomB.setValueToTreat(val);
            threadForRandomB.run();
            var endTimeThreadB = System.nanoTime();
            var executionTimeThreadB = endTimeThreadB - startTimeThreadB;

            if (executionTimeThreadA == executionTimeThreadB) { // Keep the response of the fastest method that does not throw an exception
                if (threadForRandomA.getResult() > 0 && threadForRandomB.getResult() > 0) {
                    result += threadForRandomA.getResult() + threadForRandomB.getResult();
                }
            } else if ((executionTimeThreadA < executionTimeThreadB) && (threadForRandomA.getResult() > 0)) {
                result += threadForRandomA.getResult();
            } else if (threadForRandomB.getResult() > 0) {
                result += threadForRandomB.getResult();
            } else if (threadForRandomA.getResult() > 0) {
                result += threadForRandomA.getResult();
            }

        }
        return result;
    }
}
