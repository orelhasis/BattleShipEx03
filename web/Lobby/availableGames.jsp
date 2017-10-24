<%@ page import="WebUI.MultipleGamesManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="WebUI.BattleShipWebUI" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ArrayList<BattleShipWebUI> allGames = MultipleGamesManager.getInstance().GetGamesList();
    Boolean foundAvailableGame = false;%>
    <h3>Available Games</h3>
    <ul>
        <%for (BattleShipWebUI game: allGames) {
            if(game.isAvailable()){
                foundAvailableGame = true;%>
                <li><a href="StartGame?gameID=<%=game.getGameID()%>" class="start-game-link"><%=game.getGameName()%></a></li>
            <%}
        }%>
    </ul>
<%if (!foundAvailableGame){%>
    Ther are no available games
<%}%>