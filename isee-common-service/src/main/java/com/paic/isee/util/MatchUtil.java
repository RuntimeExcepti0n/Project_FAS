package com.paic.isee.util;

import com.google.common.collect.Lists;
import com.paic.isee.entity.RiskGatewayInfo;
import com.paic.isee.entity.RiskGatewayInfoDisplayConfig;
import com.paic.isee.service.MatchRuleService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.formula.functions.T;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/16 15:28
 */
public class MatchUtil {
    /**
     *
     * @param baseInfos 根据单据类型从数据库匹配的全量集合
     * @param fields  数据库配置的需要匹配的字段
     * @param dto    匹配的单据信息
     * @return
     */
    public List<RiskGatewayInfo> matchRiskLevel(List<RiskGatewayInfo> baseInfos, List<RiskGatewayInfoDisplayConfig> fields,RiskGatewayInfo dto){
        List<RiskGatewayInfo> resultInfos = Lists.newArrayList();
        List<Map<String,String>> configMapList = getConfigList(fields,dto);
        resultInfos = matchRule(baseInfos,configMapList,dto,0);
        return resultInfos;
    }

    private <T>List<T> matchRule(List<T> list, List<Map<String, String>> configMapList, T tObject, int index) {
        //当前循环返回的结果
        List<T> resultList = new ArrayList<>();
        //最后一次循环返回的结果
        List<T> finalResultList = new ArrayList<>();
        try{
            Map<String,String> config;
            for(int i = index;i<configMapList.size();){
                config = configMapList.get(i);
                //获取(String,Integer,BigDecimal)具体规则实现类
                MatchRuleService matchRuleService = MatchRuleFactory.getInstance(config.get(MatchRuleConfig.PROPERTY_TYPE));
                //获取配置规则的属性名
                String currentParamName = config.get(MatchRuleConfig.PROPERTY_NAME);
                //依据属性名获取此对象属性的值
                Object paramValue = matchRuleService.getValueByPropertyName(currentParamName,tObject);
                matchRuleService.matchRuleByRule(list,resultList,config,paramValue);
                //递归调用
                index++;
                finalResultList = matchRule(resultList,configMapList,tObject,index);
                //最后一层递归赋值返回
                if(index == configMapList.size()){
                    finalResultList = resultList;
                }
                break;
            }
        }catch (Exception e){

        }
        return finalResultList;
    }

    /**
     * 获取每个字段的匹配规则
     * @param fields
     * @param dto
     * @return
     */
    private List<Map<String, String>> getConfigList(List<RiskGatewayInfoDisplayConfig> fields, RiskGatewayInfo dto) {
        List<Map<String,String>> configList = new ArrayList<>();
        for(RiskGatewayInfoDisplayConfig field : fields){
            Object type = getType(field.getDataBaseFieldName(),dto);
            Map<String,String> map = new HashMap<>();
            map.put("propertyName",field.getDataBaseFieldName());
            map.put("propertyType",type.toString());
            //处理匹配规则
            editMapRule(field.getMatchRule(),map);
            configList.add(map);
        }
        return configList;
    }

    private void editMapRule(String matchRule, Map<String, String> map) {
        if(StringUtils.isNotBlank(matchRule)){
            map.put(matchRule,"Y");
        }
    }

    private <T> String getType(String dataBaseFieldName, T t) {
        String type ="";
        try{
            Field tfield = t.getClass().getDeclaredField(dataBaseFieldName);
            tfield.setAccessible(true);
            String packetType = tfield.getGenericType().toString();
            type = packetType.substring(packetType.lastIndexOf('.')+1);
        }catch (Exception e){

        }
        return  type;
    }


}
