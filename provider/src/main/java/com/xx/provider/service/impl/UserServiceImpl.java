package com.xx.provider.service.impl;

import com.xx.provider.dto.request.UserInfoQueryDTO;
import com.xx.provider.dto.response.UserInfoDTO;
import com.xx.provider.service.IUserService;
import io.github.lujiafa.houtu.springcloud.feign.anotation.AutoFeign;
import io.github.lujiafa.houtu.util.web.WebUtils;
import org.springframework.stereotype.Service;

@Service
@AutoFeign
public class UserServiceImpl implements IUserService {
    @Override
    public UserInfoDTO findByUserName(UserInfoQueryDTO userInfoQueryDTO) {
        System.out.println(WebUtils.getRequest().getHeader("X-Hint"));
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setUsername(userInfoQueryDTO.getUsername());
        userInfoDTO.setUsername("sk-" + userInfoDTO.getUsername());
        userInfoDTO.setNickName("https://xx.com/xx/" + userInfoDTO.getUsername());
        return userInfoDTO;
    }
}
