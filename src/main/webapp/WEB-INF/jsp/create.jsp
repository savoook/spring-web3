<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Create</title>
</head>
<body>
<form action="/course/save" method="post">
    <input name="name">
    <input name="duration">
    <button type="submit">Создать</button>
</form>
<form action="/courseUpload" method="post" enctype="multipart/form-data">
    <input type="text" name="name">
    <input type="file" name="file">
    <input type="submit">
</form>
</body>
</html>
