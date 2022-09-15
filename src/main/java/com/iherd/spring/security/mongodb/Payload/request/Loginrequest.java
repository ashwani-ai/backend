package com.iherd.spring.security.mongodb.Payload.request;

public class Loginrequest {
  private String mobilenumber;
  private String password;

  public String getmobilenumber() {
    return mobilenumber;
  }

  public void setmobilenumber(String mobilenumber) {
    this.mobilenumber = mobilenumber;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
}
