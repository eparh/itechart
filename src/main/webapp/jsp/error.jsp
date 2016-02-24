<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error page</title>
    <link rel="shortcut icon" href="/images/favicon.ico" type="image/x-icon">
    <link rel="stylesheet" href="/style/err.css">
</head>
<body>
    <div align="center">
        <h1 class="one">Ooops! Something gonna bad :(</h1>
        <h1 class="two">Try to back home </h1>
        <button type="submit" form="form">Home</button>
    </div>
    <form action="${pageContext.request.contextPath}/controller" id="form">
        <input type="hidden" name="command" value="show">
    </form>
</body>
</html>
