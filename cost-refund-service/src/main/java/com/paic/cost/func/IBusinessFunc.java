package com.paic.cost.func;

import com.paic.cost.util.BusinessServiceException;

/**
 * @author RuntimeExcepti0n
 * @date 2020/2/27 22:57
 */
public interface IBusinessFunc {
    void excuteFunc(BusinessMethodParm businessMethodParm) throws BusinessServiceException;

    void rollbackFunc(BusinessMethodParm businessMethodParm) throws BusinessServiceException;
}
