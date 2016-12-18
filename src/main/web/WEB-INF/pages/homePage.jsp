<?xml version="1.0" encoding="UTF-8"?>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="setupPage.jsp" flush="true"/>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <title>Notebook</title>
</head>

<body>
<div class="container">
    <h1><p class="text-center">Notebook</p></h1>

    <p align="center"><a class="btn btn-sm btn-success" href="/Notebook/creating" role="button">Add Person</a></p>
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
                            <a class="btn btn-info btn-xs" href="/Notebook/email/sendingData/${person.id}" role="button">Send My Data</a>
                            <a class="btn btn-xs btn-danger" href="/Notebook/deletePerson/${person.id}" role="button">Delete Note</a>
                        </p>
                    </td>
                </tr>
            </thbody>
        </c:forEach>
    </table>
</div>

</body>
</html>
