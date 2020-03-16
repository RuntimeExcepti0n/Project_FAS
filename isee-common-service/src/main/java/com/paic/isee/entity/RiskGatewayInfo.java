package com.paic.isee.entity;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/16 14:05
 */
@Data
@ToString
@Accessors(chain = true)
public class RiskGatewayInfo {
    /**
     * 单据类型
     */
    private String billType;
    /**
     * 系列
     */
    private String series;
    /**
     * 帐套
     */
    private String ledger;
    /**
     * 公司段ID
     */
    private String segment1;
    /**
     * 业务段ID
     */
    private String segment2;

    private String riskLevel;


}
