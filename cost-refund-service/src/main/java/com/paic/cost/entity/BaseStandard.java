package com.paic.cost.entity;

import java.math.BigDecimal;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/4 20:26
 */
public class BaseStandard {

    private String id;
    /**
     * 标准类型
     */
    private String typeCode;

    private String typeDesc;
    /**
     * 标准等级
     */
    private String standardNo;

    private String standardDesc;

    private BigDecimal amount;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getTypeDesc() {
        return typeDesc;
    }

    public void setTypeDesc(String typeDesc) {
        this.typeDesc = typeDesc;
    }

    public String getStandardNo() {
        return standardNo;
    }

    public void setStandardNo(String standardNo) {
        this.standardNo = standardNo;
    }

    public String getStandardDesc() {
        return standardDesc;
    }

    public void setStandardDesc(String standardDesc) {
        this.standardDesc = standardDesc;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
