<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<?xml version="1.0" encoding="UTF-8"?>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:include page="setupPage.jsp" flush="true"/>

<html>
<head>
    <title>Create Person</title>
</head>
<body>

<div class="container">
    <h1>
        <p class="text-center">Create new Note</p>
    </h1>
    <form:form method="POST" action="person" commandName="person">
        <form:input class="form-control" id="id" path="id" value="${person.id}" type="hidden"/>
        <label>Name</label><br/>
        <div class="form-group">
            <form:input class="form-control" id="name" path="name" placeholder="Name"
                        value="${person.name}" required="required"/>
            <form:errors path="name" cssStyle="color: #ff0000;"/>
        </div>

        <label>Email</label><br/>
        <div class="form-group">
            <form:input class="form-control" id="email" path="email" placeholder="Email"
                        value="${person.email}" required="required"/>
            <form:errors path="email" cssStyle="color: #ff0000;"/>
        </div>

        <input class="btn btn-success btn-xs" type="submit" value="save">
        <a class="btn btn-default btn-xs" href="/Notebook/home" role="button">cancel</a>
    </form:form>

</div>
</body>
</html>

