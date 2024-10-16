public class Book {
    // Instance variables - To determined title, author and pages
  
    private String title;
   
    private String author;
   
    private int pages;

    // Constructor 1
    public Book(String title, String author, int pages) {
        this.title = title;
        this.author = author;
        this.pages = pages;
    }

    // Constructor 2
    public Book(String title, String author) {
        this(title, author, 0); // Calls back to the first constructor
    }

    // Prints details about the book
    public void printDetails() {
        System.out.println("Title: " + title);
        System.out.println("Author: " + author);
        System.out.println("Pages: " + pages);
    }

    // Returns a short description of the book
    public String getShortDescription() {
        return title + " by " + author;
    }

    // Returns whether the book is long or short
    public String isLong() {
        // An If/Else selection - if pages is greater than 300, return long; vice versa.
        if (pages > 300) {
            return "The book is long.";
        } else {
            return "The book is short.";
        }
    }
}
