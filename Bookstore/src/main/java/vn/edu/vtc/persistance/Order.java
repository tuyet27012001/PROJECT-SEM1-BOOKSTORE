package vn.edu.vtc.persistance;

import java.sql.Date;

public class Order {
  private int orderId;
  private String nameCustomer;
  private String paymentMethod;
  private String shippingUnit;
  private int address;
  private double shippingFee;
  private String orderStatus;
  private String dateTime;

  public Order(){
    
  }
  
  public int getOrderId(){
    return orderId;
  }

  public double getShippingFee(){
    return shippingFee;
  }

  public String getPaymentMethod(){
    return paymentMethod;
  }

  public String getShippingUnit(){
    return shippingUnit;
  }

  public int getAddress(){
    return address;
  }
  
  public String getOrderStatus(){
    return orderStatus;
  }

  public String getDateTime(){
    return dateTime;
  }

  public String getNameCustomer(){
    return nameCustomer;
  }

  public void setOrderId( int orderId){
    this.orderId = orderId;
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

  public void setAddress(int address){
    this.address = address;
  }

  public void setOrderStatus(String orderStatus){
    this.orderStatus = orderStatus;
  }

  public void setDateTime(String dateTime){
    this.dateTime = dateTime;
  }

  public void setShippingFee(Double shippingFee){
    this.shippingFee = shippingFee;
  }
}