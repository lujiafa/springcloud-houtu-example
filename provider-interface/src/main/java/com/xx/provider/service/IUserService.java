package com.xx.provider.service;

import com.xx.provider.dto.request.UserInfoQueryDTO;
import com.xx.provider.dto.response.UserInfoDTO;
import io.github.lujiafa.houtu.springcloud.feign.anotation.AutoFeign;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "provider", path = "/user")
public interface IUserService {

    @RequestMapping(value = "/findByUserName", method = RequestMethod.POST)
    UserInfoDTO findByUserName(UserInfoQueryDTO userInfoQueryDTO);

}
