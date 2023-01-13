package com.execute.feignProject.threads;

import com.execute.feignProject.client.FeignForServerTest;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Getter
@Setter
@RequiredArgsConstructor
@Scope("prototype")
@Service
public class ThreadForRandom implements Runnable {

    private final FeignForServerTest feignForServerTest;
    private static final String NO_THREAD = "noThread";
    private String threadName;
    static String fastestThread = NO_THREAD;
    public static int finalResult = 0;
    public static List<String> entryValues = new ArrayList<>();

    @Override
    public void run() {

        for (String val : entryValues) {
            try {
                if (this.getThreadName().equalsIgnoreCase("threadForRandomA")) {

                    randomTreatment(this.getThreadName(), feignForServerTest.randomA(val));
                } else {
                    randomTreatment(this.getThreadName(), feignForServerTest.randomB(val));
                }

            } catch (Exception e) {
                log.info("The http method throws some errors: {} and the random value is zero (0)", e.getMessage());
            }
        }
        log.info("All the values added up: {}", finalResult);
    }

    private void randomTreatment(String threadName, int result) {

        log.info(threadName);

        synchronized (fastestThread) {
            if (Objects.equals(fastestThread, NO_THREAD) || this.getThreadName().equals(fastestThread)) {
                finalResult += result;

                if (Objects.equals(fastestThread, NO_THREAD)) {
                    fastestThread = threadName;
                }
            } else {
                fastestThread = NO_THREAD;
            }
        }

    }
}
