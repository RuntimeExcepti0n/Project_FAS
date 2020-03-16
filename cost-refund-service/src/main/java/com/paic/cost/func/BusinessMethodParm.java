package com.paic.cost.func;

import java.util.HashMap;
import java.util.Map;

/**
 * @author RuntimeExcepti0n
 * @date 2020/2/27 22:58
 */
public class BusinessMethodParm {
    /**
     * 业务方法参数
     */
    private Object args[];
    /**
     * 业务方法返回结果
     */
    private Object result;
    /**
     * 业务自定义参数Map
     */
    private Map<String,Object> paramMap;

    private String methodType;

    public String getMethodType() {
        return methodType;
    }

    public void setMethodType(String methodType) {
        this.methodType = methodType;
    }

    public BusinessMethodParm(Object[] args){
        this.args = args;
        this.paramMap = new HashMap<>();
    }

    public BusinessMethodParm(){
        this.paramMap = new HashMap<>();
    }
    /**
     * 获得业务方法参数
     */
    public Object[] getArgs() {
        return args;
    }
    /**
     * 获得方法返回结果
     */
    public Object getResult(){
        return result;
    }

    public void setResult(Object result){
        this.result = result;
    }
    /**
     * 获取自定义参数值
     */
    public Object get(String key){
        return  paramMap.get(key);
    }
    /**
     * 设置自定义参数值，可用于在不同的功能之间传递自定义参数
     */
    public void put(String key,Object value){
        paramMap.put(key,value);
    }

}
