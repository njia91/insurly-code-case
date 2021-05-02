package com.insurely.domain.insurance;

import java.util.HashSet;
import java.util.Set;

/** CondoInsurance refers to the regular "Hemförsäkring". */
public class CondoInsurance extends BaseInsurance {
  private Boolean isCondo;
  private Integer livingArea;
  private String postalCode;
  private Integer numberOfResidents;
  private Integer insuredAmount;

  public Boolean getCondo() {
    return isCondo;
  }

  public void setCondo(Boolean condo) {
    isCondo = condo;
  }

  public Integer getLivingArea() {
    return livingArea;
  }

  public void setLivingArea(Integer livingArea) {
    this.livingArea = livingArea;
  }

  public String getPostalCode() {
    return postalCode;
  }

  public void setPostalCode(String postalCode) {
    this.postalCode = postalCode;
  }

  public Integer getNumberOfResidents() {
    return numberOfResidents;
  }

  public void setNumberOfResidents(Integer numberOfResidents) {
    this.numberOfResidents = numberOfResidents;
  }

  public Integer getInsuredAmount() {
    return insuredAmount;
  }

  public void setInsuredAmount(Integer insuredAmount) {
    this.insuredAmount = insuredAmount;
  }

  public Set<String> getFieldDiff(BaseInsurance baseInsurance) {
    Set<String> fieldDiff = new HashSet<>();

    if(baseInsurance.getClass().equals(this.getClass())) {
      CondoInsurance condoInsurance = (CondoInsurance) baseInsurance;

      if (condoInsurance.isCondo == null && !(this.isCondo == null)) {
        fieldDiff.add("isCondo");
      }
      if (condoInsurance.livingArea == null && !(this.livingArea == null)) {
        fieldDiff.add("livingArea");
      }
      if (condoInsurance.postalCode == null && !(this.postalCode == null)) {
        fieldDiff.add("postalCode");
      }
      if (condoInsurance.numberOfResidents == null && !(this.numberOfResidents == null)) {
        fieldDiff.add("numberOfResidents");
      }
      if (condoInsurance.insuredAmount == null && !(this.insuredAmount == null)) {
        fieldDiff.add("insuredAmount");
      }
    } else {
      fieldDiff.add("isCondo");
      fieldDiff.add("livingArea");
      fieldDiff.add("postalCode");
      fieldDiff.add("numberOfResidents");
      fieldDiff.add("insuredAmount");
    }

    fieldDiff.addAll(super.getFieldDiff(baseInsurance));

    return fieldDiff;
  }
}
