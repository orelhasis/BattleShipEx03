package Servlets;


import WebUI.MultipleGamesManager;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@MultipartConfig(location="/", fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
@WebServlet(name = "RefreshPlayers", urlPatterns = {"/GetPlayers"})
public class RefreshPlayers extends HttpServlet {
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
            String jsonPlayers = "";
            for (String name: MultipleGamesManager.getInstance().getAllPlayers()) {
                if(jsonPlayers != ""){
                    jsonPlayers+=",";
                }
                else{
                    jsonPlayers = "{\"players\" : [";
                }
                jsonPlayers += "{\"name\" : \"" + name +"\"}" ;
            }
            jsonPlayers += "]}";
            response.getWriter().write(jsonPlayers);
            response.getWriter().flush();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RedirectBySession(request,response);
    }
}
