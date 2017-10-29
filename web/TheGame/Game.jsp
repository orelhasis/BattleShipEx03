<%@ page import="WebUI.BattleShipWebUI" %>
<%@ page import="BattleShipsLogic.Definitions.PlayerName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% BattleShipWebUI game = (BattleShipWebUI)request.getSession().getAttribute("theGame");
    PlayerName currentPlayer = (PlayerName)request.getSession().getAttribute("inGamePlayer");
%>
<html>
<head>
    <title>BattleShips</title>
    <jsp:include page="/Global/Header.jsp"></jsp:include>
    <script type="text/javascript" src="../js/Game.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/Game.css">
</head>
<body>
    <h1>You Are in game named <%=game.getGameName()%> (<%=game.getGameType()%>)</h1>
    <div class="center-div">
        <div id="myDetails" class="player-details">
            <div>
                <jsp:include page="PlayerInfo.jsp"></jsp:include>
            </div>
        </div>
        <div id="boards">
        </div>
        <div id="opponentDetails" class="player-details">
            <div>
                <jsp:include page="OpponentInfo.jsp"></jsp:include>
            </div>
        </div>
    </div>
    <div id="Statistics" style="display: none;">
        <%=game.getStatistics(game.getPlayerByName(currentPlayer))%>
    </div>
    <form name="actionForm">
        <input type="hidden" name="Action">
        <input type="hidden" name="x">
        <input type="hidden" name="y">
    </form>
    <div id="surrenderDiv" style="display: none">
        Are you sure you want to surrender?
    </div>
</body>
</html>
