package com.nuanshui.frms.test.csservice;

import com.nuanshui.frms.test.entity.cs.User;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface UserService {
    List<User> queryUserLogin(String username);
}
