<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My contacts</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/style/main.css">
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
    <script type="text/javascript" src="/js/show.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/controller?command=show" ><span class="glyphicon glyphicon-user"></span>My Contacts</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="/jsp/contact.jsp"><span class="glyphicon glyphicon-plus"></span>Add contact</a></li>
                <li><a href="javascript:{}" onclick="getContact(); return false;"><span class="glyphicon glyphicon-pencil"></span>Edit contact</a></li>
                <li><a href="javascript:{}" onclick="deleteContact(); return false;"><span class="glyphicon glyphicon-trash"></span> Delete contacts</a></li>
                <li><a href="/jsp/search.jsp"><span class="glyphicon glyphicon-search"></span> Search contacts</a></li>
                <li><a href="/jsp/email.jsp"><span class="glyphicon glyphicon-send"></span> Send Email </a></li>
            </ul>
        </div>
    </div>
</nav>

<form action="/controller" id="checkbox" method="post">
    <input type="hidden" name="command">
    <input type="hidden" name="idContact">
    <input type="hidden" name="mode">
</form>

<form action="/controller" id="paging" method="post">
    <input type="hidden" name="command"  value="show">
    <input type="hidden" name="mode"  value="paging">
</form>

<div>
    <label for="countRecords" class="control-label col-sm-3">Show by records per page:</label>
    <div class="col-sm-1">
        <select onchange="chgRecordsPerPage()" name="countRecords"  form="paging" class="form-control" id="countRecords">
            <option value="10" ${settings.getCount() == '10' ? 'selected' : ''}>10</option>
            <option value="20" ${settings.getCount() == '20' ? 'selected' : ''}>20</option>
        </select>
    </div>
</div>

<table id="results" class="table table-striped .table-bordered .table-hover">
    <thead>
    <tr>
        <th></th>
        <th>Full name</th>
        <th>Birthday</th>
        <th>Address</th>
        <th>Company</th>
    </tr>
    </thead>
    <tbody id="table">
    <c:forEach items="${contacts}" var="contact" varStatus="num">
        <tr>
            <td>
                <input type="checkbox" name="marked" form="checkbox" value="${contact.id}">
            </td>
            <td><a href="javascript:{}" onclick="getContactByClick(${ num.count }); return false;">${ contact.getFullName()}</a></td>
            <td>${ contact.birthday}</td>
            <td>${ contact.address }</td>
            <td>${ contact.company }</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<span style="float: left; margin: 2% auto auto 5%; ">
    <p><b> Total pages: ${settings.pages}</b></p>
</span>
<div align="center">
    <ul class="pagination">
        <li onclick="prev()"><span class="glyphicon glyphicon-menu-left"></span></li>
        <li>
                <span class="col-sm-6">
                    <input type="number" form="paging" class="form-control" name="pageNumber" min="1" max="${settings.pages}" value="${settings.getPageNumber()}">
                </span>
        </li>
        <li onclick="next()"><span class="glyphicon glyphicon-menu-right"></span></li>
    </ul>
</div>
</body>
</html>
