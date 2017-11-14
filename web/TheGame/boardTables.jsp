<%@ page import="WebUI.BattleShipWebUI" %>
<%@ page import="BattleShipsLogic.Definitions.PlayerName" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%{ BattleShipWebUI game = (BattleShipWebUI)request.getSession().getAttribute("theGame");
    PlayerName currentPlayer = (PlayerName)request.getSession().getAttribute("inGamePlayer");%>
<div id="opponentBoard">
    <h3>Opponent Board</h3>
    <table>
        <%int boardSize = game.getBoardSize();
            char[][] board = game.GetOponnentBoard(currentPlayer);
            for(int i=1;i<=boardSize;i++){%>
        <tr>
            <%for(int j=1;j<=boardSize;j++){%>
            <td><img src="images/<%=board[i][j]%>.png" onclick="javascript:playerMove(<%=i%>,<%=j%>)"/></td>
            <%}%>
        </tr>
        <%}%>
    </table>
</div>
<div id="playerBoard">
    <h3>My Board</h3>
    <table>
        <%boardSize = game.getBoardSize();
            board = game.GetPlayerBoard(currentPlayer);
            for(int i=1;i<=boardSize;i++){%>
        <tr>
            <%for(int j=1;j<=boardSize;j++){%>
            <td><img src="images/<%=board[i][j]%>.png" ondragover="allowDrop(event);" ondrop="DroppedMine(event,<%=i%>,<%=j%>)"/></td>
            <%}%>
        </tr>
        <%}%>
    </table>
</div>
<%}%>