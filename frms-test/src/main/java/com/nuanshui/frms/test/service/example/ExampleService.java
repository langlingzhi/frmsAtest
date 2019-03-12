package com.nuanshui.frms.test.service.example;

import com.nuanshui.frms.test.command.example.ExampleCmd;
import com.nuanshui.frms.test.entity.Example;

import java.util.List;

public interface ExampleService {

    List<Example> findByName(String name);

    Example findById(Long id);

    List<Example> findAll(ExampleCmd cmd);

    long save(Example entity);

}









