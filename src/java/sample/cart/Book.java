/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.cart;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class Book implements Serializable{
    String bookId;
    String title;
    int amount;
    float price;

    public Book() {
    }

    public Book(String bookId, String title, int amount, float price) {
        this.bookId = bookId;
        this.title = title;
        this.amount = amount;
        this.price = price;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
    
    public float getTotal() {
        return  amount * price;
    }
    
   
}
