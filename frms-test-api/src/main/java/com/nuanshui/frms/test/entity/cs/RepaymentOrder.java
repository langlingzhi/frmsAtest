package com.nuanshui.frms.test.entity.cs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 还款订单实体
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RepaymentOrder implements Serializable {
    private Long id;

    private Long customerId;//客户号

    private String customerName;//贷款人姓名

    private String mixCardNo;//

    private String repayMixCardNo;//

    private String orderNo;//订单号

    private BigDecimal orderAmount;//订单总金额加利息的

    private BigDecimal loanAmount;//贷款总金额,贷款的

    private String orderStatus;//状态

    private Integer borrowType;//借款类型

    private Integer loadDays;//贷款天数

    private BigDecimal overdueFee;//逾期费用

    private BigDecimal baseRate;//手续费，不同的产品都有手续费，每个人的手续费都不一样

    private BigDecimal interestRate;//利息，这个每个产品都有利息，针对产品的，所以每个人都一样

    private Integer overdueDays;//逾期天数

    private Date approvedTime;//审批时间

    private Date lendTime;//放款时间

    private Date shouldReturnTime;//应还日期

    private String retrunTime;//还款日期

    private String rejectContext;//被拒原因

    private String failureContext;//失败原因

    private String phone;//手机号码

    private String loanChannel;//渠道

    private String newOldSign;//新旧标志  1 从未申请过借过款  2 旧户 有过借款成功（放款成功）记录  3 次新 有借款申请记录，无放款成功记录：次新

    private Date createDate;//创建时间

    private Date updateDate;//更新时间

    private Integer createBy;//创建人

    private Integer updateBy;//更新人

    private String urgeName;//催收员姓名

    private String lotNum;//批次号

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", customerId=").append(customerId);
        sb.append(", customerName=").append(customerName);
        sb.append(", mixCardNo=").append(mixCardNo);
        sb.append(", repayMixCardNo=").append(repayMixCardNo);
        sb.append(", orderNo=").append(orderNo);
        sb.append(", orderAmount=").append(orderAmount);
        sb.append(", loanAmount=").append(loanAmount);
        sb.append(", orderStatus=").append(orderStatus);
        sb.append(", borrowType=").append(borrowType);
        sb.append(", loadDays=").append(loadDays);
        sb.append(", overdueFee=").append(overdueFee);
        sb.append(", baseRate=").append(baseRate);
        sb.append(", interestRate=").append(interestRate);
        sb.append(", overdueDays=").append(overdueDays);
        sb.append(", approvedTime=").append(approvedTime);
        sb.append(", lendTime=").append(lendTime);
        sb.append(", shouldReturnTime=").append(shouldReturnTime);
        sb.append(", retrunTime=").append(retrunTime);
        sb.append(", rejectContext=").append(rejectContext);
        sb.append(", failureContext=").append(failureContext);
        sb.append(", phone=").append(phone);
        sb.append(", loanChannel=").append(loanChannel);
        sb.append(", newOldSign=").append(newOldSign);
        sb.append(", createDate=").append(createDate);
        sb.append(", updateDate=").append(updateDate);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", urgeName=").append(urgeName);
        sb.append(", lotNum=").append(lotNum);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}