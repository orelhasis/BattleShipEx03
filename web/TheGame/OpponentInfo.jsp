<%@ page import="WebUI.BattleShipWebUI" %>
<%@ page import="BattleShipsLogic.Definitions.PlayerName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%{ BattleShipWebUI game = (BattleShipWebUI)request.getSession().getAttribute("theGame");
PlayerName currentPlayer = (PlayerName)request.getSession().getAttribute("inGamePlayer");
    if(game.GetOpponentName(currentPlayer) == null){%>
        <h3>Waiting For Another Player</h3>
    <%}else{%>
        <div class="PlayerName"><%=game.GetOpponentName(currentPlayer)%></div>
        <div class="PlayerScore">Score: <%=game.GetOpponentScore(currentPlayer)%></div>
        <div class="PlayerMines">Mines: <%=game.GetOpponentMines(currentPlayer)%></div>
    <%}
}%>
