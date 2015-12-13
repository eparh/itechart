<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My contacts</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/style/main.css">
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
</head>
<body>

    <nav class="navbar navbar-inverse">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="/controller?command=show" ><span class="glyphicon glyphicon-user"></span>My Contacts</a>
            </div>
            <div>
                <ul class="nav navbar-nav">
                    <li><a href="/jsp/create.jsp"><span class="glyphicon glyphicon-plus"></span>Add contact</a></li>
                    <li><a href="/jsp/edit.jsp"><span class="glyphicon glyphicon-pencil"></span>Edit contact</a></li>
                    <li><a href="javascript:{}" onclick="document.getElementById('delete').submit(); return false;"><span class="glyphicon glyphicon-trash"></span> Delete contact</a></li>
                    <li><a href="/jsp/search.jsp"><span class="glyphicon glyphicon-search"></span> Search contacts</a></li>
                    <li><a href="/jsp/email.jsp"><span class="glyphicon glyphicon-send"></span> Send Email </a></li>
                </ul>
            </div>
        </div>
    </nav>

    <form action="/controller" id="delete" method="post">
        <input type="hidden" name="command" form="delete" value="delete">
    </form>

    <table class="table table-striped .table-bordered .table-hover">
        <thead>
            <tr>
                <th></th>
                <th>Full name</th>
                <th>Birthday</th>
                <th>Address</th>
                <th>Company</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${contacts}" var="contact">
                <tr>
                    <td>
                        <input type="checkbox" name="marked" form="delete" value="${contact.id}">
                    </td>
                    <td><a href="/jsp/edit.jsp">${ contact.getFullName()}</a></td>
                    <td>${ contact.birthday}</td>
                    <td>${ contact.address }</td>
                    <td>${ contact.company }</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>
