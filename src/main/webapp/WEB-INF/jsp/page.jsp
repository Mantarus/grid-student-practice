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
        <c:forEach var="message" items="${messages}">
            <tr>
                <td>
                    <c:out value="${message.MSender}"/>:
                </td>
                <td>
                   <strong><c:out value="${message.MBody}"/></strong>
                </td>
                <td>
                    <c:out value="${message.MTimestamp}"/>
                </td>
            </tr>
        </c:forEach>
    </table>

    <br>
    <form:form action="/chat" method="POST" modelAttribute="message">
    <pre>
Sender       <form:input path="mSender"/>
Message body <form:input path="mBody"/>
<input type="submit" value="Submit" />
    </pre>
    </form:form>
</body>
</html>