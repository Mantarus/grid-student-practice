<%@ page language="java"
         contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
</head>
<body>
<h3> Messenger </h3>
    <table>
        <c:forEach var="messageDto" items="${messageDtos}">
            <tr>
                <td>
                    <c:out value="${messageDto.MSender}"/>:
                </td>
                <td>
                   <strong><c:out value="${messageDto.MBody}"/></strong>
                </td>
                <td>
                    <c:out value="${messageDto.MTimestamp}"/>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <form:form action="/chat" method="POST" modelAttribute="messageDto">
    <pre>
Sender       <form:input path="mSender"/>
Message body <form:input path="mBody"/>
<input type="submit" value="Submit" />
    </pre>
    </form:form>
</body>
</html>