package com.paic.isee.service.impl;

import com.paic.isee.service.MatchRuleService;
import com.paic.isee.util.MatchRuleConfig;

import java.util.List;
import java.util.Map;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/16 17:46
 */
public class MatchRuleIntegerServiceImpl extends MatchRuleService {
    /***
     * 依据条件，匹配过滤数据
     * @param list
     * @param resultList
     * @param config
     * @param paramValue
     * @param <T>
     */
    @Override
    public <T> void matchRuleByRule(List<T> list, List<T> resultList, Map<String, String> config, Object paramValue) {
        try{
            String currentPropertyName = config.get(MatchRuleConfig.PROPERTY_NAME);
            if(paramValue == null){
                MatchRuleStringServiceImpl matchRuleStringService = new MatchRuleStringServiceImpl();
                matchRuleStringService.matchNullRule(list,resultList,currentPropertyName);
            }else {
                matchNotNullRule(list,resultList,config,paramValue,currentPropertyName);
            }
        }catch (Exception e){

        }
    }

    /**
     * 非null规则匹配 大于匹配规则，小于匹配规则
     * @param list
     * @param resultList
     * @param config
     * @param paramValue
     * @param currentPropertyName
     * @param <T>
     */
    private <T> void matchNotNullRule(List<T> list, List<T> resultList, Map<String, String> config, Object paramValue, String currentPropertyName) {
        MatchRuleStringServiceImpl matchRuleStringService = new MatchRuleStringServiceImpl();
        if("Y".equalsIgnoreCase(config.get(MatchRuleConfig.GT_MATCH))){
            //大于匹配规则
            gtMatchRule(list,resultList,paramValue,currentPropertyName);
        }else if ("Y".equalsIgnoreCase(config.get(MatchRuleConfig.LT_MATCH))){
            //小于匹配规则
            ltMatchRule(list,resultList,paramValue,currentPropertyName);
        }else if("Y".equalsIgnoreCase(config.get(MatchRuleConfig.EXACT_MATCH))){
            //精确匹配规则
            matchRuleStringService.exactMatchRule(list,resultList,paramValue,currentPropertyName);
        }else {
            //通用匹配
            matchRuleStringService.commonMatchRule(list,resultList,paramValue,currentPropertyName);
        }

    }

    /**
     * 大于匹配规则，库中匹配大于传入值数据，如果库中为空，
     * 则设置库中值为无限大
     * @param list  当前需要过滤的lisst
     * @param resultList  匹配之后的List
     * @param paramValue   当前传入参数属性值value
     * @param currentPropertyName 当前传入属性名
     * @param <T>
     */
    private <T> void ltMatchRule(List<T> list, List<T> resultList, Object paramValue, String currentPropertyName) {
        try{
            for(T t : list){
                Object objValue = getValueByPropertyName(currentPropertyName,t);
                Integer val ;
                if(objValue == null){
                    val = Integer.MAX_VALUE;
                }else {
                    val = (Integer) objValue;
                }
                //库中匹配大于传入值的数据
                if(val > (Integer)paramValue){
                    // 大于匹配成功
                    resultList.add(t);
                }
            }
        }catch (Exception e){

        }
    }

    /**
     * 小于匹配规则，库中匹配小于传入值的数据，如果库中数据为空，
     * 则相当于配置了金额为0
     * @param list
     * @param resultList
     * @param paramValue
     * @param currentPropertyName
     * @param <T>
     */
    private <T> void gtMatchRule(List<T> list, List<T> resultList, Object paramValue, String currentPropertyName) {
        try{
            //不为空 则使用小于匹配规则
            for(T t:list){
                Object objValue = getValueByPropertyName(currentPropertyName,t);
                //如果库中数据为空，则相当于库中数据为0
                Integer val;
                if(objValue == null){
                    val = 0;
                }else {
                    val = (Integer) objValue;
                }
             if(val < (Integer)paramValue){
                 resultList.add(t);
             }
            }
        }catch (Exception e){

        }
    }
}
