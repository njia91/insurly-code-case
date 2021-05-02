package com.insurely.domain.insurance;

import java.util.HashSet;
import java.util.Set;

/** The Insurely insurance model. */
public class CarInsurance extends BaseInsurance {
  private String registrationNo;
  private String brand;
  private String model;
  private String renewalDate;
  private String coverage;

  public String getRegistrationNo() {
    return registrationNo;
  }

  public void setRegistrationNo(String registrationNo) {
    this.registrationNo = registrationNo;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public String getModel() {
    return model;
  }

  public void setModel(String model) {
    this.model = model;
  }

  public String getRenewalDate() {
    return renewalDate;
  }

  public void setRenewalDate(String renewalDate) {
    this.renewalDate = renewalDate;
  }

  public String getCoverage() {
    return coverage;
  }

  public void setCoverage(String coverage) {
    this.coverage = coverage;
  }

  public Set<String> getFieldDiff(BaseInsurance baseInsurance) {
    Set<String> fieldDiff = new HashSet<>();

    if(baseInsurance.getClass().equals(this.getClass())) {
      CarInsurance carInsurance = (CarInsurance) baseInsurance;

      if (carInsurance.registrationNo == null && !(this.registrationNo == null)) {
        fieldDiff.add("registrationNo");
      }
      if (carInsurance.brand == null && !(this.brand == null)) {
        fieldDiff.add("brand");
      }
      if (carInsurance.model == null && !(this.model == null)) {
        fieldDiff.add("model");
      }
      if (carInsurance.renewalDate == null && !(this.renewalDate == null)) {
        fieldDiff.add("renewalDate");
      }
      if (carInsurance.coverage == null && !(this.coverage == null)) {
        fieldDiff.add("coverage");
      }
    } else {
      fieldDiff.add("registrationNo");
      fieldDiff.add("brand");
      fieldDiff.add("model");
      fieldDiff.add("renewalDate");
      fieldDiff.add("coverage");

    }


    fieldDiff.addAll(super.getFieldDiff(baseInsurance));

    return fieldDiff;
  }
}
