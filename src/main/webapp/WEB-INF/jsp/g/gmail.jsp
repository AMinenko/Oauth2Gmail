<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>
<html>
<head>
    <title>mail with id ${mail.id}</title>
</head>
<body>
<table>
<tr>
    <td>Subject: </td><td> ${mail.snippet}</td>
</tr>
</table>
</body>
</html>
