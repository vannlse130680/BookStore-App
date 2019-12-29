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
public class CreateUserError implements Serializable{
    String userId;
    String  password;
    String conPassword;
    String fullName;
    String address;
    String phone;

    public CreateUserError() {
    }

    public CreateUserError(String userId, String password, String conPassword, String fullName, String address, String phone) {
        this.userId = userId;
        this.password = password;
        this.conPassword = conPassword;
        this.fullName = fullName;
        this.address = address;
        this.phone = phone;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConPassword() {
        return conPassword;
    }

    public void setConPassword(String conPassword) {
        this.conPassword = conPassword;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    
}
