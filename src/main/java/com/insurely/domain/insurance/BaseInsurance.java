package com.insurely.domain.insurance;

import org.springframework.util.StringUtils;

import java.util.HashSet;
import java.util.Set;

/** Base insurance model. */
public class BaseInsurance {
  private String insuranceType;
  private String insuranceSubType;
  private String insuranceName;
  private String insuranceNumber;
  private String paymentMethod;
  private Integer premiumAmountYear;

  public String getInsuranceType() {
    return insuranceType;
  }

  public void setInsuranceType(String insuranceType) {
    this.insuranceType = insuranceType;
  }

  public String getInsuranceSubType() {
    return insuranceSubType;
  }

  public void setInsuranceSubType(String insuranceSubType) {
    this.insuranceSubType = insuranceSubType;
  }

  public String getInsuranceName() {
    return insuranceName;
  }

  public void setInsuranceName(String insuranceName) {
    this.insuranceName = insuranceName;
  }

  public String getInsuranceNumber() {
    return insuranceNumber;
  }

  public void setInsuranceNumber(String insuranceNumber) {
    this.insuranceNumber = insuranceNumber;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public Integer getPremiumAmountYear() {
    return premiumAmountYear;
  }

  public void setPremiumAmountYear(Integer premiumAmountYear) {
    this.premiumAmountYear = premiumAmountYear;
  }

  public Set<String> getFieldDiff(BaseInsurance baseInsurance) {
    Set<String> fieldDiff = new HashSet<>();

    if (baseInsurance.insuranceType == null && !(this.insuranceType == null)) {
      fieldDiff.add("insuranceType");
    }
    if (baseInsurance.insuranceSubType == null && !(this.insuranceSubType == null)) {
      fieldDiff.add("insuranceSubType");
    }
    if (baseInsurance.insuranceName == null && !(this.insuranceName == null)) {
      fieldDiff.add("insuranceName");
    }
    if (baseInsurance.insuranceNumber == null && !(this.insuranceNumber == null)) {
      fieldDiff.add("insuranceNumber");
    }
    if (baseInsurance.paymentMethod == null && !(this.paymentMethod == null)) {
      fieldDiff.add("paymentMethod");
    }
    if (baseInsurance.premiumAmountYear == null && !(this.premiumAmountYear == null)) {
      fieldDiff.add("premiumAmountYear");
    }
    return fieldDiff;
  }
}
