package com.nuanshui.frms.test.csmapper;

import com.nuanshui.frms.test.entity.cs.RepaymentOrder;
import java.util.List;

public interface RepaymentOrderMapper {
    List<RepaymentOrder> queryExpRepaymentOrder(RepaymentOrder repaymentOrder);
}