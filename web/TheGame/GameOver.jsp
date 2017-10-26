<%@ page import="WebUI.BattleShipWebUI" %>
<%@ page import="BattleShipsLogic.Definitions.PlayerName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% BattleShipWebUI game = (BattleShipWebUI)request.getSession().getAttribute("theGame");
    PlayerName currentPlayer = (PlayerName)request.getSession().getAttribute("inGamePlayer");
    %>
<html>
<head>
    <title>BattleShips Game</title>
    <jsp:include page="../Global/Header.jsp"></jsp:include>
</head>
<body>
    <H1>Game Over!</H1>
    <div class="center-div">
        <div class="results-string"><%=game.getGameOverMessage()%></div>
    </div>
    <div>
        <h1>Some Statistics:</h1>
        <div id="Statistics">
            <%=game.getStatistics(game.getPlayerByName(currentPlayer))%>
        </div>
    </div>
    <a href="BattleShips">Click here to go back to the lobby</a>
</body>
</html>
