package MonopolyGame;

import java.io.File;
import java.util.List;

/**
 * @see UserInterface The interface of UserInterface
 */

public interface UserInterface {

    /* [ Basic Output Functions ] */

    /**
     *
     * @param msg the notyfy message
     * @see for display warning / error / notice ...
     */
    void notify(String msg); // For display warning / error / notice ...

    /**
     *
     * @param msg the message which used for display
     * @see for displaying normal message
     */
    void displayMessage(String msg); // For displaying normal message

    /* [ Basic Input Functions ] */

    /**
     *
     * @return user input string
     */
    String userInputString();

    /**
     *
     * @return user input integer
     */
    int userInputInt();

    /* [ Game Display Methods ] */

    /**
     *
     * @param player the player you want to update money
     * @param amount the changed money
     */
    void playerUpdateMoney(Player player, int amount);

    /**
     *
     * @param property the property you want to change Ascription
     * @param player the player who bought this property
     */
    void changePropertyAscription(Property property, Player player);//The player can be null

    /**
     *
     * @param player the player who goes to jail
     */
    void playerGotoJail(Player player);

    /**
     *
     * @param dice1 dice 1
     * @param dice2 dice 2
     */
    void rollDice(int dice1, int dice2);

    /**
     *
     * @param beg the begin square
     * @param end the end square
     * @param steps the steps the player go
     */
    void playerMove(Square beg, Square end, int steps);

    /**
     *
     * @param player the player you want to show his info
     */
    void showPlayerInfo(Player player);

    /**
     *
     * @param playerList the player list you want to display
     */
    void displayResult(List<Player> playerList);

    /* [ Game Input Methods ] */

    /**
     *
     * @return true:new game
     */
    boolean askNewOrLoadGame(); // true: new game

    /**
     *
     * @param MIN 2
     * @param MAX 6
     * @return the number of players
     */
    int askNumberOfPlayers(int MIN, int MAX);

    /**
     *
     * @param playerNumber the player you want to set his name
     * @return the name of this player
     */
    String askPlayerName(int playerNumber);

    /**
     *
     * @param property the property which will be bought
     * @return true: buy false: not buy
     */
    boolean askForBuying(Property property);

    /**
     *
     * @return true: pay false: not pay
     */
    boolean askPayFine();

    /**
     *
     * @return the file you want to load
     */
    File loadGame();

    /**
     *
     * @return the filepath you want to save
     */
    File saveGame();

    /* [ Game operation methods ] */

    /**
     *
     * @return the choice of the playre
     */
    int step1ChooseOperation();
}
