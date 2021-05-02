package com.insurely.domain.insurance;

import java.util.HashSet;
import java.util.Set;

public class MopedInsurance extends BaseInsurance {
  private String brand;
  private String model;
  private String renewalDate;

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

  public Set<String> getFieldDiff(BaseInsurance baseInsurance) {
    Set<String> fieldDiff = new HashSet<>();

    if(baseInsurance.getClass().equals(this.getClass())) {
      MopedInsurance mopedInsurance = (MopedInsurance) baseInsurance;
      if (mopedInsurance.brand == null && !(this.brand == null)) {
        fieldDiff.add("brand");
      }
      if (mopedInsurance.model == null && !(this.model == null)) {
        fieldDiff.add("model");
      }
      if (mopedInsurance.renewalDate == null && !(this.renewalDate == null)) {
        fieldDiff.add("renewalDate");
      }
    } else {
      fieldDiff.add("brand");
      fieldDiff.add("model");
      fieldDiff.add("renewalDate");
    }

    fieldDiff.addAll(super.getFieldDiff(baseInsurance));

    return fieldDiff;
  }
}
