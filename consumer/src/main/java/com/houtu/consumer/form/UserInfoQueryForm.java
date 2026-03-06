package com.houtu.consumer.form;

import io.github.lujiafa.houtu.web.model.BaseForm;
import io.github.lujiafa.houtu.web.validation.constroins.NotXss;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserInfoQueryForm extends BaseForm {

    @NotXss
    @NotNull(message = "用户名不能为空")
    private String username;
    private String nickName;
}
