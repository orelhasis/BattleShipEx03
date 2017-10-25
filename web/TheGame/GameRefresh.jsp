<%@ page import="WebUI.BattleShipWebUI" %>
<%@ page import="BattleShipsLogic.Definitions.PlayerName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% { BattleShipWebUI game = (BattleShipWebUI)request.getSession().getAttribute("theGame");
    PlayerName currentPlayer = (PlayerName)request.getSession().getAttribute("inGamePlayer");
    String myTurnClass="",opponentTurnClass="";
    if(game.getCurrentPlayer() == currentPlayer){
        myTurnClass="currentTurn";
    }
    else{
        opponentTurnClass="currentTurn";
    }%>
<div id="myDetails">
    <div class="<%=myTurnClass%>">
        <jsp:include page="PlayerInfo.jsp"></jsp:include>
    </div>
</div>
<div id="boards">
    <jsp:include page="boardTables.jsp"></jsp:include>
</div>
<div id="opponentDetails">
    <div class="<%=opponentTurnClass%>">
        <jsp:include page="OpponentInfo.jsp"></jsp:include>
    </div>
</div>
<%}%>