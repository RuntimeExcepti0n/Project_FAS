package com.paic.cost.validate;

import com.paic.cost.annotation.NotNullIfAnotherFieldValue;
import org.apache.commons.lang.ObjectUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/2 21:01
 */
public class NotNullIfAnotherFieldValueValidator implements ConstraintValidator<NotNullIfAnotherFieldValue,Object> {

    public static final String SEPARATE_CHAR = ".";
    /**
     * 判断的属性名
     */
    private String fieldName;
    /**
     * 判断的属性值
     */
    private String expectedFeildValue;
    /**
     * 判断非空的集合属性
     */
    private String dependFeild;


    @Override
    public void initialize(NotNullIfAnotherFieldValue constraintAnnotation) {
        this.fieldName = constraintAnnotation.fildName();
        this.expectedFeildValue = constraintAnnotation.fildValue();
        this.dependFeild = constraintAnnotation.dependFeild();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        if (value == null) {
            return Boolean.TRUE;
        }
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
        Object fieldValue = beanWrapper.getPropertyValue(fieldName);
        if (expectedFeildValue.equals(fieldValue) && !validateNotNullProps(value, dependFeild)) {
            context.buildConstraintViolationWithTemplate(context.getDefaultConstraintMessageTemplate())
                    .addPropertyNode(fieldName)
                    .addConstraintViolation();
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    private boolean validateNotNullProps(Object value, String dependFeild) {
        BeanWrapperImpl beanWrapper = new BeanWrapperImpl(value);
        if(value instanceof List){
           for(Object obj : (List)value){
               Boolean listPropNull = validateNotNullProps(obj,fieldName);
               if(!listPropNull){
                   return  Boolean.FALSE;
               }
           }
           return Boolean.TRUE;
        }else {
            if(!StringUtils.contains(fieldName,SEPARATE_CHAR)){
                Object feildValue = beanWrapper.getPropertyValue(fieldName);
                return  StringUtils.isNotBlank(ObjectUtils.toString(feildValue));
            }else {
                int index = fieldName.indexOf(SEPARATE_CHAR);
                String beforeKey = StringUtils.substring(fieldName,0,index);
                String afterKey = StringUtils.substring(fieldName,index+1,fieldName.length());
                Object beforeObj = beanWrapper.getPropertyValue(beforeKey);
                return  validateNotNullProps(beforeObj,afterKey);
            }
        }
    }
}


