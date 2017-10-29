package Servlets;

import WebUI.MultipleGamesManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "Logout", urlPatterns = {"/Logout"})
public class Logout extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        unregisterAndRedirect(request,response);
    }

    private void unregisterAndRedirect(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if(session != null){
            if(session.getAttribute("inGamePlayer") == null && session.getAttribute("PlayerName")!= null){
                MultipleGamesManager.getInstance().DeletePlayer(session.getAttribute("PlayerName").toString());
                session.invalidate();
            }
        }
        request.getRequestDispatcher("/BattleShips").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        unregisterAndRedirect(request,response);
    }
}
