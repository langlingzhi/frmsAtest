package com.nuanshui.frms.test.appservice.impl;

import com.nuanshui.frms.test.appmapper.PayOrderMapper;
import com.nuanshui.frms.test.appservice.PayOrderService;
import com.nuanshui.frms.test.entity.app.PayOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PayOrderServiceImpl implements PayOrderService {

    @Autowired
    private PayOrderMapper payOrderMapper;


    public Integer selectByUserIdForUnclearedOrder(PayOrder payOrder){
        return payOrderMapper.selectByUserIdForUnclearedOrder(payOrder);
    }
}
