<%@ page import="WebUI.BattleShipWebUI" %>
<%@ page import="BattleShipsLogic.Definitions.PlayerName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% BattleShipWebUI game = (BattleShipWebUI)request.getSession().getAttribute("theGame");
    PlayerName currentPlayer = (PlayerName)request.getSession().getAttribute("inGamePlayer");
    String myTurnClass="",opponentTurnClass="";
    if(game.isAvailable()){
            if(game.getCurrentPlayer() == currentPlayer){
                myTurnClass="currentTurn";
            }
            else{
                opponentTurnClass="currentTurn";
            }
    }%>
<html>
<head>
    <title>BattleShips</title>
    <script type="text/javascript" src="../js/jquery-3.2.1.min.js"></script>
    <script type="text/javascript" src="../js/jquery.form.min.js"></script>
    <script type="text/javascript" src="../js/Game.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/Game.css">
</head>
<body>
    <h1>You Are in game named <%=game.getGameName()%></h1>
    <div class="center-div">
        <div id="myDetails" class="player-details">
            <div class="<%=myTurnClass%>">
                <jsp:include page="PlayerInfo.jsp"></jsp:include>
            </div>
        </div>
        <div id="boards">
            <jsp:include page="boardTables.jsp"></jsp:include>
        </div>
        <div id="opponentDetails" class="player-details">
            <div class="<%=opponentTurnClass%>">
                <jsp:include page="OpponentInfo.jsp"></jsp:include>
            </div>
        </div>
    </div>
    <form name="actionForm">
        <input type="hidden" name="Action">
        <input type="hidden" name="x">
        <input type="hidden" name="y">
    </form>
</body>
</html>
