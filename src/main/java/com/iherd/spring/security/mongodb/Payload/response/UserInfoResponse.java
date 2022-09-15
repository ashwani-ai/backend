package com.iherd.spring.security.mongodb.Payload.response;

import java.util.List;


public class UserInfoResponse {
  private String id;
  private String mobilenumber;
  private List<String> roles;

  public UserInfoResponse(String id,  String mobilenumber, List<String> roles) {
    this.id = id;
    this.mobilenumber = mobilenumber;
    this.roles = roles;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getmobilenumber() {
    return mobilenumber;
  }

  public void setEmail(String mobilenumber) {
    this.mobilenumber = mobilenumber;
  }


  public List<String> getRoles() {
    return roles;
  }






}
