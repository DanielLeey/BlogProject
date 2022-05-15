package com.lee.handler.validation;

import com.lee.annotation.FlagValidator;
import org.springframework.util.StringUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class FlagValidatorHandler implements ConstraintValidator<FlagValidator, String> {

    private String[] values;

    @Override
    //获取注解中的values值，表示允许的范围
    public void initialize(FlagValidator constraintAnnotation) {
        this.values = constraintAnnotation.value();
    }

    @Override
    //value为前台参数传入的值
    public boolean isValid(String value, ConstraintValidatorContext constraintValidatorContext) {
        //isValid是否通过校验
        boolean isValid = false;
        //传入的参数为空，则使用默认值
        if (!StringUtils.hasText(value)) {
            return true;
        }
        for (int i = 0; i < values.length; i++) {
            //传入的参数和注解中使用的匹配上，表示通过校验。否则未通过校验
            if (value.equals(values[i])) {
                isValid = true;
                break;
            }
        }
        return isValid;
    }
}
