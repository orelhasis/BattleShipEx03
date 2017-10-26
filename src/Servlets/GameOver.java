package Servlets;

import BattleShipsLogic.Definitions.PlayerName;
import WebUI.BattleShipWebUI;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GameOver", urlPatterns = {"/GameOver"})
public class GameOver extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        BattleShipWebUI game = (BattleShipWebUI)request.getSession().getAttribute("theGame");
        if(game == null){
            request.getRequestDispatcher("/BattleShips").forward(request, response);
        }
        else {
            request.getRequestDispatcher("/TheGame/GameOver.jsp").forward(request, response);
            game.setPlayerOut((PlayerName) request.getSession().getAttribute("inGamePlayer"));
            game.ResetGame();
            request.getSession().setAttribute("inGamePlayer", null);
            request.getSession().setAttribute("theGame", null);
        }
    }
}
