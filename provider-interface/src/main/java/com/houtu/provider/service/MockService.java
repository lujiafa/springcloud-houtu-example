package com.houtu.provider.service;

import io.github.lujiafa.houtu.web.model.ResponseData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider", path = "/mock")
public interface MockService {
    @RequestMapping(value="/simple", method = RequestMethod.POST)
    ResponseData simple(@RequestParam("type") int type);
}
