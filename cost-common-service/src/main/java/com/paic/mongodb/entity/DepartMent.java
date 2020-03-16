package com.paic.mongodb.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * @author RuntimeExcepti0n
 * @date 2020/2/23 17:15
 */
@Document(collation = "dept")
public class DepartMent {
    @Id
    private String id;
    private String deptNo;
    @Field("dName")
    private String deptName;
    @Field("loc")
    private String location;

}
