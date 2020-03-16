package com.paic.cost.service.impl;

import com.paic.cost.annotation.BusinessFunc;
import com.paic.cost.func.impl.Method1Func;
import com.paic.cost.service.RefundOrderService;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/2 20:47
 */
public class RefundOrderServiceImpl implements RefundOrderService {
    @Override
    @BusinessFunc(beforeFunc = {Method1Func.class})
    public void save() {

    }
}
