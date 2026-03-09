package com.xx.consumer.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import io.github.lujiafa.houtu.actuator.metrics.client.HttpClientMetric;
import com.xx.consumer.form.UserInfoQueryForm;
import com.xx.provider.dto.request.UserInfoQueryDTO;
import com.xx.provider.dto.response.UserInfoDTO;
import com.xx.provider.service.IUserService;
import com.xx.provider.service.MockService;
import io.github.lujiafa.houtu.springcloud.sentinel.common.DefaultFallback;
import io.github.lujiafa.houtu.util.common.BeanUtils;
import io.github.lujiafa.houtu.util.http.HttpClients;
import io.github.lujiafa.houtu.util.web.WebUtils;
import io.github.lujiafa.houtu.web.model.ResponseData;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
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

	@RequestMapping("/find/{username}")
    public ResponseData<UserInfoDTO> findByUserName(@PathVariable("username") String username) {
		UserInfoDTO userInfoDTO = new UserInfoDTO();
		userInfoDTO.setUsername(username);
		HttpClientMetric.metric();
		HttpClients.HttpResponseData resp = HttpClients.get("https://repo.maven.apache.org/maven2/io/github/openfeign/feign-hc5", HttpClients.RequestConfig.build().param("username", username));
		System.out.println(resp.getContent());
		return ResponseData.success(userInfoDTO);
	}

	@RequestMapping("/findByUserName")
    public ResponseData<UserInfoDTO> findByUserName(UserInfoQueryForm requestForm) {
		System.out.println(WebUtils.getRequest().getHeader("X-Hint"));
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
