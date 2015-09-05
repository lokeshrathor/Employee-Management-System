<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Update Employee Details</title>
</head>
<body>
<h2>Employee Management Screen</h2>
<h6><a href="<c:url value='/j_spring_security_logout'/>">Click here to logout</a></h6>
<hr>
<h3>Update Employee Details</h3>
<form:form  method="post" action="${pageContext.request.contextPath}/update/${employee.id}" modelAttribute="employee">
<table>
	<tr>
        <td><form:label path="id"><spring:message code="label.id"/></form:label></td>
        <td><form:input path="id" readonly="true"/></td>
    </tr>
    <tr>
        <td><form:label path="firstname"><spring:message code="label.firstname"/></form:label></td>
        <td><form:input path="firstname" /></td>
    </tr>
    <tr>
        <td><form:label path="lastname"><spring:message code="label.lastname"/></form:label></td>
        <td><form:input path="lastname" /></td>
    </tr>
    <tr>
        <td><form:label path="email"><spring:message code="label.email"/></form:label></td>
        <td><form:input path="email" /></td>
    </tr>
    <tr>
        <td><form:label path="telephone"><spring:message code="label.telephone"/></form:label></td>
        <td><form:input path="telephone" /></td>
    </tr>
    <tr>
        <td colspan="2">
            <input type="submit" value="<spring:message code="label.update"/>"/>
        </td>
    </tr>
</table>
</form:form>
</body>
</html>