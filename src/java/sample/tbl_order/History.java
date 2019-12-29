/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sample.tbl_order;

import java.io.Serializable;

/**
 *
 * @author Acer
 */
public class History implements Serializable{
    String orderID;
    String date;
    float allTotal;
    String title;
    int amount;
    float total;
    String code;

    public History() {
    }

    public History(String orderID, String date, float allTotal, String title, int amount, float total, String code) {
        this.orderID = orderID;
        this.date = date;
        this.allTotal = allTotal;
        this.title = title;
        this.amount = amount;
        this.total = total;
        this.code = code;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getAllTotal() {
        return allTotal;
    }

    public void setAllTotal(float allTotal) {
        this.allTotal = allTotal;
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

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
   
}
