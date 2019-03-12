package com.nuanshui.frms.test.csservice.impl;

import com.nuanshui.frms.test.csmapper.RepaymentOrderMapper;
import com.nuanshui.frms.test.csservice.RepaymentOrderService;
import com.nuanshui.frms.test.entity.cs.RepaymentOrder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RepaymentOrderServiceImpl implements RepaymentOrderService {

    @Autowired
    private RepaymentOrderMapper repaymentOrderMapper;


    @Override
    public List<RepaymentOrder> queryExpRepaymentOrder(RepaymentOrder repaymentOrder) {
        return repaymentOrderMapper.queryExpRepaymentOrder(repaymentOrder);
    }
}
