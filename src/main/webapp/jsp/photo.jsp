<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Choose photo</title>
    <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">

</head>
<body>
    <div class="container">
        <br><br><br>
        <form method="post" class="form-horizontal upload-file" data-max-size="2048">
            <div class="form-group">
                <div class="col-sm-10">
                    <input type="file" name="file" class="upload-file" data-max-size="2048">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-10" style="float: right;">
                    <input type="submit" value="Save">
                    <input type="button" value="Cancel">
                </div>
            </div>

        </form>
    </div>
</body>
</html>
