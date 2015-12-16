<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <title>Test</title>
</head>
<body>
<div>
    <table class="table">
        <thead>
        <tr>
            <th></th>
            <th>Full telephone</th>
            <th>Description</th>
            <th>Commment</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach items="${phones}" var="phone">
            <tr>
                <td>

                </td>
                <td>${phone.getFullPhone()}</td>
                <td>${ phone.kind }</td>
                <td>${ phone.comment }</td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>
</body>
</html>
