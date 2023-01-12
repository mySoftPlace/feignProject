package com.execute.feignProject.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

@FeignClient(value = "feignServerTest", url = "http://localhost:9876/random")
public interface FeignForServerTest {

    @PostMapping(value = "/{value}")
    public Integer randomA(@PathVariable("value") String value);

    @PutMapping(value = "/{value}")
    public Integer randomB(@PathVariable("value") String value);
}
