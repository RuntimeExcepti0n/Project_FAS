package com.paic.mongodb.service.impl;

import com.paic.mongodb.entity.DepartMent;
import com.paic.mongodb.repository.DepartMentRepository;
import com.paic.mongodb.service.DepartMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RuntimeExcepti0n
 * @date 2020/2/23 17:27
 */
@Service
public class DepartMentServiceImpl  implements DepartMentService {
    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    DepartMentRepository departMentRepository;

    @Override
    public List<DepartMent> findAll() {
        List<DepartMent> all = mongoTemplate.findAll(DepartMent.class);
        return all;
    }


}
