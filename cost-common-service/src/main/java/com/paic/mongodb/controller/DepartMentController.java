package com.paic.mongodb.controller;

import com.paic.mongodb.entity.DepartMent;
import com.paic.mongodb.service.DepartMentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author RuntimeExcepti0n
 * @date 2020/2/23 17:29
 */
@RestController
@RequestMapping("/dept")
public class DepartMentController {

    @Autowired
    private DepartMentService departMentService;

    @GetMapping("/list")
    public List<DepartMent> findAll(){
        List<DepartMent> all = departMentService.findAll();
        return all;
    }

}
