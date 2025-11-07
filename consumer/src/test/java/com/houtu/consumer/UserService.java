package com.houtu.consumer;

import com.houtu.data.security.annotation.SecurityParam;
import com.houtu.data.security.annotation.SecurityWatch;
import com.houtu.data.security.support.SecurityObject;
import com.houtu.util.common.JsonUtils;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    @SecurityWatch
    public Object request(DataInfo dataInfo, @SecurityParam String name) {
        System.out.println("name:" + name);
        System.out.println("dataInfo:" + JsonUtils.toString(dataInfo));
        dataInfo.setName("334FB9A3404F98CDD293FE6ED0DAC152");
        return dataInfo;
    }

    @Data
    static class DataInfo implements SecurityObject {
        @SecurityParam
        private String name;
        @SecurityParam
        private List<Object> list;
    }

}
