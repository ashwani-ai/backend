package com.iherd.spring.security.mongodb.models;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
@Document(collection = "users")
public class User {
    @Id
    private String id;
    private String mobilenumber;
    private String password;
  
    @DBRef
    private Set<Role> roles = new HashSet<>();
  
    public User() {
    }
  
    public User(String mobilenumber, String password) {

      this.mobilenumber = mobilenumber;
      this.mobilenumber = mobilenumber;
      this.password = password;
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
  
    public void setmobilenumber(String mobilenumber) {
      this.mobilenumber = mobilenumber;
    }
  
    public String getPassword() {
      return password;
    }
  
    public void setPassword(String password) {
      this.password = password;
    }
  
    public Set<Role> getRoles() {
      return roles;
    }
  
    public void setRoles(Set<Role> roles) {
      this.roles = roles;
    }
  }

