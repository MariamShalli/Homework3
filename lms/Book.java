package lms;

public class Book {
    String title, author;
    Student borrowedBy;

    public Book() {
    }

    public Book(String title, String author) {
        this.title = title;
        this.author = author;
        this.borrowedBy = null;
    }

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

    public Student getBorrowedBy() {
        return borrowedBy;
    }

    public void setBorrowedBy(Student borrowedBy) {
        this.borrowedBy = borrowedBy;
    }
}
