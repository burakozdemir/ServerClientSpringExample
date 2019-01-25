<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Product Information</title>
</head>
<body>
JSP
<h3>Product Information</h3>
<table cellpadding="2" cellspacing="2" border="1">
    <tr>
        <td>Gonderen Ip</td>
        <td>${data.baseIp}</td>
    </tr>
    <tr>
        <td>Data Mesaj</td>
        <td>${data.message}</td>
    </tr>
    <tr>
        <td>File</td>
        <td>
            <img src="/reciever/image/${photo}"/>
        </td>
    </tr>
</table>

</body>
</html>