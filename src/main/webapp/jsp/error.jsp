<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/style/error.css">
</head>
<body>
    <div align="center">
        <h2>Ooops! Could not Find it :(</h2>
        <h1>404</h1>
        <button type="submit" form="form">Home</button>
    </div>
    <form action="/controller" id="form">
        <input type="hidden" name="command" value="show">
    </form>
</body>
</html>
