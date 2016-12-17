<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error</title>
</head>
<body>

    <jsp:include page="topNavigationButtons.jsp" flush="true"/>

    <h2>Sorry, the email was not sent because of the following error:</h2>
    <h3>${exception.message}</h3>

</body>
</html>
