package com.nuanshui.frms.test.appmapper;
import com.nuanshui.frms.test.entity.app.PayOrder;

public interface PayOrderMapper {
    Integer selectByUserIdForUnclearedOrder(PayOrder payOrder);
}