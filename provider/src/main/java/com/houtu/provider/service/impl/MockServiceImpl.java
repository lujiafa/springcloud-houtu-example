package com.houtu.provider.service.impl;

import io.github.lujiafa.houtu.core.exception.BusinessException;
import com.houtu.provider.service.MockService;
import io.github.lujiafa.houtu.springcloud.feign.anotation.AutoFeign;
import io.github.lujiafa.houtu.web.model.ResponseData;
import org.springframework.stereotype.Service;

@Service
@AutoFeign
public class MockServiceImpl implements MockService {
    @Override
    public ResponseData simple(int type) {
        switch (type) {
            case -2: throw new BusinessException(1001, "mock RuntimeException");
            case -1: throw new RuntimeException("I have a RuntimeException");
        }
        return ResponseData.success();
    }
}
