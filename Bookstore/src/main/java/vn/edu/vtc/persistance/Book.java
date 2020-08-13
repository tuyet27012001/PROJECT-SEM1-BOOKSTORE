package vn.edu.vtc.persistance;

public class Book {
  private int bookId;
  private int publishingCompanyName;
  private String title;
  private String author;
  private double price;
  private String detail;
  private int quantity;
  private String isbn;

  public Book(int bookId, int publishingCompanyName, String title, String author, double price, String detail, int quantity, String isbn){
    this.bookId = bookId;
    this.publishingCompanyName = publishingCompanyName;
    this.title = title;
    this.author = author;
    this.price = price;
    this.detail = detail;
    this.quantity = quantity;
    this.isbn = isbn;
  }

  public int getBookId(){
    return bookId;
  }

  public int getPublishingCompanyName(){
    return publishingCompanyName;
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