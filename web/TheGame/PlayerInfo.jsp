<%@ page import="WebUI.BattleShipWebUI" %>
<%@ page import="BattleShipsLogic.Definitions.PlayerName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%{ BattleShipWebUI game = (BattleShipWebUI)request.getSession().getAttribute("theGame");
PlayerName currentPlayer = (PlayerName)request.getSession().getAttribute("inGamePlayer");%>
<div class="PlayerName"><%=request.getSession().getAttribute("PlayerName")%></div>
<div class="PlayerScore">Score: <%=game.GetPlayerScore(currentPlayer)%></div>
<div class="PlayerMines">
    Mines: <%=game.GetPlayerMines(currentPlayer)%>
    <input type="hidden" name ="playerMines" value="<%=game.GetPlayerMines(currentPlayer)%>"/>
</div>
<%}%>
