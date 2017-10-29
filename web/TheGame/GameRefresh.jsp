<%@ page import="WebUI.BattleShipWebUI" %>
<%@ page import="BattleShipsLogic.Definitions.PlayerName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% { BattleShipWebUI game = (BattleShipWebUI)request.getSession().getAttribute("theGame");
    PlayerName currentPlayer = (PlayerName)request.getSession().getAttribute("inGamePlayer");%>
<div id="myDetails">
    <div>
        <jsp:include page="PlayerInfo.jsp"></jsp:include>
    </div>
</div>
<div id="boards">
    <%if (!game.isAvailable()){%>
        <jsp:include page="boardTables.jsp"></jsp:include>
    <%}%>
</div>
<div id="opponentDetails">
    <div>
        <jsp:include page="OpponentInfo.jsp"></jsp:include>
    </div>
</div>
<div id="Statistics">
    <%=game.getStatistics(game.getPlayerByName(currentPlayer))%>
</div>
<%}%>