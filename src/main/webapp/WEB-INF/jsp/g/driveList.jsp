<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>
<html>
<head>
    <title></title>
</head>
<body>
  File List
  <ol>
    <c:forEach var="file" items="${files}">
        <li><c:url value="/get" context="/mail-app/g/drive" var="fileUrl">
                <c:param name="fileId" value ="${file.id}"/>
            </c:url>
           <a href=<c:out value="${fileUrl}"/>> ${file.title}</a>
            <br/>
        </li>
    </c:forEach>
  </ol>




</body>
</html>
