package com.paic.mongodb.repository;

import com.paic.mongodb.entity.DepartMent;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * @author RuntimeExcepti0n
 * @date 2020/2/23 20:22
 */
@Repository
public interface DepartMentRepository extends MongoRepository<DepartMent,String> {

}
