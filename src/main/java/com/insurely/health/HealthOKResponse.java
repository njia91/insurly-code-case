package com.insurely.health;

public class HealthOKResponse {
  private String status;

  public HealthOKResponse(String status) {
    this.status = status;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }
}
