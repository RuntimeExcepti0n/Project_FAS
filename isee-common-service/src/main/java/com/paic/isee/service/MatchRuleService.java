package com.paic.isee.service;

import com.paic.isee.entity.RiskGatewayInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/16 16:32
 */
public abstract class MatchRuleService {
    private static final Logger logger = LoggerFactory.getLogger(RiskGatewayInfo.class);

    public abstract <T> void matchRuleByRule(List<T> list, List<T> resultList, Map<String,String> config,Object paramValue);

    public <T> Object getValueByPropertyName(String currentPropertieName,T t){
        Object objectValue = null;
        try{
            Field tfield = t.getClass().getDeclaredField(currentPropertieName);
            tfield.setAccessible(true);
            objectValue  = tfield.get(t);
        }catch (Exception e){

        }
        return objectValue;
    }
}
