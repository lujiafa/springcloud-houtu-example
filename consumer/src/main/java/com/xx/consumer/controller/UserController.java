package com.xx.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.xx.consumer.form.UserInfoQueryForm;
import com.xx.provider.dto.request.UserInfoQueryDTO;
import com.xx.provider.dto.response.UserInfoDTO;
import com.xx.provider.service.IUserService;
import com.xx.provider.service.MockService;
import io.github.lujiafa.houtu.springcloud.sentinel.common.DefaultFallback;
import io.github.lujiafa.houtu.util.common.BeanUtils;
import io.github.lujiafa.houtu.util.common.DateUtils;
import io.github.lujiafa.houtu.web.model.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

@Controller
@RequestMapping(value = "/user")
public class UserController {

	private final static Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private IUserService userService;
	@Autowired(required = false)
	private MockService mockService;

	@RequestMapping("/find/{username}")
    public ResponseData<UserInfoDTO> findByUserName(@PathVariable("username") String username) {
		UserInfoQueryDTO userInfoQueryDTO = new UserInfoQueryDTO();
		userInfoQueryDTO.setUsername(username);
		// HttpClientMetric.metric();
		logger.info("请求/user/find/xx 接口，时间：{}", DateUtils.formatDateTime(new Date()));
		UserInfoDTO userInfoDTO = userService.findByUserName(userInfoQueryDTO);
		return ResponseData.success(userInfoDTO);
	}

	@RequestMapping("/findByUserName")
    public ResponseData<UserInfoDTO> findByUserName(HttpServletRequest request, UserInfoQueryForm requestForm) {
		System.out.println(request.getHeader("X-Hint"));
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
