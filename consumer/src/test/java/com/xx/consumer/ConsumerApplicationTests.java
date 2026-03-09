package com.xx.consumer;

//import com.auth0.jwt.JWT;
//import com.auth0.jwt.algorithms.Algorithm;
//import com.auth0.jwt.interfaces.DecodedJWT;

import io.github.lujiafa.houtu.util.common.JsonUtils;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

@SpringBootTest
class ConsumerApplicationTests {

	@Autowired
	private UserService userService;

	@Test
	void contextLoads() {
		UserService.DataInfo dataInfo = new UserService.DataInfo();
		dataInfo.setName("定了个当");
		ArrayList<Object> objects = new ArrayList<>();
		objects.add("wakk");
		objects.add(new Object());
		dataInfo.setList(objects);
		String name = "张三";
		Object obj = userService.request(dataInfo, name);
		System.out.println("\r\n\r\n");
		System.out.println("result-->" + JsonUtils.toString(obj) + "  " + (obj == name));
		System.out.println("dataInfo-->" + JsonUtils.toString(dataInfo));
	}

	//JWT验证
//	public static void main(String[] args) {
//		Calendar cal = Calendar.getInstance();
//		Date issat = cal.getTime();
//		cal.add(Calendar.SECOND, 1);
//		Date expat = cal.getTime();
//		String secretKey = "123";
//		String token = JWT.create()
//				.withIssuer("yg")
//				.withIssuedAt(issat)
//				.withExpiresAt(expat)
//				.withClaim("uid", "1")
//				.sign(Algorithm.HMAC256(secretKey));
//		DecodedJWT dj = JWT
//				.require(Algorithm.HMAC256(secretKey))
//				.withIssuer("yg")
//				.build()
//				.verify(token);
//		System.out.println(dj.getIssuer());
//		System.out.println(token);
//	}


	static void aa(String... args) {
		System.out.println(args);
		System.out.println(args.length);
	}

	public static void main(String[] args) throws Exception {
	aa(null, "wak");
	}
}
