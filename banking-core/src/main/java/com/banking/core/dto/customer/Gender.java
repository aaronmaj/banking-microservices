package com.banking.core.dto.customer;


public enum Gender {
  MALE("Male"), FEMALE("Female");

  private String description;

  Gender(String description) {
    this.description = description;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }
}
