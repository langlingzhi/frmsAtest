package com.nuanshui.frms.test.csmapper;

import com.nuanshui.frms.test.entity.cs.User;

import java.util.List;

public interface UserMapper {
    List<User> queryUserLogin(String username);
}
