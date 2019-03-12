package com.nuanshui.frms.test.csservice;

import com.nuanshui.frms.test.entity.cs.RepaymentOrder;

import java.util.List;

public interface RepaymentOrderService {
    List<RepaymentOrder> queryExpRepaymentOrder(RepaymentOrder repaymentOrder);
}
