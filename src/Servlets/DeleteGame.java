package Servlets;

import WebUI.BattleShipWebUI;
import WebUI.MultipleGamesManager;

import javax.persistence.criteria.CriteriaBuilder;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.Response;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
@MultipartConfig
@WebServlet(name = "DeleteGame", urlPatterns = {"/Delete"})
public class DeleteGame extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/BattleShip").forward(request,response);
    }

    HttpServletRequest theRequest;
    HttpServletResponse theResponse;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        theRequest = request;
        theResponse = response;
        if(session.getAttribute("PlayerName") == null){
            request.getRequestDispatcher("/BattleShip").forward(request,response);
        }else{

            String gameToDeleteID = request.getQueryString().replace("gameID=","");
            int DeleteGameID;
            PrintWriter out = response.getWriter();
            try {
                DeleteGameID = Integer.parseInt(gameToDeleteID);
                String jsonDelResString = deleteGame(DeleteGameID);
                out.print(jsonDelResString);
                out.flush();
            }catch (NumberFormatException e){
                out.print("{\"success\":false, \"message\":\"Failed To Delete\"}");
                out.flush();
            }
        }
    }

    private String deleteGame(int gameToDeleteID) {
        String successString = "true";
        String successMSG = "Game Deleted Successfully";
        //TODO lock on game object
        BattleShipWebUI game = MultipleGamesManager.getInstance().getGame(gameToDeleteID);
        if(game == null){
            successString = "false";
            successMSG = "Game with id " + gameToDeleteID + " does not exist";
        }
        else if(!game.isAvailable()){
            successString += "false";
            successMSG = "Game has started and cannot be deleted";
        }
        else if(game.isPlayerRegistered()){
            successString += "false";
            successMSG = "Game has a registered player and cannot be deleted";
        }
        else if (game.getGameOwner() != theRequest.getSession().getAttribute("PlayerName")){
            successString += "false";
            successMSG = "You are not the owner of this game";
        }
        else if(!MultipleGamesManager.getInstance().DeleteGame(game)){
            successString += "false";
            successMSG = "Failed To Delete Game";
        }
        return "{ \"success\" : " + successString + ", \"message\" : \"" + successMSG + "\"}";
    }
}
