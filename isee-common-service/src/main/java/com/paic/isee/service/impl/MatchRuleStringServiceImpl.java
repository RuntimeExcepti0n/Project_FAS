package com.paic.isee.service.impl;

import com.paic.isee.service.MatchRuleService;
import com.paic.isee.util.MatchRuleConfig;
import org.apache.commons.collections4.CollectionUtils;

import java.util.List;
import java.util.Map;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/16 16:43
 */
public class MatchRuleStringServiceImpl extends MatchRuleService {
    @Override
    public <T> void matchRuleByRule(List<T> list, List<T> resultList, Map<String, String> config, Object paramValue) {
        try{
            String currentPropertyName = config.get("propertyName");
            if(paramValue == null){
                matchNullRule(list,resultList,currentPropertyName);
            }else {
                matchNoNullRule(list,resultList,config,paramValue,currentPropertyName);
            }
        }catch (Exception e){

        }
    }


    /**
     * 传入的参数为Null.规则匹配null 只能匹配Null
     * @param list 当前需要过滤的list
     * @param resultList 匹配之后的结果List
     * @param currentPropertyName 当前传入的属性名
     * @param <T>
     */
    public  <T> void matchNullRule(List<T> list, List<T> resultList, String currentPropertyName) {
        try {
            for (T t: list){
                Object objValue = getValueByPropertyName(currentPropertyName,t);
                if(objValue == null){
                    resultList.add(t);
                }
            }
        }catch (Exception e){

        }

    }

    /**
     * 传入的参数不为null时 匹配规则
     * @param list
     * @param resultList
     * @param config
     * @param paramValue
     * @param currentPropertyName
     * @param <T>
     */
    private <T> void matchNoNullRule(List<T> list, List<T> resultList,Map<String,String> config,Object paramValue, String currentPropertyName) {
        if("Y".equalsIgnoreCase(config.get(MatchRuleConfig.EXACT_MATCH))){
            exactMatchRule(list,resultList,paramValue,currentPropertyName);
        }else if("Y".equalsIgnoreCase(MatchRuleConfig.CONTAIN_MATCH)){
            containMatchRule(list,resultList,paramValue,currentPropertyName,"[;；]");
        }else {
            commonMatchRule(list,resultList,paramValue,currentPropertyName);
        }
    }

    /**
     *  包含匹配规则
     *  传入值为分号隔开字符串，库中为逗号隔开数据
     *  主要传入值中一个元素等于库中逗号元素数据
     *  为匹配成功， 如果传入值为空，则取为空的数据(null匹配规则)
     * @param list
     * @param resultList
     * @param paramValue
     * @param currentPropertyName
     * @param splitReg
     * @param <T>
     */
    private <T> void containMatchRule(List<T> list, List<T> resultList, Object paramValue, String currentPropertyName, String splitReg) {
        Object objValue;
        try{
            for(T t :list){
                //获取当前库中对象属性value
                objValue = getValueByPropertyName(currentPropertyName,t);
                //库中数据为空 先忽略，下一步处理
                if(objValue == null){
                    continue;
                }
                String[] splitParam = paramValue.toString().split(splitReg);
                String[] splitValue = objValue.toString().split(splitReg);
                int count = getSameCount(splitParam,splitValue);
                if(count > 0){
                    resultList.add(t);
                }
            }
            //如果包含匹配不到，那么匹配结果值里面为null的值
            if(CollectionUtils.isEmpty(resultList)){
                matchNullRule(list,resultList,currentPropertyName);
            }
        }catch (Exception e){

        }
    }

    private int getSameCount(String[] splitParam, String[] splitValue) {
        int count = 0;
        for (String param : splitParam){
            for (String value : splitValue){
                if(value.equalsIgnoreCase(param)){
                    count++;
                }
            }
        }
        return count;
    }

    /**
     * 精确匹配，传入的参数和结果集中的参数必须一致
     * @param list
     * @param resultList
     * @param paramValue
     * @param currentPropertyName
     * @param <T>
     */
    public  <T> void exactMatchRule(List<T> list, List<T> resultList, Object paramValue, String currentPropertyName) {
        try{
            for (T t : list){
                Object objValue = getValueByPropertyName(currentPropertyName,t);
                if(objValue != null && paramValue.toString().equalsIgnoreCase(objValue.toString())){
                    resultList.add(t);
                }
            }
        }catch (Exception e){

        }
    }

    /**
     * 通用匹配规则，先精确匹配，如果一条都匹配不上，就匹配null记录
     * @param list  当前需要过滤的List
     * @param resultList 匹配之后结果list
     * @param paramValue  当前传入参数属性值value
     * @param currentProperty 当前传入的属性名
     */
    public <T> void commonMatchRule(List<T> list, List<T> resultList,Object paramValue,String currentProperty){
        try{
            for (T t : list){
                Object objValue = getValueByPropertyName(currentProperty,t);
                if(objValue != null && paramValue.toString().equalsIgnoreCase(objValue.toString())){
                    resultList.add(t);
                }
            }
            if(CollectionUtils.isEmpty(resultList)){
                matchNullRule(list,resultList,currentProperty);
            }
        }catch (Exception e){

        }
    }
}
