package com.iherd.spring.security.mongodb.Payload.request;

import java.util.Set;

public class Signuprequest {

  private Set<String> roles;
  private String mobilenumber;
  private String password;
  private String confirmpassword;

  public String getmobilenumber() {
    return mobilenumber;
  }

  public void setmobilenumber(String mobilenumber) {
    this.mobilenumber = mobilenumber;
  }

  public String getpassword() {
    return password;
  }

  public void setpassword(String password) {
    this.password = password;
  }

  public String getconfirmPassword() {
    return confirmpassword;
  }

  public void setconfirmPassword(String confirmpassword) {
    this.confirmpassword = confirmpassword;
  }

  public Set<String> getRoles() {
    return this.roles;
  }

  public void setRole(Set<String> roles) {
    this.roles = roles;
  }
}
