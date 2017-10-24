package Servlets;

    import BattleShipsLogic.GameObjects.Point;
    import WebUI.BattleShipWebUI;
    import WebUI.MultipleGamesManager;

    import java.io.IOException;
    import java.io.PrintWriter;
    import java.text.ParseException;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PlayerAction", urlPatterns = {"/Action"})
public class PlayerAction extends HttpServlet {
    private HttpServletRequest theRequest;
    private HttpServletResponse theResponse;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        theRequest = request;
        theResponse = response;
        String ActionType = request.getParameter("Action");
        BattleShipWebUI theGame = (BattleShipWebUI)request.getSession().getAttribute("theGame");
        switch(ActionType){
            case "Mine":
                TryToSetAMine(theGame);
                break;
        }
    }

    private void TryToSetAMine(BattleShipWebUI theGame) {
        try{
            Point minePoint = getPointFromRequest();
            if(!theGame.SetAMine(minePoint)){
                theRequest.setAttribute("MoveError",theGame.getMinePositioningError());
            }
        }catch (ParseException e){
            theRequest.setAttribute("MoveError","You cannot do that");
        }
    }

    private Point getPointFromRequest() throws ParseException{
        int x = Integer.parseInt(theRequest.getParameter("x"));
        int y = Integer.parseInt(theRequest.getParameter("y"));
        return new Point(x,y);
    }

}
