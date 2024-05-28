package lms;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class LMS {
    List<Book> books = new ArrayList<>();

    public void addBook(Book book) {
        books.add(book);
    }

    public boolean removeBook(Book book) {
        return books.remove(book);
    }

    public boolean borrowBook(Book book, Student student) {
        if (book.getBorrowedBy() == null) {
            book.setBorrowedBy(student);
            return true;
        }
        return false;
    }

    public boolean returnBook(Book book) {
        if (book.getBorrowedBy() != null) {
            book.setBorrowedBy(null);
            return true;
        }
        return false;
    }

    public void saveState(String filePath) {
        saveState(filePath, true);
    }

    public void saveState(String filePath, boolean printToConsole) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Book book : books) {
                StringBuilder bookInfo = new StringBuilder("Book: " + book.getTitle() + ", " + book.getAuthor());
                if (book.getBorrowedBy() != null) {
                    bookInfo.append(" - Borrowed by: ").append(book.getBorrowedBy().getName()).append(" ")
                            .append(book.getBorrowedBy().getSurname()).append(" (")
                            .append(book.getBorrowedBy().getPersonalNumber()).append(")");
                }
                writer.write(bookInfo.toString());
                writer.newLine();
                if (printToConsole) {
                    System.out.println(bookInfo.toString());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean loadState(String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            books.clear();
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(" - Borrowed by: ");
                String[] bookInfo = parts[0].substring(6).split(", ");
                Book book = new Book(bookInfo[0], bookInfo[1]);
                if (parts.length > 1) {
                    String[] borrowerInfo = parts[1].split(" ");
                    String name = borrowerInfo[0];
                    String surname = borrowerInfo[1];
                    String personalNumber = borrowerInfo[2].substring(1, borrowerInfo[2].length() - 1);
                    Student student = new Student(name, surname, personalNumber);
                    book.setBorrowedBy(student);
                }
                books.add(book);
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }
}
