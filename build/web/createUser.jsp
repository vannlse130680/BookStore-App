<%-- 
    Document   : createUser
    Created on : Nov 25, 2019, 2:15:22 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create User Page</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.ADMIN}
        </font>
        <br>
        <a href="logout">Logout</a>
        <h1>Create A New user</h1>
        
        
        <form method="POST" action="createUser">
            <c:set var="error" value="${requestScope.CREATEERROR}"/>
            User ID: <input type="text" name="txtUserId" value="${param.txtUserId}" />
            <c:if test="${not empty error.userId}">
                <font color="red">
                ${error.userId} 
                </font>
            </c:if>
            <br>
            Password :<input type="password" name="txtPassword" value="" />
            <c:if test="${not empty error.password}">
                <font color="red">
                ${error.password} 
                </font>
            </c:if>
            <br>
            Confirm Password: <input type="password" name="txtConPassword" value="" />
            <c:if test="${not empty error.conPassword}">
                <font color="red">
                ${error.conPassword} 
                </font>
            </c:if>
            <br>
            Full Name: <input type="text" name="txtFullName" value="${param.txtFullName}" />
            <c:if test="${not empty error.fullName}">
                <font color="red">
                ${error.fullName} 
                </font>
            </c:if>
            <br>
            Address: <input type="text" name="txtAddress" value="${param.txtAddress}" /> 
            <c:if test="${not empty error.address}">
                <font color="red">
                ${error.address} 
                </font>
            </c:if>
            <br>
            Phone: <input type="text" name="txtPhone" value="${param.txtPhone}" /> 
            <c:if test="${not empty error.phone}">
                <font color="red">
                ${error.phone} 
                </font>
            </c:if>
            <br>
            
            <input type="submit" value="Create" name="btAction" />
            <input type="submit" value="Cancel" name="btAction" />
        </form>
    </body>
</html>
