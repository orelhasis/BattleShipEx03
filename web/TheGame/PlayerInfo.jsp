<%@ page import="WebUI.BattleShipWebUI" %>
<%@ page import="BattleShipsLogic.Definitions.PlayerName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%{ BattleShipWebUI game = (BattleShipWebUI)request.getSession().getAttribute("theGame");
PlayerName currentPlayer = (PlayerName)request.getSession().getAttribute("inGamePlayer");
String Moveres,PlayerMSG;
Boolean myTurn = game.getCurrentPlayer() == currentPlayer && !game.isAvailable();
Moveres = request.getAttribute("MoveResult") == null ? "" : request.getAttribute("MoveResult").toString();
PlayerMSG = request.getAttribute("PlayerMSG") == null ? "" : request.getAttribute("PlayerMSG").toString();
if(PlayerMSG.length() > 0){
    PlayerMSG = PlayerMSG;
}%>
<div class="PlayerName"><%=request.getSession().getAttribute("PlayerName")%></div>
<div class="PlayerScore">Score: <%=game.GetPlayerScore(currentPlayer)%></div>
<%if(game.getGameType().equalsIgnoreCase("advance")){%>
    <div class="PlayerMines">
        Mines: <%=game.GetPlayerMines(currentPlayer)%>
        <input type="hidden" name ="playerMines" value="<%=game.GetPlayerMines(currentPlayer)%>"/>
        <img src="images/M.png" ondragstart="DragMine(event);" alt="Drag this to YOUR board to set a mine">
    </div>
<%}%>
<div class="error-div"></div>
<br>
<%if(!game.isAvailable()){%>
    <div style="display: inline-block;margin-right: 10px;"><input type="button" value="Show Statistics" onclick="showStatistics();"></div>
    <div style="display: inline-block"><input type="button" value="Surrender" onclick="startSurrender('Surrender');"></div>
<%}else{%>
<div><input type="button" value="Exit Game" onclick="startSurrender('Exit');"></div>
<%}%>
<div id="msgPlayer"><%=PlayerMSG%></div>
<div id="actionResults" ><%=Moveres%></div>
<%if(myTurn){%>
    <br>
    <img src="images/turnarrow.png">
<%}%>
<%}%>
