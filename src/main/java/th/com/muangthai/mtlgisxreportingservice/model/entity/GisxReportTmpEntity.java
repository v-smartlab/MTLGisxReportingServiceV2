package th.com.muangthai.mtlgisxreportingservice.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "gisx_report_tmp")
public class GisxReportTmpEntity {
    @Id
    @Column(name = "key_no")
    private BigDecimal keyNo;

    @Size(max = 30)
    @Column(name = "policy_no", length = 30)
    private String policyNo;

    @Size(max = 100)
    @Column(name = "plan_code", length = 100)
    private String planCode;

    @Column(name = "seq_no_plan_code")
    private Integer seqNoPlanCode;

    @Size(max = 300)
    @Column(name = "plan_desc", length = 300)
    private String planDesc;

    @Column(name = "seq_no_benefit_condition")
    private Integer seqNoBenefitCondition;

    @Size(max = 2000)
    @Column(name = "benefit_condition_desc", length = 2000)
    private String benefitConditionDesc;

    @Size(max = 50)
    @Column(name = "plan_benefit", length = 50)
    private String planBenefit;

    @Size(max = 50)
    @Column(name = "plan_benefit_amount", length = 50)
    private String planBenefitAmount;

    @Size(max = 100)
    @Column(name = "reserve1", length = 100)
    private String reserve1;

    @Size(max = 100)
    @Column(name = "reserve2", length = 100)
    private String reserve2;

    @Size(max = 100)
    @Column(name = "reserve3", length = 100)
    private String reserve3;

    @NotNull
    @Column(name = "status_flag", nullable = false)
    private Integer statusFlag;

    @Size(max = 20)
    @NotNull
    @Column(name = "create_by", nullable = false, length = 20)
    private String createBy;

    @NotNull
    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    public BigDecimal getKeyNo() {
        return keyNo;
    }

    public void setKeyNo(BigDecimal keyNo) {
        this.keyNo = keyNo;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getPlanCode() {
        return planCode;
    }

    public void setPlanCode(String planCode) {
        this.planCode = planCode;
    }

    public Integer getSeqNoPlanCode() {
        return seqNoPlanCode;
    }

    public void setSeqNoPlanCode(Integer seqNoPlanCode) {
        this.seqNoPlanCode = seqNoPlanCode;
    }

    public String getPlanDesc() {
        return planDesc;
    }

    public void setPlanDesc(String planDesc) {
        this.planDesc = planDesc;
    }

    public Integer getSeqNoBenefitCondition() {
        return seqNoBenefitCondition;
    }

    public void setSeqNoBenefitCondition(Integer seqNoBenefitCondition) {
        this.seqNoBenefitCondition = seqNoBenefitCondition;
    }

    public String getBenefitConditionDesc() {
        return benefitConditionDesc;
    }

    public void setBenefitConditionDesc(String benefitConditionDesc) {
        this.benefitConditionDesc = benefitConditionDesc;
    }

    public String getPlanBenefit() {
        return planBenefit;
    }

    public void setPlanBenefit(String planBenefit) {
        this.planBenefit = planBenefit;
    }

    public String getPlanBenefitAmount() {
        return planBenefitAmount;
    }

    public void setPlanBenefitAmount(String planBenefitAmount) {
        this.planBenefitAmount = planBenefitAmount;
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2;
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3;
    }

    public Integer getStatusFlag() {
        return statusFlag;
    }

    public void setStatusFlag(Integer statusFlag) {
        this.statusFlag = statusFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

}