package Servlets;

import WebUI.MultipleGamesManager;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        String userName = request.getParameter("user_name");
        if(!userName.isEmpty()){
            if(MultipleGamesManager.getInstance().addPlayer(userName)){
                session.setAttribute("PlayerName",userName);
            }
            else{
                request.setAttribute("ExtraText",userName + " Player name already exists");
            }
        }
        else {
            request.setAttribute("ExtraText","Player name cannot be empty");
        }
        request.getRequestDispatcher("/BattleShips").forward(request,response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/BattleShips").forward(request,response);
    }
}
