package com.iherd.spring.security.mongodb.models;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "roles")  
public class Role {
    
    @Id
    private String id;
  
    private ERole mobilenumber;
  
    public Role() {
  
    }
  
    public Role(ERole mobilenumber) {
      this.mobilenumber = mobilenumber;
    }
  
    public String getId() {
      return id;
    }
  
    public void setId(String id) {
      this.id = id;
    }
  
    public ERole getmobilenumber() {
      return mobilenumber;
    }
  
    public void setName(ERole mobilenumber) {
      this.mobilenumber = mobilenumber;
    }



}
