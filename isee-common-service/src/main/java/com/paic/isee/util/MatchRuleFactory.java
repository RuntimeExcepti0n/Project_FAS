package com.paic.isee.util;

import com.paic.isee.service.MatchRuleService;
import com.paic.isee.service.impl.MatchRuleIntegerServiceImpl;
import com.paic.isee.service.impl.MatchRuleStringServiceImpl;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/16 16:40
 */
public class MatchRuleFactory {

    public static MatchRuleService getInstance(String propertyType){
        MatchRuleService matchRuleService = null;
        try{
            if("String".equalsIgnoreCase(propertyType)){
                matchRuleService = new MatchRuleStringServiceImpl();
            }else if("Integer".equalsIgnoreCase(propertyType)){
                matchRuleService = new MatchRuleIntegerServiceImpl();
            }else if("BigDecimal".equalsIgnoreCase(propertyType)){

            }else {
                System.out.println("get mathcRuleService is null");
            }
        }catch (Exception e){

        }
        return  matchRuleService;
    }
}
