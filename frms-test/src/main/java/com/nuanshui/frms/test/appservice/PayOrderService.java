package com.nuanshui.frms.test.appservice;

import com.nuanshui.frms.test.entity.app.PayOrder;

public interface PayOrderService {

    Integer selectByUserIdForUnclearedOrder(PayOrder payOrder);
}
