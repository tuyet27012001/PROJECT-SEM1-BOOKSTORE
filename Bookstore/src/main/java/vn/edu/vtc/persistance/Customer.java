package vn.edu.vtc.persistance;

import java.sql.Date;

public class Customer {
  private int idCustomer;
  private String name;
  private String gender;
  private Date birthDate;
  private String phone;
  private String email;
  private String password;

  public Customer() {
    this.idCustomer = idCustomer;
    this.name = name;
    this.gender = gender;
    this.birthDate = birthDate;
    this.phone = phone;
    this.email = email;
    this.password = password;
  }

  public int getIdCustomer() {
    return idCustomer;
  }

  public String getName() {
    return name;
  }

  public String getGender() {
    return gender;
  }

  public Date getBirthDate() {
    return birthDate;
  }

  public String getPhone() {
    return phone;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public void setIdCustomer(int idCustomer) {
    this.idCustomer = idCustomer;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setGender(String gender) {
    this.gender = gender;
  }

  public void setBirthDate(Date birthDate) {
    this.birthDate = birthDate;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setPassword(String password) {
    this.password = password;
  }

}