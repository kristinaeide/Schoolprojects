public class Book {
    private int id;              // unique
    private String title;
    private String author;
    private String isbn;
    private int pages;

    // Default constructor
    public Book() {
        this.id = 0;
        this.title = "";
        this.author = "";
        this.isbn = "";
        this.pages = 0;
    }

    // Parameterized constructor (5 params)
    public Book(int id, String title, String author, String isbn, int pages) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.pages = pages;
    }

    // Getters / Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }

    public int getPages() { return pages; }
    public void setPages(int pages) { this.pages = pages; }

    // Member method required by rubric
    public void printBookInfo() {
        System.out.println("--------------------------------------------------");
        System.out.println("ID:     " + id);
        System.out.println("Title:  " + title);
        System.out.println("Author: " + author);
        System.out.println("ISBN:   " + isbn);
        System.out.println("Pages:  " + pages);
        System.out.println("--------------------------------------------------");
    }
}