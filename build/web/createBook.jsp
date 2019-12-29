<%-- 
    Document   : createBook
    Created on : Nov 25, 2019, 2:58:37 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Book Page</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.ADMIN}
        </font>
        <br>
        <a href="logout">Logout</a> <br> <br>
        <h1>Create A New Book</h1>
        <form action="createBook">
            <c:set var="error" value="${requestScope.CREATEERROR}"/>
            Book ID: <input type="text" name="txtBookId" value="${param.txtBookId}" />
            <c:if test="${not empty error.bookId}">
                <font color="red">
                ${error.bookId}
                </font>
            </c:if>
            <br>
            Title: <input type="text" name="txtTitle" value="${param.txtTitle}" /> 
            <c:if test="${not empty error.title}">
                <font color="red">
                ${error.title} 
                </font>
            </c:if>
            <br>
            Price: <input type="text" name="txtPrice" value="${param.txtPrice}" /> 
            <c:if test="${not empty error.price}">
                <font color="red">
                ${error.price} 
                </font>
            </c:if>
            <br>
            Author <input type="text" name="txtAuthor" value="${param.txtAuthor}" /> 
            <c:if test="${not empty error.auhtor}">
                <font color="red">
                ${error.auhtor} 
                </font>
            </c:if>
            <br>
            Category  <select name="categoryName">
                <c:set var="listCategory" value="${sessionScope.LISTCATEGORY}"/>
                <c:forEach var="category" items="${listCategory}">
                    <c:choose>
                        <c:when test="${param.categoryName == category.categoryName}">
                            <option selected value="${category.categoryId}">${category.categoryName}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${category.categoryId}">${category.categoryName}</option>
                        </c:otherwise>
                    </c:choose>


                </c:forEach>

            </select>
            <br>
            Quantity: <input type="text" name="txtQuantity" value="${param.txtQuantity}" /> 
            <c:if test="${not empty error.quantity}">
                <font color="red">
                ${error.quantity} 
                </font>
            </c:if>
            <br>
            Description <input type="text" name="txtDescription" value="${param.txtDescription}" /> 
            <c:if test="${not empty error.description}">
                <font color="red">
                ${error.description} 
                </font>
            </c:if>
            <br>

           <img src="" width="100px" height="100px" alt="No image" id="img${counter.count}"/> <br>

            File <input type="file"  value="" id="file${counter.count}"/>

            <c:if test="${not empty error.image}">
                <font color="red">
                ${error.image} 
                </font>
            </c:if>

            <br>

            <script>
                window.addEventListener('load', function () {
                    document.getElementById('file${counter.count}').addEventListener('change', function () {
                        if (this.files && this.files[0]) {
                            var img = document.getElementById('img${counter.count}');
                            var source = document.getElementById('source${counter.count}');
                            var file = document.getElementById('file${counter.count}');
                            source.setAttribute('value', file.value);
                            img.src = file.value;
                        }
                    });
                });
            </script>
            <input type="hidden" name="source" value="" id="source${counter.count}" />
            
            <input type="submit" value="Create" name="btAction"/>
            <input type="submit" value="Cancel" name="btAction"/>
        </form>
    </body>
</html>
