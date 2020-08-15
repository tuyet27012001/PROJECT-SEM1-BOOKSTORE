package vn.edu.vtc.persistance;

import java.sql.Date;

public class Order {
  private int orderId;
  private String nameCustomer;
  private String paymentMethod;
  private String shippingUnit;
  private String address;
  private String orderStatus;
  private Date dateTime;

  public Order(){
    
  }
  
  public int getOrderId(){
    return orderId;
  }

  public String getPaymentMethod(){
    return paymentMethod;
  }

  public String getShippingUnit(){
    return shippingUnit;
  }

  public String getAddress(){
    return address;
  }
  
  public String getOrderStatus(){
    return orderStatus;
  }

  public Date getDateTime(){
    return dateTime;
  }

  public String getNameCustomer(){
    return nameCustomer;
  }

  public void setNameCustomer( String nameCustomer){
    this.nameCustomer = nameCustomer;
  }

  public void setPaymentMethod( String paymentMathod){
    this.paymentMethod = paymentMathod;
  }

  public void setShippingUnit(String shippingUnit){
    this.shippingUnit = shippingUnit;
  }

  public void setAddress(String address){
    this.address = address;
  }

  public void setOrderStatus(String orderStatus){
    this.orderStatus = orderStatus;
  }

  public void setDateTime(Date dateTime){
    this.dateTime = dateTime;
  }
}