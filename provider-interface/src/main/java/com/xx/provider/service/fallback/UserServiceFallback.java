package com.xx.provider.service.fallback;

import com.xx.provider.dto.request.UserInfoQueryDTO;

public class UserServiceFallback {

    public static String findByUserName(UserInfoQueryDTO userInfoQueryDTO) {
        System.out.println("getFullName------------------");
        return "getFullName.熔断.fallback";
    }

    public static String findById(Long userId) {
        System.out.println("getSimpleName------------------");
        return "getSimpleName.熔断.fallback";
    }
}