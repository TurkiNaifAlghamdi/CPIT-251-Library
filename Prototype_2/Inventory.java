
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Inventory {

    private Book root;
    private int totalInventoryCount;
    private double sales;
    private boolean loaded;

    public Inventory() {
        root = null;
        this.loaded = false;
        totalInventoryCount = 0;
        sales = 0;
    }

    // Check if Inventory is Empty
    public boolean isEmpty() {
        return root == null;
    }

    public int getTotalInventoryCount() {
        return totalInventoryCount;
    }

    public double getSales() {
        return sales;
    }

    // Get All Books from Books File
    public void loadBooks() throws FileNotFoundException {
        File input = new File("Books.txt");
        Scanner read = new Scanner(input);

        while (read.hasNext()) {
            String title = read.nextLine();
            String author = read.nextLine();
            int year = read.nextInt();
            int location = read.nextInt();
            read.nextLine();
            String publisher = read.nextLine();
            int stock = read.nextInt();
            double price = read.nextDouble();
            long isbn = read.nextLong();
            if (read.hasNext()) {
                read.nextLine();
            }

            totalInventoryCount += stock;
            root = insert(root, new Book(title, author, year, location, publisher, stock, price, isbn));
        }

        loaded = true;
    }

    // Add Book
    public void addBook(String title, String author, int year, int location, String publisher, int stock, double price, long isbn) throws FileNotFoundException {
        root = insert(root, new Book(title, author, year, location, publisher, stock, price, isbn));
        if (loaded) {
            updateBookList();
        }
    }

    public void deleteBookByTitle(String title) throws FileNotFoundException {
        root = deleteBookByTitle(root, title);
        if (loaded) {
            updateBookList();
        }
    }

    private Book insert(Book root, Book book) {
        if (root == null) {
            return book;
        }

        if (book.getTitle().compareTo(root.getTitle()) < 0) {
            root.setLeft(insert(root.getLeft(), book));
        } else if (book.getTitle().compareTo(root.getTitle()) > 0) {
            root.setRight(insert(root.getRight(), book));
        }

        return root;
    }

    // Delete by Title
    private Book deleteBookByTitle(Book root, String title) {
        if (root == null) {
            return root;
        }

        int compareResult = title.compareTo(root.getTitle());

        if (compareResult < 0) {
            root.setLeft(deleteBookByTitle(root.getLeft(), title));
        } else if (compareResult > 0) {
            root.setRight(deleteBookByTitle(root.getRight(), title));
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }

            root.setTitle(minValue(root.getRight()).getTitle());
            root.setRight(deleteBookByTitle(root.getRight(), root.getTitle()));
        }

        return root;
    }

    private Book minValue(Book root) {
        Book min = root;
        while (root.getLeft() != null) {
            min = root.getLeft();
            root = root.getLeft();
        }
        return min;
    }

    // Print all books
    public void displayAllBooks() {
        displayAllBooks(root);
    }

    private void displayAllBooks(Book root) {
        if (root != null) {
            displayAllBooks(root.getLeft());
            System.out.println(root.toString());
            displayAllBooks(root.getRight());
        }
    }

    // Update Inventory File
    public void updateBookList() throws FileNotFoundException {
        File output = new File("Books.txt");
        PrintWriter out = new PrintWriter(output);
        updateBookList(root, out);
        out.flush();
        out.close();
    }

    private void updateBookList(Book root, PrintWriter out) {
        if (root != null) {
            updateBookList(root.getLeft(), out);
            out.println(root.getTitle() + "\n" + root.getAuthor() + "\n" + root.getYear() + "\n" + root.getLocation() + "\n"
                    + root.getPublisher() + "\n" + root.getStock() + "\n" + root.getPrice() + "\n" + root.getIsbn());

            updateBookList(root.getRight(), out);
        }
    }

    // Decrease Stock of a book / Sell a Book
    public void decreaseStock(String title, int quantity) throws FileNotFoundException {
        Book book = searchBook(root, title);
        if (book != null) {
            int currentStock = book.getStock();
            if (currentStock >= quantity) {
                book.setStock(currentStock - quantity);
                totalInventoryCount -= quantity;
                System.out.println("Stock decreased for book: " + title);
                if (book.getStock() <= 5) {
                    increaseStock(book.getTitle(), 10);
                } else {
                    updateBookList();
                }
            } else {
                System.out.println("Insufficient stock for book: " + title);
            }
        } else {
            System.out.println("Book not found: " + title);
        }
    }

    // Order Books
    public void increaseStock(String title, int quantity) throws FileNotFoundException {
        Book book = searchBook(root, title);
        if (book != null) {
            book.setStock(book.getStock() + quantity);
            totalInventoryCount += quantity;
            updateBookList();
            System.out.println("Stock increased for book: " + title);
        } else {
            System.out.println("Book not found: " + title);
        }
    }

    // Search for books
    private Book searchBook(Book currentBook, String title) {
        title = title.toLowerCase();
        if (currentBook == null || currentBook.getTitle().toLowerCase().equals(title)) {
            return currentBook;
        }
        if (title.compareTo(currentBook.getTitle().toLowerCase()) < 0) {
            return searchBook(currentBook.getLeft(), title);
        } else {
            return searchBook(currentBook.getRight(), title);
        }
    }
    
    public Book searchBook(String title) {
        return searchBook(root, title);
    }

}
