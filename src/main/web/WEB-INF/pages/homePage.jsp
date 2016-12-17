<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib prefix="from" uri="http://www.springframework.org/tags/form" %>
<%@ page session="false" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Notebook</title>
    <jsp:include page="setupPage.jsp" flush="true"/>
</head>
<body>
<div class="container">
    <h1><p class="text-center">Notebook</p></h1>

    <div class="form-group searchText" style="width: 600px; margin: auto">
        <h4>Person search:</h4>
        <c:url var="queryAction" value='/queryNote'/>
        <form:form action="${queryAction}" commandName="query">
            <input name="searchControl" class="form-control" placeholder="Type search text">
            <input type="submit" value="<spring:message text="Search"/>"/>
        </form:form>
    </div>

    <p align="right"><a class="btn btn-sm btn-success" href="/showCreatePersonPage" role="button">Add Person</a></p>
    <table class="table">
        <thead>
        <tr>
            <th>Name</th>
            <th>Email</th>
        </tr>
        </thead>
        <c:forEach var="person" items="${persons}">
            <thbody>
                <tr>
                    <td>${person.name}</td>
                    <td>${person.email}</td>
                    <td>
                        <p>
                            <a class="btn btn-info btn-xs" href="/email/sendingData/${person.id}" role="button">Send My Data</a>
                            <a class="btn btn-xs btn-danger" href="/deletePerson/${person.id}" role="button">Delete Note</a>
                        </p>
                    </td>
                </tr>
            </thbody>
        </c:forEach>
    </table>
</div>

</body>
</html>
