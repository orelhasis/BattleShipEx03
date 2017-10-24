<%@ page import="WebUI.BattleShipWebUI" %>
<%@ page import="BattleShipsLogic.Definitions.PlayerName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% BattleShipWebUI game = (BattleShipWebUI)request.getSession().getAttribute("theGame");
    PlayerName currentPlayer = (PlayerName)request.getSession().getAttribute("inGamePlayer");%>
<html>
<head>
    <title>BattleShips</title>
    <style>
        table img{
            max-height:30px;
            max-width:30px;
            cursor:crosshair;
        }
        #boards h3{
            margin-bottom:3px;
        }
        #boards h3:first-child{
            margin-top:0px;
        }
        #boards table{
            border-collapse: collapse;
            border:0;
        }
        #boards td{
            padding: 0;
        }
        .center-div {
            width:80%;
            margin: auto;
            display: flex;
            align-content: space-between;
        }
    </style>
</head>
<body>
    <h1>You Are in game named <%=game.getGameName()%></h1>
    <div class="center-div">
        <div id="myDetails">
            <jsp:include page="PlayerInfo.jsp"></jsp:include>
        </div>
        <div id="boards">
            <jsp:include page="boardTables.jsp"></jsp:include>
        </div>
        <div id="opponentDetails">
            <jsp:include page="OpponentInfo.jsp"></jsp:include>
        </div>
    </div>
</body>
</html>
