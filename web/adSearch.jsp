<%-- 
    Document   : search
    Created on : Nov 24, 2019, 9:44:38 AM
    Author     : Acer
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Search Page</title>
    </head>
    <body>

        <font color="red">
        Welcome, ${sessionScope.ADMIN}
        </font>
        <br>
        <a href="logout">Logout</a> <br> <br>
        <a href="createUser.jsp">Create A New User</a> <br>
        <a href="createBook.jsp">Create A New Book</a> <br>
        <a href="preAddCode">Add Discount code</a>
        <h1>AD Search Page</h1>
        <form action="searchBy">
            Search By
            <select name="searchBy">
                <option>Optional</option>
                <option>Book Title</option>
                <option>Book Category</option>
                <option>Book Price</option>
            </select>
            <input type="submit" value="Ok" /> 
        </form>
        <br>
        <c:set var="searchOption" value="${requestScope.SEARCHBY}"/>
        <c:if test="${not empty searchOption}">
            Search by ${searchOption}
            <form action="Search">
                <c:if test="${searchOption == 'title'}">
                    Title <input type="text" name="txtTitle" value="${param.txtTitle}" />
                </c:if>
                <c:if test="${searchOption == 'category'}">
                    <c:set var="listCategory" value="${sessionScope.LISTCATEGORY}"/>
                    <select name="categoryName">
                        <option>Category</option>
                        <c:forEach var="category" items="${listCategory}">
                            <c:choose>
                                <c:when test="${param.categoryName == category.categoryName}">
                                    <option selected value="${category.categoryName}">${category.categoryName}</option>
                                </c:when>
                                <c:otherwise>
                                    <option value="${category.categoryName}">${category.categoryName}</option>
                                </c:otherwise>
                            </c:choose>


                        </c:forEach>
                    </select>

                </c:if>
                <c:if test="${searchOption == 'price'}">
                    Minimum price <input type="text" name="txtMin" value="${param.txtMin}" />
                    Maximum price <input type="text" name="txtMax" value="${param.txtMax}" />
                </c:if>
                <input type="hidden" name="option" value="${searchOption}" />
                <input type="submit" value="Search" />
            </form>
        </c:if>



        <c:set var="result" value="${requestScope.SEARCHRESULT}"/>


        <c:if test="${not empty result}">


            <table border="1">
                <thead>
                    <tr>
                        <th>NO.</th>
                        <th>Image</th>
                        <th>Title</th>
                        <th>Description</th>
                        <th>Price</th>
                        <th>Author</th>
                        <th>Category</th>
                        <th>Delete</th>
                        <th>Update</th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="bookDto" items="${result}" varStatus="counter">
                    <form action="deleteBook">
                        <tr>
                            <td>
                                ${counter.count}

                            </td>
                            <td>
                                <img src="${bookDto.image}" width="100px" height="100px"/>

                            </td>
                            <td>
                                ${bookDto.title}

                            </td>
                            <td>
                                ${bookDto.description}

                            </td>
                            <td>
                                ${bookDto.price}

                            </td>
                            <td>
                                ${bookDto.author}

                            </td>
                            <td>
                                ${bookDto.category}

                            </td>
                            <td>
                                <input type="hidden" name="txtBookId" value="${bookDto.bookId}" />

                                <input type="hidden" name="categoryName" value="${param.categoryName}" />
                                <input type="hidden" name="txtTitle" value="${param.txtTitle}" />
                                <input type="hidden" name="txtMin" value="${param.txtMin}" />
                                <input type="hidden" name="txtMax" value="${param.txtMax}" />
                                <input type="hidden" name="option" value="${searchOption}" />
                                <input type="submit" value="Delete" onclick="return confirm('Are you sure you want to delete this item?');"/>

                            </td>
                            <td>
                                <c:url var="updateLink" value="updateBook.jsp">
                                    <c:param name="txtBookId" value="${bookDto.bookId}"/>
                                    <c:param name="txtTitle" value="${bookDto.title}"/>
                                    <c:param name="txtPrice" value="${bookDto.price}"/>
                                    <c:param name="txtAuthor" value="${bookDto.author}"/>
                                    <c:param name="categoryName" value="${bookDto.category}"/>
                                    <c:param name="txtImportDate" value="${bookDto.importDate}"/>
                                    <c:param name="txtQuantity" value="${bookDto.quantity}"/>
                                </c:url>
                                <a href="${updateLink}">Update</a>

                            </td>
                        </tr>
                    </form>

                </c:forEach>

            </tbody>
        </table> 
    </c:if>
    <c:if test="${empty result}">
        <h2>No record is matched</h2>

    </c:if>
</body>
</html>
