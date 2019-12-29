/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.cart;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Acer
 */
public class CartObj implements Serializable {

    private Map<String, Book> items;

    public Map<String, Book> getItems() {
        return items;
    }

    public void addItemToCart(Book b) {
        if (this.items == null) {
            this.items = new HashMap<>();

        }
        int amount = 0;
        if (this.items.containsKey(b.bookId)) {
            amount = this.items.get(b.bookId).amount + b.amount;
            b.setAmount(amount);
        }
        this.items.put(b.bookId, b);

    }

    public void removeItemFromCart(String bookId) {
        if (this.items == null) {
            return;
        }
        if (this.items.containsKey(bookId)) {
            this.items.remove(bookId);
            if (this.items.isEmpty()) {
                this.items = null;
            }
        }
    }

}
