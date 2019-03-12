package com.nuanshui.frms.test.entity.app;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class PayOrder implements Serializable {
    private Long id;

    private Long userId;

    private String mixCardNo;

    private String repayMixCardNo;

    private String orderNo;

    private Long orderAmount;

    private Long loanAmount;

    private Integer orderStatus;

    private Integer borrowType;

    private Integer loadDays;

    private Long overdueFee;

    private Long remissionFee;

    private Integer baseRate;

    private Integer interestRate;

    private Integer overdueDays;

    private Date approvedTime;

    private Date lendTime;

    private Date shouldReturnTime;

    private Date retrunTime;

    private Date createdAt;

    private Date updatedAt;

    private String rejectContext;

    private String failureContext;

    private String phone;

    private String loanChannel;

    private String newOldSign;

    private Integer loadPeriod;

    private String paymentFailureReason;

    private static final long serialVersionUID = 1L;

}