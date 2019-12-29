/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_book;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class Tbl_BookDTO implements Serializable{
    String bookId;
    String title;
    String image;
    float price;
    String author;
    String category;
    int quantity;
    String description;
    String importDate;
    String status;

    public Tbl_BookDTO() {
    }

    public Tbl_BookDTO(String bookId, String title, String image, float price, String author, String category, int quantity, String description, String importDate, String status) {
        this.bookId = bookId;
        this.title = title;
        this.image = image;
        this.price = price;
        this.author = author;
        this.category = category;
        this.quantity = quantity;
        this.description = description;
        this.importDate = importDate;
        this.status = status;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
}
