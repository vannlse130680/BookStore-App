<%-- 
    Document   : viewCart
    Created on : Nov 25, 2019, 8:45:41 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Cart Page</title>
    </head>
    <body onload="myFunction()" >
        <font color="red">
        Welcome, ${sessionScope.USER}
        </font>
        <br>
        <a href="logout">Logout</a> <br> <br>
        <h1>View Your Cart</h1>
        <c:set var="session" value="${pageContext.session}"/>
        <c:if test="${not empty session}">
            <c:set var="cart" value="${sessionScope.CART}"/>
            <c:if test="${not empty cart}">
                <c:set var="items" value="${cart.items}"/>
                <c:if test="${not empty items}">

                    <table border="1">
                        <thead>
                            <tr>
                                <th>NO.</th>
                                <th>Title</th>
                                <th>Amount</th>
                                <th>Price</th>
                                <th>Total</th>
                                <th>Remove</th>
                                <th>Update</th>
                            </tr>
                        </thead>

                        <tbody>

                            <c:forEach var="item" items="${items.entrySet()}" varStatus="counter">
                            <form action="updateItemsFromCart">
                                <tr>
                                    <td>
                                        ${counter.count}
                                    </td>
                                    <td>
                                        ${item.value.title}
                                    </td>
                                    <td>

                                        <input type="text" name="txtAmount" value="${item.value.amount}" />
                                    </td>
                                    <td>
                                        ${item.value.price}
                                    </td>
                                    <td>

                                        ${item.value.total}
                                        <c:set var="total" value="${total + item.value.total}"/>
                                    </td>


                                    <td>
                                        <input type="hidden" name="txtBookId" value="${item.value.bookId}" />

                                        <input type="submit" value="Update"  />
                                    </td>
                            </form>
                            <form action="removeItemsFromCart">
                                <td>
                                    <input type="hidden" name="txtBookId" value="${item.value.bookId}" />
                                    <input type="submit" value="Remove" onclick="return confirm('Are you sure you want to delete this item?');" />
                                </td>
                            </form>
                        </tr>
                    </c:forEach>

                    <tr>
                        <td colspan="7">
                            Total : ${total}
                        </td>



                    </tr>


                </tbody>

            </table>
            <form action="useDiscountCode">

                Enter Discount Code <input type="text" name="txtDCode" value="" /> 
                <input type="submit" value="USE" />
                <br>
                <c:set var="validCode" value="${sessionScope.VALIDCODE}"/>
                <c:set var="invalidCode" value="${requestScope.INVALIDCODE}"/>

                <c:if test="${not empty validCode}">
                    <c:set var="percent" value="${sessionScope.PERCENT}"/>
                     <c:set var="total" value="${total - total * percent / 100}"/>
                    CODE : ${validCode} 
                    
                    <br>
                    Total after discount (${percent}%): ${total}
                   
                </c:if>
                <c:if test="${not empty invalidCode}">
                    <font color="red">
                     Invalid code or this code is used !
                    </font>
                </c:if>

            </form>
            <form action="confirm">
                <input type="hidden" name="txtTotal" value="${total}" />
                <input type="submit" value="Confirm" />
            </form>



        </c:if>
    </c:if>

</c:if>
<c:if test="${empty items}">
    <h2>No cart is existed!!!!</h2>
</c:if>

<a href="search">Add more book to your cart !!</a>
<c:set var="invalidAmount" value="${requestScope.INVALIDAMOUNT}"/>
<c:set var="outOfStock" value="${requestScope.OUTOFSTOCK}"/>
<script>
    function myFunction() {
    <c:if test="${not empty invalidAmount}">
        alert("${invalidAmount}");
    </c:if>
        
    <c:if test="${not empty outOfStock}">
        alert("${outOfStock}");
    </c:if>

    }

</script>
</body>
</html>
