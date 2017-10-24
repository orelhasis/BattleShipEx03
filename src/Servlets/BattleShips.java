package Servlets;

import WebUI.MultipleGamesManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "BattleShips", urlPatterns = {"/BattleShips"})
public class BattleShips extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RedirectBySession(request,response);
    }

    private void RedirectBySession(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        if(session.getAttribute("PlayerName") == null){
            request.getRequestDispatcher("/Login/index.jsp").forward(request,response);
        }
        else if (session.getAttribute("inGamePlayer") != null) {
            request.getRequestDispatcher("/TheGame/Game.jsp").forward(request,response);
        }else{
            request.setAttribute("AvailableGames", MultipleGamesManager.getInstance().GetGamesList());
            request.getRequestDispatcher("/Lobby/GameLobby.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RedirectBySession(request,response);
    }
}
