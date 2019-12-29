/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.error;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class UpdateError implements Serializable{
    String title;
    String price;
    String author;
    String category;
    String importDate;
    String quantity;

    public UpdateError() {
    }

    public UpdateError(String title, String price, String author, String category, String importDate, String quantity) {
        this.title = title;
        this.price = price;
        this.author = author;
        this.category = category;
        this.importDate = importDate;
        this.quantity = quantity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
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

    public String getImportDate() {
        return importDate;
    }

    public void setImportDate(String importDate) {
        this.importDate = importDate;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    
    
    
    
    
    
}
