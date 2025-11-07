package com.houtu.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.houtu.consumer.form.UserInfoQueryForm;
import com.houtu.provider.dto.request.UserInfoQueryDTO;
import com.houtu.provider.dto.response.UserInfoDTO;
import com.houtu.provider.service.IUserService;
import com.houtu.provider.service.MockService;
import com.houtu.springcloud.sentinel.common.DefaultFallback;
import com.houtu.util.common.BeanUtils;
import com.houtu.web.model.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;
	@Autowired(required = false)
	private MockService mockService;

	@RequestMapping("/findByUserName")
    public ResponseData<UserInfoDTO> findByUserName(UserInfoQueryForm requestForm) {
		UserInfoQueryDTO userInfoQueryDTO = BeanUtils.smartCopyProperties(requestForm, UserInfoQueryDTO.class);
		UserInfoDTO userInfoDTO = userService.findByUserName(userInfoQueryDTO);
		if (userInfoDTO == null) {
			return ResponseData.fail(1001, "用户不存在");
		}
		return ResponseData.success(userInfoDTO);
	}

	@SentinelResource(value = "imock", fallbackClass = DefaultFallback.class, defaultFallback = "fallback")
	@RequestMapping(value = "/mock", method = RequestMethod.GET)
	public ResponseData mock(@RequestParam("type") Integer type) {
		return mockService.simple(type);
	}


}
