<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create contact</title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <a class="navbar-brand" href="/controller?command=show" >My Contacts</a>
        </div>
        <div>
            <ul class="nav navbar-nav">
                <li><a href="/jsp/search.jsp">Search contacts</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <h2>Edit contact</h2>
    <form action="/controller" class="form-horizontal" method="post" role="form">
        <input type="hidden" name="command" value="create">

        <div class="form-group">
            <label class="control-label col-sm-2" for="name">Name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="name" name="name" placeholder="Enter name">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="surname">Surname:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="surname" name="surname" placeholder="Enter surname">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="middname">Middle name:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="middname" name="middname" placeholder="Enter middle name">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="birthday">Birthday:</label>
            <div class="col-sm-10">
                <input type="date" class="form-control" id="birthday" name="birthday" placeholder="Enter birthday">
            </div>
        </div>


        <div class="form-group">
            <label class="control-label col-sm-2" for="gender">Gender:</label>
            <div class="col-sm-10">
                <input list="gender" class="list-view" name="gender" placeholder="Gender">
                <datalist id="gender">
                    <option value="Male">
                    <option value="Female">
                </datalist><br/>
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="national">Nationality:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="national" name="national" placeholder="Enter nationality">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="maritStatus">Marital status:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="maritStatus" name="maritStatus" placeholder="Enter marital status">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="site">Website:</label>
            <div class="col-sm-10">
                <input type="url" class="form-control" id="site" name="site" placeholder="Enter website">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="email">Email:</label>
            <div class="col-sm-10">
                <input type="email" class="form-control" id="email" name="email" placeholder="Enter email">
            </div>
        </div>


        <div class="form-group">
            <label class="control-label col-sm-2" for="company">Company:</label>
            <div class="col-sm-10">
                <input type="text" class="form-control" id="company" name="company" placeholder="Enter company">
            </div>
        </div>

        <div class="form-group">
            <label class="control-label col-sm-2" for="country">Address:</label>

            <div class="col-sm-10">
                <input type="text" class="form-control" id="country" name="country" placeholder="Enter country">
            </div>

        </div>

        <div class="form-group">
            <label class="control-label col-sm-2"></label>
            <div class="col-sm-10">
                <input type="text" class="form-control"  name="city" placeholder="Enter city">
            </div>
        </div>


        <div class="form-group">
            <label class="control-label col-sm-2"></label>
            <div class="col-sm-10">
                <input type="text" class="form-control"  name="address" placeholder="Enter address">
            </div>
        </div>


        <div class="form-group">
            <label class="control-label col-sm-2"></label>
            <div class="col-sm-10">
                <input type="text" class="form-control"  name="index" placeholder="Enter index">
            </div>
        </div>

    </form>
</div>

<div class="btn-group" >
    <form method="post" action="/controller">
        <input type="submit" value="Add " >
    </form>

    <form method="post" action="/controller">
        <input type="submit" value="Edit ">
    </form>
</div>



</body>
</html>
