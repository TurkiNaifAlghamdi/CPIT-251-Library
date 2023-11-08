package library;

  public class Inventory {
      private Book Inventory;
    private Book root;
    private int totalInventoryCount;

    public Inventory() {
        root = null;
        totalInventoryCount = 0;
    }

    public void decreaseStock(String title, int quantity) {
        Book book = searchBook(root, title);
        if (book != null) {
            int currentStock = book.getStock();
            if (currentStock >= quantity) {
                book.setStock(currentStock - quantity);
                totalInventoryCount -= quantity;
                System.out.println("Stock decreased for book: " + title);
            } else {
                System.out.println("Insufficient stock for book: " + title);
            }
        } else {
            System.out.println("Book not found: " + title);
        }
    }

    public void increaseStock(String title, int quantity) {
        Book book = searchBook(root, title);
        if (book != null) {
            book.setStock(book.getStock() + quantity);
            totalInventoryCount += quantity;
            System.out.println("Stock increased for book: " + title);
        } else {
            System.out.println("Book not found: " + title);
        }
    }

    // Other methods for managing the book inventory, such as adding, removing, and searching books

    // Helper method to search for a book recursively in the binary tree
    private Book searchBook(Book currentBook, String title) {
        if (currentBook == null || currentBook.getTitle().equals(title)) {
            return currentBook;
        }
        if (title.compareTo(currentBook.getTitle()) < 0) {
            return searchBook(currentBook.getLeft(), title);
        } else {
            return searchBook(currentBook.getRight(), title);
        }
    }

    // Helper method to add a book recursively to the binary tree
    private Book addBook(Book currentBook, Book newBook) {
        if (currentBook == null) {
            currentBook = newBook;
            return currentBook;
        }
        if (newBook.getTitle().compareTo(currentBook.getTitle()) < 0) {
            currentBook.setLeft(addBook(currentBook.getLeft(), newBook));
        } else {
            currentBook.setRight(addBook(currentBook.getRight(), newBook));
        }
        return currentBook;
    }

    public void addBook(Book book) {
        root = addBook(root, book);
        totalInventoryCount += book.getStock();
    }

    // Other methods for managing the book inventory, such as removing and searching books

    // Getters for the variables

    public int getTotalInventoryCount() {
        return totalInventoryCount;
    }

    public Book getRoot() {
        return root;
    }
}
