<%@ page import="WebUI.BattleShipWebUI" %>
<%@ page import="BattleShipsLogic.Definitions.PlayerName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%{ BattleShipWebUI game = (BattleShipWebUI)request.getSession().getAttribute("theGame");
PlayerName currentPlayer = (PlayerName)request.getSession().getAttribute("inGamePlayer");
String Moveres,PlayerMSG;
    Moveres = request.getAttribute("MoveResult") == null ? "" : request.getAttribute("MoveResult").toString();
PlayerMSG = request.getAttribute("PlayerMSG") == null ? "" : request.getAttribute("PlayerMSG").toString();
if(PlayerMSG.length() > 0){
    PlayerMSG = PlayerMSG;
}%>
<div class="PlayerName"><%=request.getSession().getAttribute("PlayerName")%></div>
<div class="PlayerScore">Score: <%=game.GetPlayerScore(currentPlayer)%></div>
<div class="PlayerMines">
    Mines: <%=game.GetPlayerMines(currentPlayer)%>
    <input type="hidden" name ="playerMines" value="<%=game.GetPlayerMines(currentPlayer)%>"/>
    <img src="../images/M.png" ondragstart="DragMine(event);" alt="Drag this to YOUR board to set a mine">
</div>
<div class="error-div"></div>
<div id="msgPlayer"><%=PlayerMSG%></div>
<div id="actionResults"><%=Moveres%></div>
<%}%>
