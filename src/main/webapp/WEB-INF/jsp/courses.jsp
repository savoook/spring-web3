<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Courses</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
</head>
<body>
<h1>${courses}</h1>

<sec:authorize access="isAuthenticated()">
    <a href="/setCookie">Set cookie</a>
</sec:authorize>

<script type="text/javascript">
    $(document).ready(function () {
        $.ajax({
            type: "POST",
            url: "/courses/add",
            contentType: "application/json",
            data: JSON.stringify({name: "web+course", duration: 52}),
            dataType: 'json'
        })
    })
</script>
</body>
</html>
