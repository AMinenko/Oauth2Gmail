<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec"  uri="http://www.springframework.org/security/tags"%>
<%@page session="true"%>
<html>
<head>
    <script>
        function logout(){
            window.location.href='http://localhost:8080/mail-app/logout';
        }
    </script>

    <title></title>
</head>
<body>
    Hello mails!!!
    <c:forEach var="mail" items="${mails}">
      <ol>
        <li><c:url value="/gmail" context="/mail-app/g" var="mailUrl">
                <c:param name="mailId" value ="${mail.id}"/>
            </c:url>
           <a href=<c:out value="${mailUrl}"/>> Click to see message detail with id: ${mail.id}</a>
            <br/>
        </li>
      </ol>
    </c:forEach>




</body>
</html>
