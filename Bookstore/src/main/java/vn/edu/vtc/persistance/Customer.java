package vn.edu.vtc.persistance;



public class Customer {
  private int idCustomer;
  private String name;
  private String gender;
  private String birthDate;
  private String phone;
  private String email;
  private String password;

  public Customer() {
    
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

  public String getBirthDate() {
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

  public void setBirthDate(String birthDate) {
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