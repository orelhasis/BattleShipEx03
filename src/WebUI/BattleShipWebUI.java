package WebUI;

import BattleShipsLogic.Definitions.PlayerName;
import BattleShipsLogic.GameObjects.GameManager;
import BattleShipsLogic.GameObjects.PlayerStatistics;
import BattleShipsLogic.GameObjects.Point;

import java.io.File;
import java.util.Observable;

public class BattleShipWebUI extends BattleShipUI {

    private File tempLoadingFile;
    String player1UserName;
    String player2UserName;
    public PlayerName RegisterPlayer(String str){
        PlayerName resultName = PlayerName.PLAYER_1;
        if(player1UserName == null || player1UserName.length() == 0){
            player1UserName = str;
        }
        else{
            player2UserName = str;
            resultName = PlayerName.PLAYER_2;
            StartGame();
        }
        return resultName;
    }

    public String GetOpponentName(PlayerName requestingPlayer){
        if (requestingPlayer == PlayerName.PLAYER_1){
            return player2UserName;
        }
        return player1UserName;
    }

    public PlayerStatistics GetOpponentStatistics(PlayerName requestingPlayer){
        if (requestingPlayer == PlayerName.PLAYER_1){
            return theGame.getPlayers()[1].getStatistics();
        }
        return theGame.getPlayers()[0].getStatistics();
    }

    public int getBoardSize(){return theGame.getBoarSize();}

    public PlayerStatistics GetPlayerStatistics(PlayerName requestingPlayer){
        if (requestingPlayer == PlayerName.PLAYER_1){
            return theGame.getPlayers()[0].getStatistics();
        }
        return theGame.getPlayers()[1].getStatistics();
    }

    public char[][] GetOponnentBoard(PlayerName requestingPlayer){
        if (requestingPlayer == PlayerName.PLAYER_1){
            return theGame.getPlayers()[1].getPlayerTrackingGrid();
        }
        return theGame.getPlayers()[0].getPlayerTrackingGrid();
    }

    public char[][] GetPlayerBoard(PlayerName requestingPlayer){
        if (requestingPlayer == PlayerName.PLAYER_1){
            return theGame.getPlayers()[0].getPlayerPrimaryGrid();
        }
        return theGame.getPlayers()[1].getPlayerPrimaryGrid();
    }

    public int GetOpponentMines(PlayerName requestingPlayer){
        if (requestingPlayer == PlayerName.PLAYER_1){
            return theGame.getPlayers()[1].getNumberOfMines();
        }
        return theGame.getPlayers()[0].getNumberOfMines();
    }

    public int GetPlayerMines(PlayerName requestingPlayer){
        if (requestingPlayer == PlayerName.PLAYER_1){
            return theGame.getPlayers()[0].getNumberOfMines();
        }
        return theGame.getPlayers()[1].getNumberOfMines();
    }

    public int GetOpponentScore(PlayerName requesingPlayer){
        if (requesingPlayer == PlayerName.PLAYER_1){
            return theGame.getPlayers()[1].getScore();
        }
        return theGame.getPlayers()[0].getScore();
    }

    public int GetPlayerScore(PlayerName requesingPlayer){
        if (requesingPlayer == PlayerName.PLAYER_1){
            return theGame.getPlayers()[0].getScore();
        }
        return theGame.getPlayers()[1].getScore();
    }

    public Boolean SetAMine(Point minePoint){
        MinePositioningError = null;
        return setMineInPosition(minePoint);
    }

    public void SetLoadingXMLFile(File xmlFile){tempLoadingFile = xmlFile;}

    public int getGameID(){
        return theGame.getGameID();
    }

    public String getGameName(){ return theGame.getGameName();}

    public BattleShipWebUI(int ID,String Name){
        theGame = new GameManager(ID,Name);
        theGame.addObserver(this);
        UIloadingError = "";
    }

    @Override
    protected Boolean loadGame() {
        UIloadingError = "";
        isAvailable = true;
        boolean isLoadedSuccessfully = true;
        if(tempLoadingFile == null){
            UIloadingError = "File Not Set";
            isLoadedSuccessfully = false;
        }else{
            isLoadedSuccessfully = loadXMLFile(tempLoadingFile);
            UIloadingError += theGame.getLoadingError();
        }
        return isLoadedSuccessfully;
    }

    @Override
    protected void showWelcomeMessage() {

    }

    @Override
    protected void showGameLoadedMessage() {

    }

    @Override
    protected void showGameLoadFailedMessage() {

    }

    public Boolean LoadGame(){
        return this.loadGame();
    }

    @Override
    public void StartGame() {
        isAvailable = false;
    }

    @Override
    protected void printGameStartsMessage() {

    }

    @Override
    protected void publishWinnerResults() {

    }

    @Override
    protected void printStatistics() {

    }

    @Override
    protected void showBoards(char[][] board, char[][] trackingboard, String attackPlayerName, String attackedPlayerName) {

    }

    @Override
    protected void showUsedMessage() {

    }
    private String MinePositioningError;

    public String getMinePositioningError(){return MinePositioningError;
    }
    @Override
    protected void showMineBadPositionMessage() {
        MinePositioningError = "A mine in this position Overlaps with another mine or ship";
    }

    @Override
    protected void showMinePositionWasHitMessage() {
        MinePositioningError = "This position was previously attacked,<br>you don't want to do that";
    }

    @Override
    protected void showDrownedMessage() {

    }

    @Override
    protected void showHitAMineMessage() {

    }

    @Override
    protected void showMissMessage() {

    }

    @Override
    protected void showAMineWasSetMessage() {
        MinePositioningError = "You have set a mine,<br>Lets wait for them to hit it!";
    }

    @Override
    protected void showHitMessage() {

    }

    @Override
    protected void showBoard(char[][] board) {

    }

    @Override
    protected String getFilePath() {
        return null;
    }

    @Override
    protected void showMinePositionIsTakenMessage() {
        MinePositioningError = "There is already a battleship or a mine here!";
    }

    @Override
    public void update(Observable o, Object arg) {

    }
    private Boolean isAvailable;
    public Boolean isAvailable() {
        return isAvailable;
    }
}