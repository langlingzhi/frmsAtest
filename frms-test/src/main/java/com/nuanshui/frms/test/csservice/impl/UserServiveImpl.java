package com.nuanshui.frms.test.csservice.impl;

import com.nuanshui.frms.test.csmapper.UserMapper;
import com.nuanshui.frms.test.csservice.UserService;
import com.nuanshui.frms.test.entity.cs.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@Slf4j
public class UserServiveImpl implements UserService {

    @Autowired
    private UserMapper usermapper;
    @Override
    public List<User> queryUserLogin(String username) {

        return usermapper.queryUserLogin(username);
    }
}
