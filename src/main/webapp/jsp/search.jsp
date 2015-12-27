<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Search</title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/controller?command=show" ><span class="glyphicon glyphicon-user"></span>My Contacts</a>
        </div>
    </div>
</nav>
<div class="container">
<h1 style="margin: auto 30% 2% auto;">Search contacts</h1>
<form id="form" action="/controller" class="form-horizontal" method="post" accept-charset="utf-8" role="form">
    <input type="hidden" name="command" value="show">
    <input type="hidden" name="mode" value="search">

    <div class="form-group">
        <label class="control-label col-sm-2" for="name">Name:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="name" name="s_name" placeholder="Enter name">
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="surname">Surname:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="surname" name="s_surname" placeholder="Enter surname">
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="middname">Middle name:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="middname" name="s_middname" placeholder="Enter middle name">
        </div>
    </div>

    <div class="form-group">
        <label for="gender" class="control-label col-sm-2">Gender:</label>
        <div class="col-sm-5">
            <select name="s_gender" class="form-control" id="gender">
                <option>Male</option>
                <option>Female</option>
            </select>
        </div>
    </div>


    <div class="form-group">
        <label class="control-label col-sm-2">Birthday's range:</label>
        <div class="col-sm-4">
            <input type="date" class="form-control"  name="s_birthdayFrom" placeholder="Enter birthday">
            <input type="date"  class="form-control" name="s_birthdayTO" placeholder="Enter birthday">
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="national">Nationality:</label>
        <div class="col-sm-10">
            <input type="text" class="form-control" id="national" name="s_national" placeholder="Enter nationality">
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="maritStatus">Marital status:</label>
        <div class="col-sm-10">
            <input type="text"  class="form-control" id="maritStatus" name="s_maritStatus" placeholder="Enter marital status">
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2" for="country">Address:</label>
        <div class="col-sm-10">
            <input type="text"  class="form-control" id="country" name="s_country" placeholder="Enter country">
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2"></label>
        <div class="col-sm-10">
            <input type="text"  class="form-control"  name="s_city" placeholder="Enter city">
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2"></label>
        <div class="col-sm-10">
            <input type="text"  class="form-control"  name="s_address" placeholder="Enter address">
        </div>
    </div>

    <div class="form-group">
        <label class="control-label col-sm-2"></label>
        <div class="col-sm-10">
            <input type="text"  class="form-control"  name="s_index" placeholder="Enter index">
        </div>
    </div>
</form>
<button type="submit" form="form" class="btn-default">Search</button>
</div>
</body>
</html>
