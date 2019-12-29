<%-- 
    Document   : viewHistory
    Created on : Nov 26, 2019, 4:25:45 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
    </head>
    <body onload="myFunction()">
        
        <font color="red">
        Welcome, ${sessionScope.USER}
        </font>
        <br>
        <a href="logout">Logout</a> <br> <br>
        <h1>View History Shopping</h1>
      
        Search History By:
        <form action="searchHistory">
            Book Title : <input type="text" name="txtTitle" value="${param.txtTitle}" /> 
            <input type="submit" value="Search By Title"  name="btAction"/> <br>
            Shopping Date: <input type="text" name="txtDate" value="${param.txtDate}" placeholder="YYYY-MM-DD" />
           <input type="submit" value="Search By Date"  name="btAction"/>
           <input type="hidden" name="txtUserId" value="${sessionScope.USERID}" />
        </form>
        

        <c:set var="result" value="${requestScope.HISTORY}"/>


        <c:if test="${not empty result}">


            <table border="1">
                <thead>
                    <tr>
                        <th>NO.</th>
                        <th>Order ID</th>
                        <th>Date</th>
                        <th>Total All</th>
                        <th>Book Title</th>
                        <th>Amount</th>
                        <th>Total</th>
                        <th>Discount Code is used</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="h" items="${result}" varStatus="counter">

                        <tr>
                            <td>
                                ${counter.count}

                            </td>
                            <td>
                                ${h.orderID}

                            </td>
                            <td>
                               ${h.date}

                            </td>
                            <td>
                                ${h.allTotal}

                            </td>
                            <td>
                               ${h.title}

                            </td>
                            <td>
                               ${h.amount}

                            </td>
                            <td>
                                ${h.total}

                            </td>
                            <td>
                                ${h.code}

                            </td>

                    

                </tr>


            </c:forEach>

        </tbody>
    </table> 
</c:if>
<c:if test="${empty result}">
    <h2>No history !</h2>

</c:if>
    <c:set var="invalidDate" value="${requestScope.INVALIDDATE}"/>
   <script>
    function myFunction() {
    <c:if test="${not empty invalidDate}">
        alert("${invalidDate}");
    </c:if>
        
   

    }

</script>
    <a href="search">Back to shopping page ...</a>
</body>
</html>
