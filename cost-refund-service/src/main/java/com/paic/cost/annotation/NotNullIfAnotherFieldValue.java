package com.paic.cost.annotation;

import com.paic.cost.validate.NotNullIfAnotherFieldValueValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/2 20:50
 */
@Constraint(validatedBy = NotNullIfAnotherFieldValueValidator.class)
@Target({ElementType.ANNOTATION_TYPE,ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNullIfAnotherFieldValue {
    /**
     * 判断的属性名
     * @return
     */
    String fildName();

    /**
     * 判断的属性值
     * @return
     */
    String fildValue();

    /**
     * 校验分组
     * @return
     */
    Class<?>[] groups() default{};

    /**
     * 判断非空的属性名集合
     * @return 属性名字符串
     */
    String dependFeild();

    /**
     * 校验不通过的提示信息
     * @return
     */
    String message() default "NotNullIfAnotherFieldValue.message";

    @Target({ElementType.ANNOTATION_TYPE,ElementType.TYPE})
    @Retention(RetentionPolicy.RUNTIME)
    @Documented
    @interface List{
        NotNullIfAnotherFieldValue[] value();
    }

    Class<? extends Payload>[] payload() default {};
}
