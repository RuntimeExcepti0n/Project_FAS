package com.paic.cost.Enum;

/**
 * @author RuntimeExcepti0n
 * @date 2020/2/28 0:23
 */
public enum BillStatusEnum {
    DRAFT("草稿","0"),
    SUBMIT("提交","1");

    BillStatusEnum(String value, String name) {
        this.value = value;
        this.name = name;
    }

    public static  String getName(String value){
        for (BillStatusEnum billStatusEnum : BillStatusEnum.values()){
            if(billStatusEnum.getValue().equals(value)){
                return billStatusEnum.name;
            }
        }
        return  null;
    }

    private String value;

    private String name;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
