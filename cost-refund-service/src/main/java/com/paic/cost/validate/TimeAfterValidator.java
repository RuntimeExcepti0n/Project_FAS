package com.paic.cost.validate;

import com.paic.cost.annotation.TimeAfter;
import com.paic.cost.util.DateUtil;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.xml.crypto.Data;
import java.util.Date;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/16 11:27
 */
public class TimeAfterValidator implements ConstraintValidator<TimeAfter,Object> {
    /**
     * 开始时间
     */
    private String beforeTimeFeild;
    /**
     * 结束时间
     */
    private String afterTimeFeild;

    @Override
    public void initialize(TimeAfter constraintAnnotation) {
        this.afterTimeFeild = constraintAnnotation.afterTimeFeild();
        this.beforeTimeFeild = constraintAnnotation.beforeTimeFeild();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
        Object beforeTimeObject = beanWrapper.getPropertyValue(beforeTimeFeild);
        Object afterTimeObject = beanWrapper.getPropertyValue(afterTimeFeild);
        try{
            Date beforeTime = DateUtil.parseDate(beforeTimeObject);
            Date afterTime = DateUtil.parseDate(afterTimeObject);
            if(beforeTime == null || afterTime == null){
                return Boolean.TRUE;
            }
            if(afterTime.before(beforeTime)){
                context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                        .addPropertyNode(afterTimeFeild)
                        .addConstraintViolation();
                return Boolean.FALSE;
            }
        }catch (Exception e){

        }
        return Boolean.TRUE;
    }
}
