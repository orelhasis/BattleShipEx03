package WebUI;

import BattleShipsLogic.GameObjects.GameManager;
import com.sun.org.apache.xpath.internal.operations.Bool;

import java.io.File;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.List;

public class MultipleGamesManager {
    private ArrayList<BattleShipWebUI> allGames;
    private ArrayList<String> allPlayers;
    private int lastID;
    private static MultipleGamesManager instance;
    private MultipleGamesManager(){
        allPlayers = new ArrayList<>();
        allGames = new ArrayList<>();
        lastID = 1;
    }

    public static MultipleGamesManager getInstance(){
        if(instance == null){
            synchronized (MultipleGamesManager.class){
                if(instance == null){
                    instance = new MultipleGamesManager();
                }
            }
        }

        return instance;
    }

    public ArrayList<BattleShipWebUI> GetGamesList(){
        return allGames;
    }

    public int GetNumberOfGames(){
        return allGames.size();
    }

    public BattleShipWebUI getGame(int id){
        BattleShipWebUI res = null;
        for (BattleShipWebUI g:allGames) {
            if(g.getGameID() == id){
                res = g;
            }
        }
        return res;
    }

    public String AddNewGame(String Name,File gameFile, String Owner){
        BattleShipWebUI newGame = new BattleShipWebUI(lastID,Name,Owner);
        Boolean addSuccessfully;
        String retString = null;
        addSuccessfully = isGameNameAvailable(Name);
        if(!addSuccessfully){
            retString = "A game with this name already exists!";
        }
        else{
            newGame.SetLoadingXMLFile(gameFile);
            addSuccessfully = newGame.LoadGame();
            if(addSuccessfully){
                this.allGames.add(newGame);
                lastID++;
            }
            else{
                retString = newGame.UIloadingError;
            }
        }

        return retString;
    }

    private Boolean isGameNameAvailable(String name) {
        Boolean res = true;
        for (BattleShipWebUI game :allGames) {
            if(name.equalsIgnoreCase(game.getGameName())){
                res = false;
            }
        }
        return res;
    }

    public ArrayList<String> getAllPlayers() {
        return allPlayers;
    }

    public Boolean addPlayer(String newPlayerName){
        Boolean isPlayerExists = false;
        for (String str :allPlayers) {
            if(str.equalsIgnoreCase(newPlayerName)){
                isPlayerExists = true;
            }
        }
        if(!isPlayerExists){
            allPlayers.add(newPlayerName);
        }
        return !isPlayerExists;
    }

    public boolean DeleteGame(BattleShipWebUI game) {
        return allGames.remove(game);
    }

    public void DeletePlayer(String playerName) {
        //todo: synchronizr player deletion and loop
        allPlayers.remove(playerName);
    }
}
