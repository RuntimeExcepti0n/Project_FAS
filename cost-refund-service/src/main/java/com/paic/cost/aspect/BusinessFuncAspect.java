package com.paic.cost.aspect;

import com.google.common.collect.Lists;
import com.paic.cost.annotation.BusinessFunc;
import com.paic.cost.func.BusinessMethodParm;
import com.paic.cost.func.IBusinessFunc;
import com.paic.cost.util.BusinessServiceException;
import com.paic.cost.util.SpringContextUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

/**
 * @author RuntimeExcepti0n
 * @date 2020/2/27 23:12
 */
@Component
@Aspect
public class BusinessFuncAspect {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 业务功能处理，拦截@BusinessFunc注解
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around(value="@annotation(com.paic.cost.annotation.BusinessFunc)")
    public Object businessFunc(ProceedingJoinPoint joinPoint) throws  Throwable{
        return  excuteBusinessFunc(joinPoint);
    }

    private Object excuteBusinessFunc(ProceedingJoinPoint joinPoint) throws Throwable {
        BusinessMethodParm businessMethodParm = new BusinessMethodParm(joinPoint.getArgs());
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        BusinessFunc businessFunc = methodSignature.getMethod().getAnnotation(BusinessFunc.class);
        Class<IBusinessFunc>[] beforeFuncArray = (Class<IBusinessFunc>[]) businessFunc.beforeFunc();
        Class<IBusinessFunc>[] overWriteFunc = (Class<IBusinessFunc>[]) businessFunc.overFunc();
        Class<IBusinessFunc>[] afterFuncArray = (Class<IBusinessFunc>[]) businessFunc.afterFunc();
        List<Class<IBusinessFunc>> excutedFuncs = Lists.newArrayList();

        try{
            if(ArrayUtils.isNotEmpty(beforeFuncArray)){
                excutedFunc(beforeFuncArray,businessMethodParm,excutedFuncs);
            }
            if(ArrayUtils.isNotEmpty(overWriteFunc)){
                //如果有多个，只执行最后一个
                Class<IBusinessFunc> over = overWriteFunc[overWriteFunc.length - 1];
                excutedFunc(over,businessMethodParm,excutedFuncs);
            }else {
                Object returnObject = joinPoint.proceed();
                businessMethodParm.setResult(returnObject);
            }
            if(ArrayUtils.isNotEmpty(afterFuncArray)){
                excutedFunc(afterFuncArray,businessMethodParm,excutedFuncs);
            }
        }catch (Throwable throwable){
            if(CollectionUtils.isNotEmpty(excutedFuncs)){
                Collections.reverse(excutedFuncs);
                excuteRollBack(excutedFuncs.toArray(new Class[excutedFuncs.size()]),businessMethodParm);
            }
            throw throwable;
        }
        return  businessMethodParm.getResult();
    }

    /**
     * 批量触发回滚功能
     * @param funcArray
     * @param businessMethodParm
     */
    private void excuteRollBack(Class[] funcArray, BusinessMethodParm businessMethodParm) {
        for(Class<IBusinessFunc> funcClass : funcArray){
            excuteRollBack(funcClass,businessMethodParm);
        }
    }
    private void excuteRollBack(Class<IBusinessFunc> funcClass,BusinessMethodParm businessMethodParm){
        try{
            String funcName = funcClass.getSimpleName();
            IBusinessFunc businessFunc = SpringContextUtil.getBean(funcClass);
            businessFunc.rollbackFunc(businessMethodParm);
        }catch (Exception e){
            //TODO  回滚失败后，不中断其他功能回滚，增加异常回滚记录，异常回滚记录可以入日志表
            try{
                //回滚
            }catch (Exception ex){

            }
        }

    }

    private void excutedFunc(Class<IBusinessFunc>[] funcArray, BusinessMethodParm businessMethodParm, List<Class<IBusinessFunc>> excutedFuncs) throws BusinessServiceException {
        for(Class<IBusinessFunc> func :funcArray){
            excutedFunc(func,businessMethodParm,excutedFuncs);
        }
    }

    private void excutedFunc(Class<IBusinessFunc> funcClass, BusinessMethodParm businessMethodParm, List<Class<IBusinessFunc>> excutedFuncs) throws BusinessServiceException {
        String funcName = funcClass.getSimpleName();
        long funcStartTime = System.currentTimeMillis();
        IBusinessFunc businessFunc = SpringContextUtil.getBean(funcClass);
        if (businessFunc == null){
            throw  new BusinessServiceException("找不到对应的功能："+funcClass);
        }
        businessFunc.excuteFunc(businessMethodParm);
        excutedFuncs.add(funcClass);
    }
}
