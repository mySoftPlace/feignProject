package com.execute.feignProject.threads;

import com.execute.feignProject.Utils.DataToTreatModel;
import com.execute.feignProject.client.FeignForServerTest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class ThreadForRandomB extends DataToTreatModel implements Runnable {

    private final FeignForServerTest feignForServerTest;

    @Override
    public void run() {

        try {
            setResult(feignForServerTest.randomB(getValueToTreat()));
        } catch (Exception e) {
            setResult(0);
            log.info("The http method PUT throws some errors: {} and the random value is zero (0)", e.getMessage());
        }
    }
}
