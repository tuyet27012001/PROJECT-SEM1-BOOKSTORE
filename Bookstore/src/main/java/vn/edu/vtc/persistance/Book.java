package vn.edu.vtc.persistance;

public class Book {
  private int bookId;
  private String publishingCompanyName;
  private String title;
  private String author;
  private double price;
  private String detail;
  private int quantity;
  private String isbn;

  public Book(){
    
  }

  public int getBookId(){
    return bookId;
  }

  public String getPublishingCompanyName(){
    return publishingCompanyName;
  }

  public void setBookId(int bookId){
    this.bookId = bookId;
  }

  public void setPublishingCompanyName(String publishingCompanyName){
    this.publishingCompanyName = publishingCompanyName;
  }
  
  public void setTitle(String title){
    this.title = title;
  }

  public void setAuthor(String author){
    this.author= author;
  }

  public void setPrice(double price){
    this.price = price;
  }

  public void setDetail(String detail){
    this.detail = detail;
  }

  public void setQuantity(int quantity){
    this.quantity = quantity;
  }

  public void setIsbn(String isbn){
    this.isbn = isbn;
  }

  public String getTitle() {
    return title;
  }

  public String getAuthor() {
    return author;
  }

  public double getPrice() {
    return price;
  }

  public String getDetail() {
    return detail;
  }

  public int getQuantity() {
    return quantity;
  }

  public String getIsbn() {
    return isbn;
  }
}