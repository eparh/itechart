<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create contact</title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script type="text/javascript" src="/js/contact.js"></script>
    <link rel="stylesheet" href="/style/contact.css">
</head>
<body  onload="selectAvatar()">

<!--Navigation bar -->

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/controller?command=show" ><span class="glyphicon glyphicon-user"></span>My Contacts</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="/jsp/search.jsp"><span class="glyphicon glyphicon-search"></span> Search contacts</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <a href="javascript:{}" onclick="document.getElementById('avatar').click()">
        <img id="image" src="/controller?command=avatar&idContact=${contact.id}">
    </a>
    <input id="avatar" type="file" name="avatar" form="form" class="file" accept="image/*" style="display: none">

    <c:set var="title"  value="Edit contact"/>
    <c:if test="${mode == add}">
        <c:set var="title"  value="Create contact"/>
        <c:set var="mode" value="add"/>
    </c:if>

    <h1> ${title}</h1>

    <!--Основная форма с input - полями -->

    <form id="form" action="/controller" class="form-horizontal" method="post" accept-charset="utf-8" enctype="multipart/form-data" role="form">
        <input type="hidden" name="title" value="${title}">

        <!--Для paging-а-->
        <input type="hidden" name="mode" value="${mode}">

        <input type="hidden" name="command"  value="save">

        <input type="hidden" name="idContact" value="${contact.id}">

        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-10">
                <input type="text" value="${contact.name}" class="form-control" id="name" name="name" placeholder="Enter name" required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="surname">Surname:</label>
            <div class="col-sm-10">
                <input type="text" value="${contact.surname}" class="form-control" id="surname" name="surname" placeholder="Enter surname" required>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="middname">Middle name:</label>
            <div class="col-sm-10">
                <input type="text" value="${contact.midName}" class="form-control" id="middname" name="middname" placeholder="Enter middle name">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="birthday">Birthday:</label>
            <div class="col-sm-10">
                <input type="date" value="${contact.birthday}" class="form-control" id="birthday" name="birthday" placeholder="Enter birthday">
            </div>
        </div>

        <div class="form-group">
            <label for="gender" class="control-label col-sm-2">Gender:</label>
            <div class="col-sm-5">
                <select name="gender" class="form-control" id="gender">
                    <option ${contact.gender == 'Male' ? 'selected' : ''}>Male</option>
                    <option ${contact.gender == 'Female' ? 'selected' : ''}>Female</option>
                </select>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="national">Nationality:</label>
            <div class="col-sm-10">
                <input type="text" value="${contact.nationality}" class="form-control" id="national" name="national" placeholder="Enter nationality">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="maritStatus">Marital status:</label>
            <div class="col-sm-10">
                <input type="text" value="${contact.maritStatus}" class="form-control" id="maritStatus" name="maritStatus" placeholder="Enter marital status">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="site">Website:</label>
            <div class="col-sm-10">
                <input type="url" value="${contact.site}" class="form-control" id="site" name="site" placeholder="Enter website">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email:</label>
            <div class="col-sm-10">
                <input type="email" value="${contact.email}" class="form-control" id="email" name="email" placeholder="Enter email">
            </div>
        </div>


        <div class="form-group">
            <label class="control-label col-sm-2" for="company">Company:</label>
            <div class="col-sm-10">
                <input type="text" value="${contact.company}" class="form-control" id="company" name="company" placeholder="Enter company">
            </div>
        </div>

        <input type="hidden" name="idAddress" value="${contact.address.idAddress}">

        <div class="form-group">
            <label class="control-label col-sm-2" for="country">Address:</label>
            <div class="col-sm-10">
                <input type="text" value="${contact.address.country}" class="form-control" id="country" name="country" placeholder="Enter country">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2"></label>
            <div class="col-sm-10">
                <input type="text" value="${contact.address.city}" class="form-control"  name="city" placeholder="Enter city">
            </div>
        </div>


        <div class="form-group">
            <label class="control-label col-sm-2"></label>
            <div class="col-sm-10">
                <input type="text" value="${contact.address.address}" class="form-control"  name="address" placeholder="Enter address">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2"></label>
            <div class="col-sm-10">
                <input type="text" value="${contact.address.index}" class="form-control"  name="index" placeholder="Enter index">
            </div>
        </div>
    </form>

    <!--Pop-up для телефонов -->

    <div id="phonePopUp" style="display:none">
        <div class='tt'>
            <div class="container">
                <form id="telephone" class="form-horizontal" accept-charset="utf-8" role="form">
                    <div style="margin: auto auto 2% auto">
                        <div class="close" onclick="phoneService.cancelPhone()">x</div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="countryCode">Country code:</label>
                        <div class="col-sm-2">
                            <input  type="number" min="0" class="form-control" max="1000" id="countryCode" name="countryCode" placeholder="Country code">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="operatorCode">Operator code:</label>
                        <div class="col-sm-2">
                            <input type="number" min="0" class="form-control" id="operatorCode" name="operatorCode" placeholder="Operator code" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="phone">Telephone:</label>
                        <div class="col-sm-5">
                            <input type="number" min="0" class="form-control" id="phone" name="phone" placeholder="Telephone" required>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="kind" class="control-label col-sm-2">Kind:</label>
                        <div class="col-sm-5">
                            <select name="kind" class="form-control" id="kind">
                                <option>Home</option>
                                <option>Mobile</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="control-label col-sm-2" for="comment">Comment:</label>
                        <div class="col-sm-5">
                            <textarea  class="form-control" rows="5" name="comment" id="comment" placeholder="Type your comment..."></textarea>
                        </div>
                    </div>
                </form>

                <button  onclick="phoneService.savePhone()" class="btn-default">Save</button>
                <button onclick="phoneService.cancelPhone()" class="btn-default">Cancel</button>
            </div>
        </div>
    </div>

    <!--Pop-up для attachments -->

    <div id="attachPopUp" style="display:none">
        <div id="attachTT">
            <div class="container form-horizontal">
                <div style="margin: auto auto 2% auto">
                    <div class="close" onclick="attachService.cancelAttach()">x</div>
                </div>

                <div id="b_file_name">
                    <div class="form-group" >
                        <label class="control-label col-sm-2" for="file_name">File:</label>
                        <div class="col-sm-5">
                            <input  type="text"  class="form-control" id="file_name" readonly>
                        </div>
                    </div>
                </div>

                <div id="b_attach">
                    <div class="form-group" >
                        <label class="control-label col-sm-2" for="attach">Choose file:</label>
                        <div class="col-sm-5">
                            <input  type="file"  form="form" class="form-control" id="attach" name="attach" >
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <label class="control-label col-sm-2" for="attachComment">Comment:</label>
                    <div class="col-sm-5">
                        <textarea  class="form-control" form="form" rows="5" name="attach_comment" id="attachComment" placeholder="Type your comment..."></textarea>
                    </div>
                </div>


                <button  onclick="attachService.saveAttach()" class="btn-default">Save</button>
                <button onclick="attachService.cancelAttach()" class="btn-default">Cancel</button>
            </div>
        </div>
    </div>


    <!--Таблица с телефонами и кнопками -->

    <div style="float: right;">
        <button onclick="phoneService.addPhone()" class="btn-default">Add phone</button>
        <button onclick="phoneService.deletePhone()" class="btn-default">Delete phone</button>
        <button onclick="phoneService.editPhone()" class="btn-default">Edit phone</button>
    </div>
    <div>
        <table class="table" >
            <thead>
            <tr>
                <th></th>
                <th>Full telephone</th>
                <th>Description</th>
                <th>Commment</th>
            </tr>
            </thead>
            <tbody id="phoneTable">
            <c:forEach items="${phones}" var="phone" varStatus="num">
                <tr>
                    <td>
                        <input type='checkbox'  name='phones'/>
                    </td>
                    <td><input type='text' form='form' value="${ phone.getFullPhone()}" readonly/></td>
                    <td><input type='text' form='form' name="kind${ num.count - 1 }" value="${ phone.kind}" readonly/></td>
                    <td><input type='text' form='form' name="comment${ num.count - 1 }" value="${ phone.comment}" readonly/></td>
                    <td><input type='hidden' form='form' name="countryCode${ num.count - 1}" value="${ phone.countryCode}" readonly/></td>
                    <td><input type='hidden' form='form' name="operatorCode${ num.count - 1}" value="${ phone.operatorCode}" readonly/></td>
                    <td><input type='hidden' form='form' name="phone${ num.count - 1}" value="${ phone.number}" readonly/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>


    <!--Таблица с attachments и кнопками -->

    <div style="float: right;">
        <button onclick="attachService.addAttach()" class="btn-default">Add attachment</button>
        <button onclick="attachService.deleteAttach()" class="btn-default">Delete attachment</button>
        <button onclick="attachService.editAttach()" class="btn-default">Edit attachment</button>
    </div>
    <div>
        <table class="table" >
            <thead>
            <tr>
                <th></th>
                <th>Name</th>
                <th>Date of load</th>
                <th>Commment</th>
            </tr>
            </thead>
            <tbody id="attachTable">
            <c:forEach items="${attaches}" var="attach" varStatus="num">
                <tr>
                    <td>
                        <input type='checkbox' form="form" name='attaches'/>
                    </td>
                    <td><input type='text'  value="${attach.name}" readonly/></td>
                    <td><input type='date'  value="${ attach.date}" readonly/></td>
                    <td><input type='text'  value="${ attach.comment}" readonly/></td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>

    <button form="form" type="submit" class="btn-default">Save</button>
</div>

</body>
</html>
