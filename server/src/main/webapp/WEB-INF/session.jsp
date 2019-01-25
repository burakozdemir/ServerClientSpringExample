<%@ page import = "java.io.*,java.util.*,java.sql.*"%>
<%@ page import = "javax.servlet.http.*,javax.servlet.*" %>
<%@ page import = "java.util.Date,java.text.*" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c"%>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/sql" prefix = "sql"%>
<%@ taglib prefix = "x" uri = "http://java.sun.com/jsp/jstl/xml" %>

<html>
<head>
    <title>JSTL sql:dataParam Tag</title>
</head>

<body>
<sql:setDataSource var = "snapshot" driver = "org.postgresql.Driver"
                   url = "jdbc:postgresql://localhost:5432/postgres"
                   user = "postgres"  password = "1234"/>

<%
    Date a = new Date("1999/01/01");
    int studentId = 3;
%>

<sql:transaction dataSource = "${snapshot}">
    <sql:update var = "count">
        UPDATE tb_csv SET text = 'Ali' WHERE id = 1
    </sql:update>

    <sql:update var = "count">
        UPDATE tb_csv SET text = 'Shah' WHERE id = 2
    </sql:update>

</sql:transaction>

<sql:query dataSource = "${snapshot}" var = "result">
    SELECT * from tb_csv;
</sql:query>

<table border = "1" width="100%">
    <tr>
        <th>id</th>
        <th>text</th>
        <th>dob</th>
    </tr>

    <x:parse xml = "${xmltext}" var = "output"/>
    <x:if select = "$output//book">
    Document has at least one <book> element.
    </x:if>
    <br />

    <c:forEach var = "row" items = "${result.rows}">
        <tr>
            <td> <c:out value = "${row.id}"/></td>
            <td> <c:out value = "${row.text}"/></td>
            <td> <c:out value = "${row.dob}"/></td>

        </tr>
    </c:forEach>
</table>



<x:parse xml = "${xmltext}" var = "output"/>
<b>The title of the first book is</b>:
<x:out select = "$output/books/book[1]/name" />
<br>

<b>The price of the second book</b>:
<x:out select = "$output/books/book[2]/price" />


</body>
</html>