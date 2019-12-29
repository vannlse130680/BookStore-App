<%-- 
    Document   : addCode
    Created on : Nov 27, 2019, 7:13:19 PM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Discount Code Page</title>
    </head>
    <body>
        <font color="red">
        Welcome, ${sessionScope.ADMIN}
        </font>
        <br>
        <a href="logout">Logout</a> <br> <br>
        <h1>Add A New Discount Code</h1>

        <form action="addCode">
            New Discount Code: <input type="text" name="txtCode" value="${param.txtCode}" />
            <font color="red">
            ${requestScope.ERROR}
            </font>
            
            <br>
            Percent: <select name="cbxPercent">
                <c:forEach begin="1" end="100" step="1" var="percent">
                    <c:choose>
                        <c:when test="${param.cbxPercent == percent}">
                            <option selected value="${percent}">${percent}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${percent}">${percent}</option>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
                            
            </select>
            <br>
            <c:set var="listUserId" value="${requestScope.LISTUSERID}"/>
            User ID mapping:<select name="cbxUserId">
                <c:forEach  var="id" items="${listUserId}">
                    <c:choose>
                        <c:when test="${param.cbxUserId == id}">
                            <option selected value="${id}">${id}</option>
                        </c:when>
                        <c:otherwise>
                            <option value="${id}">${id}</option>
                        </c:otherwise>
                    </c:choose>

                </c:forEach>
                            
            </select>
            <br>
            <input type="submit" value="Add" name="btAction"/>
            <input type="submit" value="Cancel" name="btAction" />
        </form>
    </body>
</html>
