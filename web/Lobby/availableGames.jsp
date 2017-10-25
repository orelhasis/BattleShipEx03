<%@ page import="WebUI.MultipleGamesManager" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="WebUI.BattleShipWebUI" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%ArrayList<BattleShipWebUI> allGames = MultipleGamesManager.getInstance().GetGamesList();
String playerName = request.getSession().getAttribute("PlayerName").toString();%>
    <h3>Available Games</h3>
<%if(allGames.size() != 0){%>
    <table id="gamesTable">
        <thead>
            <tr><th><img src="../images/del.jpg" style="width:20px"></th><th>Game Name</th><th>Owner</th><th>Board Size</th><th>Game Type</th><th>Game Status</th></tr>
        </thead>
        <%for (BattleShipWebUI game: allGames) {
            Boolean isAvailable = game.isAvailable();%>
                <tr <%=isAvailable?"" : "class='inGame'"%>>
                    <td>
                        <%if(playerName == game.getGameOwner()){%>
                            <img src="../images/del.jpg" style="width:20px" onclick="javascript:delGame(<%=game.getGameID()%>)"/>
                        <%}%>
                    </td>
                    <td>
                        <%if(isAvailable){%>
                            <a href="StartGame?gameID=<%=game.getGameID()%>" class="start-game-link"><%=game.getGameName()%></a>
                        <%}else{%>
                            <%=game.getGameName()%>
                        <%}%>
                    </td>
                    <td><%=game.getGameOwner()%></td>
                    <td><%=game.getBoardSize()%></td>
                    <td><%=game.getGameType()%></td>
                    <td>
                        <%if(isAvailable){%>
                            <%=game.isPlayerRegistered()?"Player Waiting" : "Free"%>
                        <%}else{%>
                            Game Started
                        <%}%>
                    </td>
                </tr>
            <%}%>
    </table>
<%}else{%>
    There are no available games
<%}%>