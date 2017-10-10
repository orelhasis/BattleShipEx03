package Servlets;

import BattleShipsLogic.Definitions.GameStatus;
import BattleShipsLogic.GameObjects.GameManager;
import javafx.scene.control.Button;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "GameServlet", urlPatterns = {"/game_servlet"})
public class GameServlet extends HttpServlet {

    private static final long NANO_SECONDS_IN_SECOND = 1000000000;
    private GameManager theGame;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleRequest(request, response);
    }

    private void handleRequest(HttpServletRequest request, HttpServletResponse response) {
        String action = request.getParameter("action_name");

        // To Do: get the game game object.

        switch (action) {
            case "start_game":
                startGame(request, response);
                break;
            case "show_statistics":
                showStatistics(request, response);
                break;
        }
    }

    private void startGame(HttpServletRequest request, HttpServletResponse response) {

        theGame.LoadGame(theGame.getGameSettings());
        theGame.setStartTime((int)(System.nanoTime()/NANO_SECONDS_IN_SECOND));
        theGame.setCurrentTurnStartTimeInSeconds((int)(System.nanoTime()/NANO_SECONDS_IN_SECOND));
        theGame.setStatus(GameStatus.RUN);

        // To Do: show boards and game info.
    }

    private void showStatistics(HttpServletRequest request, HttpServletResponse response) {

        String statistics,  nl = System.getProperty("line.separator");
        statistics = "Total number of turns: " + (theGame.getPlayers()[0].getStatistics().getNumberOfTurns() + theGame.getPlayers()[1].getStatistics().getNumberOfTurns()) + nl;

        if (theGame.getStatus() == GameStatus.OVER) {
            statistics += "Game total time: " + calcTime((theGame.getEndTimeInSeconds() - theGame.getStartTime())) + nl;
        }
        else {
            statistics += "Time elapsed: " + calcTime((int) ((System.nanoTime()/NANO_SECONDS_IN_SECOND) - theGame.getStartTime())) + nl;
        }
        statistics += "Number of hits: " + theGame.getPlayers()[0].getName() + " - " + theGame.getPlayers()[0].getStatistics().getNumberOfHits() + ", " + theGame.getPlayers()[1].getName() + " - " + theGame.getPlayers()[1].getStatistics().getNumberOfHits() + "." + nl;
        statistics += "Number of misses: " + theGame.getPlayers()[0].getName() + " - " + theGame.getPlayers()[0].getStatistics().getNumberOfMissing() + ", " + theGame.getPlayers()[1].getName() + " - " + theGame.getPlayers()[1].getStatistics().getNumberOfMissing() + "." + nl;
        statistics += "Average time for attack: " + theGame.getPlayers()[0].getName() + " - " + calcTime(theGame.getPlayers()[0].getStatistics().getAverageTimeForTurn()) + ", " + theGame.getPlayers()[1].getName() + " - " + calcTime(theGame.getPlayers()[1].getStatistics().getAverageTimeForTurn()) + "." + nl;

        if(theGame.getStatus()!=GameStatus.OVER) {
            statistics += "Remaining ships to destroy: " + theGame.getRemainingShipsToDestroy() + "." + nl;
        }

        // To Do: show the statistics string on the screen.

    }

    private String calcTime(int numberOfSeconds) {
        String secondsStr = (Integer.toString(numberOfSeconds%60));
        String minutesStr = (Integer.toString(numberOfSeconds/60));
        if(secondsStr.length()==1){
            secondsStr = '0' + secondsStr;
        }
        if(minutesStr.length()==1){
            minutesStr = '0' + minutesStr;
        }
        return minutesStr + ":" + secondsStr;
    }
}
