package com.paic.cost.entity;

import com.paic.cost.annotation.NotNullIfAnotherFieldValue;
import com.paic.cost.annotation.TimeAfter;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/2 21:30
 */
@NotNullIfAnotherFieldValue.List({
        @NotNullIfAnotherFieldValue(fildName = "isQuickPay",fildValue = "1",dependFeild = "payees.oneWalletNo",
        message = "未维护壹钱包账号，无法提交，请先维护壹钱包账号",groups = {})
})
@TimeAfter.List({
       @TimeAfter(beforeTimeFeild = "refundStartDate",afterTimeFeild = "refundEndDate",message = "结束时间必须大于开始时间")
})
public class RefundBillHeaderInfo {

    private String billNo;

}
