package com.nuanshui.frms.test.service.example.impl;

import com.nuanshui.frms.test.command.example.ExampleCmd;
import com.nuanshui.frms.test.entity.Example;
import com.nuanshui.frms.test.mapper.example.ExampleMapper;
import com.nuanshui.frms.test.repository.example.ExamplComponent;
import com.nuanshui.frms.test.service.example.ExampleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class ExampleServiceImpl implements ExampleService{

    @Autowired
    private ExampleMapper exampleMappper;

    @Autowired
    private ExamplComponent examplComponent;

    @Override
    public List<Example> findByName(String name) {
        return null;
    }

    @Override
    public Example findById(Long id) {
        Example example = new Example();
        return example;
    }

    @Override
    public List<Example> findAll(ExampleCmd cmd) {
        List<Example> exampleList = new ArrayList<>();
        return exampleList;
    }

    @Override
    public long save(Example entity) {

        log.info("-----save-----start");
        Long id =examplComponent.save(entity);
        log.info("-----save-----end");
        if(entity== null || entity.getName().equals("name111")){
            String str = null;
            str.substring(0);
        }
        return id;
    }
}
