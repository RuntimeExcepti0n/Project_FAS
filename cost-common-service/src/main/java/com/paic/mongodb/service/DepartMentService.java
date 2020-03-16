package com.paic.mongodb.service;

import com.paic.mongodb.entity.DepartMent;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author RuntimeExcepti0n
 * @date 2020/2/23 17:23
 */

public interface DepartMentService {

    List<DepartMent> findAll();


}
