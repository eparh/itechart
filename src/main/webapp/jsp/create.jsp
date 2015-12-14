<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create contact</title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <script type="text/javascript" src="/js/script.js"></script>
    <link rel="stylesheet" href="/style/create.css">
</head>
<body>

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

        <h2 align="center">Create contact</h2>
        <a href="/jsp/photo.jsp">
            <img src="/images/noavatar.png" style="float:left; width: 128px; height: 128px ;">

        </a>

        <br/><br/><br/><br/><br/><br/><br/><br/>


        <form id="form" action="/controller" class="form-horizontal" method="post" accept-charset="utf-8" role="form">
            <input type="hidden" name="command">
            <input type="hidden" name="mode" value="add">

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
                    <input type="text" value="${contact.middname}" class="form-control" id="middname" name="middname" placeholder="Enter middle name">
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
                    <select name="gender" value="${contact.gender}" class="form-control" id="gender">
                        <option>Male</option>
                        <option>Female</option>
                    </select>
                </div>
            </div>

            <div class="form-group">
                <label class="control-label col-sm-2" for="national">Nationality:</label>
                <div class="col-sm-10">
                    <input type="text" value="${contact.national}" class="form-control" id="national" name="national" placeholder="Enter nationality">
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

            <div class="form-group">
                <label class="control-label col-sm-2" for="country">Address:</label>

                <div class="col-sm-10">
                    <input type="text" value="${contact.adds.country}" class="form-control" id="country" name="country" placeholder="Enter country">
                </div>

            </div>

            <div class="form-group">
                <label class="control-label col-sm-2"></label>
                <div class="col-sm-10">
                    <input type="text" value="${contact.adds.ciyy}" class="form-control"  name="city" placeholder="Enter city">
                </div>
            </div>


            <div class="form-group">
                <label class="control-label col-sm-2"></label>
                <div class="col-sm-10">
                    <input type="text" value="${contact.adds.address}" class="form-control"  name="address" placeholder="Enter address">
                </div>
            </div>


            <div class="form-group">
                <label class="control-label col-sm-2"></label>
                <div class="col-sm-10">
                    <input type="text" value="${contact.adds.index}" class="form-control"  name="index" placeholder="Enter index">
                </div>
            </div>


        </form>


        <div id="Wrapp" style="display:none">
            <div id='tt'>

                <div class="container">
                    <div class="form-horizontal">



                        <div style="margin: auto auto 2% auto">
                             <div class="close" onclick="openbox('Wrapp')">x</div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2" for="countryCode">Country code:</label>
                            <div class="col-sm-2">
                                <input form="form"  type="text" class="form-control" id="countryCode" name="countryCode" placeholder="Country code">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2" for="operatorCode">Operator code:</label>
                            <div class="col-sm-2">
                                <input form="form" type="text" class="form-control" id="operatorCode" name="operatorCode" placeholder="Operator code">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2" for="phone">Telephone:</label>
                            <div class="col-sm-5">
                                <input form="form" type="tel" class="form-control" id="phone" name="phone" placeholder="Telephone">
                            </div>
                        </div>


                        <div class="form-group">
                            <label for="kind" class="control-label col-sm-2">Kind:</label>
                            <div class="col-sm-5">
                                <select form="form" name="kind" class="form-control" id="kind">
                                    <option>Home</option>
                                    <option>Mobile</option>
                                </select>
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2" for="comment">Comment:</label>
                            <div class="col-sm-5">
                                <textarea form="form" class="form-control" rows="5" name="comment" id="comment" placeholder="Type your comment..."></textarea>
                            </div>
                        </div>



                    </div>

                    <button form="form" onclick="addPhone()" class="btn-default">Save</button>
                    <button onclick="openbox('Wrapp')" class="btn-default">Cancel</button>

                </div>


            </div>
        </div>
        <div>
            <button onclick="openbox('Wrapp')" class="btn-default">Add phone</button>
        </div>


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
                            <input type="checkbox" name="phones"  value="${phone.id}">
                        </td>
                        <td>${phone.getFullPhone()}</td>
                        <td>${ phone.kind }</td>
                        <td>${ phone.comment }</td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        </div>

        <button form="form" onclick="saveContact()" class="btn-default">Save</button>
    </div>









</body>
</html>
