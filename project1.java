import java.util.*;

class Book {
    String title;
    String author;
    int id;
    boolean isAvailable;

    public Book(String title, String author, int id) {
        this.title = title;
        this.author = author;
        this.id = id;
        this.isAvailable = true;
    }
}

class Library {
    private Map<Integer, Book> books;
    private Queue<Integer> requestQueue;

    public Library() {
        books = new HashMap<>();
        requestQueue = new LinkedList<>();
    }

    public void addBook(String title, String author, int id) {
        books.put(id, new Book(title, author, id));
        System.out.println("Book added successfully: " + title);
    }

    public void borrowBook(int id) {
        if (books.containsKey(id)) {
            Book book = books.get(id);
            if (book.isAvailable) {
                book.isAvailable = false;
                System.out.println("You have borrowed: " + book.title);
            } else {
                requestQueue.offer(id);
                System.out.println("Book is currently unavailable. You have been added to the waitlist.");
            }
        } else {
            System.out.println("Book not found.");
        }
    }

    public void returnBook(int id) {
        if (books.containsKey(id)) {
            Book book = books.get(id);
            book.isAvailable = true;
            System.out.println("Book returned: " + book.title);
            
            if (!requestQueue.isEmpty() && requestQueue.peek() == id) {
                requestQueue.poll();
                System.out.println("The book is now available for the next person in the queue.");
            }
        } else {
            System.out.println("Invalid book ID.");
        }
    }

    public void displayBooks() {
        System.out.println("Library Books:");
        for (Book book : books.values()) {
            System.out.println(book.id + ". " + book.title + " by " + book.author + " - " + (book.isAvailable ? "Available" : "Checked Out"));
        }
    }
}

public class project1 {
    public static void main(String[] args) {
        Library library = new Library();
        library.addBook("lord of the rings", "jrr tolkien", 1);
        library.addBook("harry potter", "jk rowling", 2);
        
        library.displayBooks();
        
        library.borrowBook(1);
        library.displayBooks();
        
        library.returnBook(1);
        library.displayBooks();
    }
}
