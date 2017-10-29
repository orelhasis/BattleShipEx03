<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>The Battle Ships Game</title>
    <jsp:include page="/Global/Header.jsp"></jsp:include>
</head>
<body>
    <h1>The Battle Ships Game</h1>
    <h4>Please enter a unique user name:</h4>
    <form id="login_form" method="POST" action="login">
        <label>User Name:</label>
        <input type="text" name="user_name"> <br><br>
        <input type="submit" id="login_button">
    </form>
    <%if(request.getAttribute("ExtraText") != null){%>
    <div class="error-div"><%=request.getAttribute("ExtraText")%></div>
    <%}%>
</body>
</html>