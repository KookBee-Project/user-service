package com.KookBee.userservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CLASS-SERVICE")
public interface ClassesServiceClient {
    @GetMapping("/classes/{classesId}")
    Classes getClassesById(@PathVariable("classesId") Long classId);
}
