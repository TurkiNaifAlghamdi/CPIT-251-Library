public class Book {

    private String title;
    private String author;
    private int year;
    private int location;
    private String publisher;
    private int stock;
    private double price;
    private long isbn;
    private Book right;
    private Book left;

    // Constructors
    public Book(String title, String author, int year, int location, String publisher, int stock, double price, long isbn) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.location = location;
        this.publisher = publisher;
        this.stock = stock;
        this.price = price;
        this.isbn = isbn;
    }

    public Book(String title, String author, int year, int location, String publisher, int stock, double price, long isbn, Book right, Book left) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.location = location;
        this.publisher = publisher;
        this.stock = stock;
        this.price = price;
        this.isbn = isbn;
        this.right = right;
        this.left = left;
    }

    public Book getRight() {
        return right;
    }

    public void setRight(Book right) {
        this.right = right;
    }

    public Book getLeft() {
        return left;
    }

    public void setLeft(Book left) {
        this.left = left;
    }

    // Getters and Setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getLocation() {
        return location;
    }

    public void setLocation(int location) {
        this.location = location;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public long getIsbn() {
        return isbn;
    }

    public void setIsbn(long isbn) {
        this.isbn = isbn;
    }

    @Override
    public String toString() {
        return "Book{" + "title=" + title + ", author=" + author + ", year=" + year + ", location=" + location + 
                ", publisher=" + publisher + ", stock=" + stock + ", price=" + price + ", isbn=" + isbn + '}';
    }
    
    

}
