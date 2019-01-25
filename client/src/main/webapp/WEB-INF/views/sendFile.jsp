<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Upload File</title>
    <link rel="stylesheet" type="text/css" href="guiStyle.css">
</head>
<body>

<div class="general">
    <h3 style="color: black;">File Transport Form</h3>

<s:form method="post" modelAttribute="product" enctype="multipart/form-data"
        action="sender/sendToServer">
    <label class="myLabel">Target Location IP </label>
        <s:input path="sendIp" />
    <tr>
        <td>Upload File: </td>
        <td><input type="file" name="file" />
    </tr>
    <tr>
        <td> </td>
        <td><input type="submit" value="fileUpload" />
        </td>
        <td> </td>
    </tr>
</s:form>

</div>

</body>
</html>