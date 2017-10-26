package Servlets;

    import BattleShipsLogic.Definitions.GameStatus;
    import BattleShipsLogic.Definitions.PlayerName;
    import BattleShipsLogic.GameObjects.Point;
    import WebUI.BattleShipWebUI;
    import WebUI.MultipleGamesManager;

    import java.io.IOException;
    import java.io.PrintWriter;
    import java.text.ParseException;
    import javax.servlet.ServletException;
    import javax.servlet.annotation.MultipartConfig;
    import javax.servlet.annotation.WebServlet;
    import javax.servlet.http.HttpServlet;
    import javax.servlet.http.HttpServletRequest;
    import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PlayerAction", urlPatterns = {"/Action"})
@MultipartConfig(location="/", fileSizeThreshold=1024*1024,
        maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class PlayerAction extends HttpServlet {
    private HttpServletRequest theRequest;
    private HttpServletResponse theResponse;
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        theRequest = request;
        theResponse = response;
        Boolean refresh = true;
        BattleShipWebUI theGame = (BattleShipWebUI) request.getSession().getAttribute("theGame");
        if(theRequest.getSession().getAttribute("inGamePlayer") == null){
            request.getRequestDispatcher("/BattleShips").forward(request, response);
        }else if(theGame.getGameStatus() == GameStatus.OVER){
            response.getWriter().write("GameOver");
            response.getWriter().flush();
        }
        else {
            String ActionType = request.getParameter("Action");
            switch (ActionType) {
                case "Mine":
                    TryToSetAMine(theGame);
                    break;
                case "Attack":
                    AttackAPoint(theGame);
                    break;
                case "Surrender":
                    refresh = false;
                    theGame.Surrender((PlayerName)request.getSession().getAttribute("inGamePlayer"));
                    request.getSession().setAttribute("gameOverType", "Surrender");
                    response.getWriter().write("GameOver");
                    response.getWriter().flush();
                    break;
                case "Exit":
                    refresh = false;
                    theGame.UnregisterPlayer(request.getSession().getAttribute("PlayerName").toString());
                    request.getSession().setAttribute("inGamePlayer",null);
                    request.getSession().setAttribute("theGame",null);
                    response.getWriter().write("GoBack");
                    break;
            }
            if(refresh){
                getMessageToPlayer(theGame);
                request.getRequestDispatcher("/TheGame/GameRefresh.jsp").forward(request, response);
            }
        }
    }

    private void getMessageToPlayer(BattleShipWebUI theGame) {
        String PlayerMSG = theGame.getMessage((PlayerName) theRequest.getSession().getAttribute("inGamePlayer"));
        theRequest.setAttribute("PlayerMSG", PlayerMSG);
    }

    private void AttackAPoint(BattleShipWebUI theGame) {
        if(isPlayersTurn(theGame)) {
            try {
                Point attackPoint = getPointFromRequest();
                theGame.AttackAPoint(attackPoint);
                theRequest.setAttribute("MoveResult", theGame.getAttackMessage());
            } catch (NumberFormatException e) {
                theRequest.setAttribute("MoveResult", "You cannot do that");
            }
        }
    }

    private Boolean isPlayersTurn(BattleShipWebUI theGame){
        Boolean result = !theGame.isAvailable();
        if(!result){
            theRequest.setAttribute("MoveResult","Game hasnt started yet!");
        }
        else{
            result = theRequest.getSession().getAttribute("inGamePlayer") == theGame.getCurrentPlayer();
            if(!result){
                theRequest.setAttribute("MoveResult","It is not your turn!");
            }
        }
        return result;
    }

    private void TryToSetAMine(BattleShipWebUI theGame) {
        if(isPlayersTurn(theGame)) {
            try {
                Point minePoint = getPointFromRequest();
                if (!theGame.SetAMine(minePoint)) {
                    theRequest.setAttribute("MoveResult", theGame.getMinePositioningError());
                }
            } catch (NumberFormatException e) {
                theRequest.setAttribute("MoveResult", "You cannot do that");
            }
        }
    }

    private Point getPointFromRequest(){
        int x = Integer.parseInt(theRequest.getParameter("x")) -  1;
        int y = Integer.parseInt(theRequest.getParameter("y")) - 1;
        return new Point(x,y);
    }

}
