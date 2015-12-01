<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>List of contacts</title>

    <link rel="stylesheet" href="/style/main.css">
</head>
<body>
    <ul>
        <li><a class="active" href="/jsp/modify.jsp">Add contact</a></li>
        <li><a href="/jsp/modify.jsp">Edit contact</a></li>
        <li><a href="/jsp/main.jsp">Delete contact</a></li>
        <li><a href="/jsp/search.jsp">Search contact</a></li>
        <li><a href="/jsp/email.jsp">Send Email</a></li>
    </ul>

    <c:if test="${not empty contacts}">
        <table>
            <thead>
            <tr>
                <th></th>
                <th>Full name</th>
                <th>BirthDay</th>
                <th>Address</th>
                <th>Company</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${contacts}" var="contact">
                <tr>
                    <td></td>
                    <td><c:out value="${ contact.id }"/></td>
                    <td><c:out value="${ contact.name }"/></td>
                    <td><c:out value="${ contact.surname }"/></td>
                    <td><c:out value="${ contact.login }"/></td>
                    <td><c:out value="${ contact.email }"/></td>
                    <td><c:out value="${ contact.number }"/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</body>
</html>
