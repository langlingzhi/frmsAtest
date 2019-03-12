package com.nuanshui.frms.test.repository.example;

import com.nuanshui.frms.test.entity.Example;
import com.nuanshui.frms.test.mapper.example.ExampleMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.transaction.Transactional;

/**
 * Created by Administrator on 2017/12/22.
 */
@Component
@Slf4j
public class ExamplComponent {
    @Autowired
    private ExampleMapper exampleMappper;

    @Transactional
    public Long save(Example entity){
        Long id =exampleMappper.insert(entity);
        if(entity== null || entity.getName().equals("name110")){
            String str = null;
            str.substring(0);
        }
        return id;
    }

}
