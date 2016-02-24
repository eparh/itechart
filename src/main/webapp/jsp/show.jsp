<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>My contacts</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="stylesheet" href="/style/show.css">
    <link rel="shortcut icon" href="${pageContext.request.contextPath}/images/favicon.ico" type="image/x-icon">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/show.js"></script>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="${pageContext.request.contextPath}/controller?command=show">
                <span class="glyphicon glyphicon-user"></span>My Contacts
            </a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li>
                    <a href="${pageContext.request.contextPath}${pageContext.request.contextPath}/jsp/contact.jsp">
                        <span class="glyphicon glyphicon-plus"></span> Add contact
                    </a>
                </li>
                <li>
                    <a href="javascript:{}" onclick="getContact(); return false;">
                        <span class="glyphicon glyphicon-pencil"></span> Edit contact
                    </a>
                </li>
                <li>
                    <a href="javascript:{}" onclick="deleteContact(); return false;">
                        <span class="glyphicon glyphicon-trash"></span> Delete contacts
                    </a>
                </li>
                <li>
                    <a href="${pageContext.request.contextPath}/jsp/search.jsp"><span class="glyphicon glyphicon-search">
                </span> Search contacts
                    </a>
                </li>
                <li><a href="javascript:{}" onclick="email(); return false;">
                    <span class="glyphicon glyphicon-send"></span> Send Email
                </a>
                </li>
            </ul>
        </div>
    </div>
</nav>
<form action="${pageContext.request.contextPath}/controller" id="checkbox" method="post">
    <input type="hidden" name="command">
    <input type="hidden" name="idContact">
    <input type="hidden" name="mode">
</form>

<form action="${pageContext.request.contextPath}/controller" id="paging" method="post">
    <input type="hidden" name="command"  value="show">
    <input type="hidden" name="mode"  value="paging">
</form>

<div>
    <label for="countRecords" class="control-label col-sm-3">Show by records per page:</label>
    <div class="col-sm-1">
        <select onchange="chgRecordsPerPage()" name="countRecords"  form="paging" class="form-control" id="countRecords">
            <option  ${settings.getCount() == '10' ? 'selected' : ''}>10</option>
            <option  ${settings.getCount() == '20' ? 'selected' : ''}>20</option>
        </select>
    </div>
</div>

<c:forEach items="${templates}" var="template" varStatus="num">
    <input type="hidden" id="${template.key}" value="${template.value}">
</c:forEach>

<div id="emailPopUp" style="display:none">
    <div class="tt">
        <div class="container">
            <form action="${pageContext.request.contextPath}/controller" id="email" class="form-horizontal"
                  accept-charset="utf-8" role="form">
                <input type="hidden" name="command" value="email">
                <div style="margin: auto auto 3% auto">
                    <div class="close" onclick="openbox('emailPopUp')">x</div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-1" for="address">To:</label>
                    <div class="col-sm-7">
                        <textarea  class="form-control" rows="1" name="address" id="address" readonly></textarea>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-1" for="subject">Subject:</label>
                    <div class="col-sm-7">
                        <input  type="text"  id="subject"  class="form-control" name="subject" required>
                    </div>
                </div>

                <div class="form-group">
                    <label for="template" class="control-label col-sm-1">Template:</label>
                    <div class="col-sm-7">
                        <select name="template" class="form-control" id="template" onchange="setTemplate()">
                            <option>Without template</option>
                            <option>Message to friend</option>
                            <option>Message to boss</option>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-1" for="text">Text:</label>
                    <div class="col-sm-7">
                        <textarea  class="form-control" rows="8" name="text" id="text" placeholder="Type message..."
                                   required ></textarea>
                    </div>
                </div>
            </form>

            <button  type="submit" form="email" class="btn-default">Send</button>
            <button onclick="openbox('emailPopUp')" class="btn-default">Cancel</button>
        </div>
    </div>
</div>


<input type="hidden" id="afterEmailing"  value="${message}">
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
            <td>
                <a href="javascript:{}" onclick="getContactByClick(${ num.count }); return false;">${ contact.getFullName()}
                </a>
            </td>
            <td>${ contact.birthday}</td>
            <td>${ contact.address }</td>
            <td>${ contact.company }</td>
            <td><input type="hidden" name="email"  value="${contact.email}"> </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<footer>
<span style="float: left; margin: 2% auto auto 5%; ">
    <p><b> Total pages: ${settings.pages}</b></p>
</span>
    <div align="center">
        <ul class="pagination">
            <li onclick="prev()"><span class="glyphicon glyphicon-menu-left"></span></li>
            <li>
                <span class="col-sm-6">
                    <input type="number" form="paging" class="form-control" name="pageNumber" min="1"
                           max="${settings.pages}" value="${settings.getPageNumber()}">
                </span>
            </li>
            <li onclick="next()"><span class="glyphicon glyphicon-menu-right"></span></li>
        </ul>
    </div>
</footer>
</body>
<script>
    (function() {
        informUser();

    })();
</script>
</html>
