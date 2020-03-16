package com.paic.cost.util;

import com.google.common.collect.Lists;
import com.paic.cost.entity.BaseStandard;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author RuntimeExcepti0n
 * @date 2020/3/4 20:22
 */
public class SortUtil {

    public void sortBySelf(){
        List<String> tranfficSortRule = Lists.newArrayList("火车等级","大巴金额","机票标准","轮船标准");
        List<BaseStandard> newTrafficList = new LinkedList<>();
        List<BaseStandard> oldTrafficList = new LinkedList<>();
        List<BaseStandard> sortedList = oldTrafficList.stream().sorted(Comparator.comparing(BaseStandard::getTypeDesc, (x, y) -> {
            for (String sort : tranfficSortRule) {
                if (sort.equals(x) || sort.equals(y)) {
                    if (x.equals(y)) {
                        return 0;
                    } else if (sort.equals(x)) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
            }
            return 0;
        })).collect(Collectors.toList());

    }
}
