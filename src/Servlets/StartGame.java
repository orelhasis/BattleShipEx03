package Servlets;

import BattleShipsLogic.Definitions.PlayerName;
import WebUI.BattleShipWebUI;
import WebUI.MultipleGamesManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StartGame", urlPatterns = {"/StartGame"})
public class StartGame extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/BattleShips").forward(request, response);
    }
    private HttpServletRequest theRequest;
    private HttpServletResponse theResponse;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute("inGamePlayer") != null) {
            request.getRequestDispatcher("/TheGame/Game.jsp").forward(request, response);
        }
        else {
            //todo Synchronize this
            theRequest = request;
            theResponse = response;
            String GameID = request.getParameter("gameID");
            BattleShipWebUI theGame = null;
            int id;
            try {
                id = Integer.parseInt(GameID);
            } catch (Exception e) {
                id = -1;
            }
            if (id > 0) {
                theGame = MultipleGamesManager.getInstance().getGame(id);
            }
            if (theGame == null) {
                //handle Game Not Found
            } else {
                registerAndStartGame(theGame);
            }
        }
    }

    private void registerAndStartGame(BattleShipWebUI theGame)throws ServletException, IOException {
        if(theGame.isAvailable()){
            //todo Synchronize on theGame object
            PlayerName receivedName = theGame.RegisterPlayer(theRequest.getSession().getAttribute("PlayerName").toString());
            theRequest.getSession().setAttribute("inGamePlayer", receivedName);
            theRequest.getSession().setAttribute("theGame", theGame);
            theRequest.getRequestDispatcher("/TheGame/Game.jsp").forward(theRequest,theResponse);
        }

    }
}
