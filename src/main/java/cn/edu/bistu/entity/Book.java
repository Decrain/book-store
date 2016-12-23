package cn.edu.bistu.entity;

/**
 * Created by hongyu on 2016/12/23.
 */
public class Book {
    private int bookId;
    private double price;
    private String isbn;
    private String name;
    private String author;
    private String press;

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", price=" + price +
                ", isbn='" + isbn + '\'' +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", press='" + press + '\'' +
                '}';
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public Book() {
    }

    public Book(double price, String isbn, String name, String author, String press) {
        this.price = price;
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.press = press;
    }

    public Book(int bookId, double price, String isbn, String name, String author, String press) {
        this.bookId = bookId;
        this.price = price;
        this.isbn = isbn;
        this.name = name;
        this.author = author;
        this.press = press;
    }
}
