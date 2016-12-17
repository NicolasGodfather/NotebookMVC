<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Save Person</title>
    <jsp:include page="setupPage.jsp" flush="true"/>
</head>
<body>

<div class="container">
    <h1><p class="text-center">Create new Person</p></h1>

    <form:form method="POST" action="/createPerson" commandName="person">

        <form:input class="form-control" id="id" path="id" value="${person.id}" type="hidden"/>

        <label>Name</label><br/>
        <div class="form-group">
            <form:input class="form-control" id="name" path="name" placeholder="Name"
                        value="${person.name}"/>
            <form:errors path="name_error" cssStyle="color: #ff0000;"/>
        </div>

        <label>Email</label><br/>
        <div class="form-group">
            <form:input class="form-control" id="email" path="email" placeholder="Email"
                        value="${person.email}"/>
            <form:errors path="email_error" cssStyle="color: #ff0000;"/>
        </div>

        <input class="btn btn-success btn-xs" type="submit" value="save">
        <a class="btn btn-default btn-xs" href="/" role="button">cancel</a>
    </form:form>
</div>
</body>
</html>

