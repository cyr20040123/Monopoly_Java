package MonopolyGame;

import java.io.Serializable;

/**
 * @see Colors the colors stands for each playre
 */
enum Colors implements Serializable{
    /**
     * @see for representing playre 1
     */
    RED("#F33",1),
    /**
     * @see for representing playre 2
     */
    BLUE("#33F",2),
    /**
     * @see for representing playre 3
     */
    YELLOW("#EE4",3),
    /**
     * @see for representing playre 4
     */
    GREEN("#3F3",4),
    /**
     * @see for representing playre 5
     */
    PURPLE("#E4E",5),
    /**
     * @see for representing playre 6
     */
    BLACK("#443",6);
    private final String name;
    private final int colorNumber;

    /**
     *
     * @param name the color name
     * @param colorNumber the color no
     */

    Colors(String name, int colorNumber) {
        this.name = name;
        this.colorNumber = colorNumber;
    }

    /**
     *
     * @return the color name
     */
    public String getName() {
        return this.name;
    }
}

/**
 * @see Player the Player class
 */
public class Player implements Serializable, Cloneable {

    private boolean status;//Whether this player is in game or quited/lose.
    private String name;
    private int money;
    private Square location;
    private int jailDays;
    private int playerNo;
    private boolean auto;
    private Colors color;

    private final int ORIGINALMONEY = 2000;

    /**
     *
     * @param name name
     * @param color color
     * @param location location
     */

    public Player(String name, Colors color, Square location) {
        this.status = true;
        this.name = name;
        this.money = ORIGINALMONEY;
        this.location = location;
        this.jailDays = 0;
        this.playerNo = color.ordinal()+1;
        this.auto = false;
        this.color = color;
    }

    /* [ Get Methods ] */

    /**
     *
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return money
     */
    public int getMoney() {
        return money;
    }

    /**
     *
     * @return location
     */
    public Square getLocation() {
        return location;
    }

    /**
     *
     * @return playerNo
     */
    public int getPlayerNo() {
        return playerNo;
    }

    /**
     *
     * @return Color
     */
    public String getColor() {
        return color.getName();
    }

    /**
     *
     * @return jailDays
     */
    public int getJailDays() {
        return jailDays;
    }

    /* [ Boolean Methods ] */

    /**
     *
     * @return whether online
     */
    public boolean isOnline(){
        return status;
    }

    /**
     *
     * @return whether inJail
     */
    public boolean isInJail(){
        return !(jailDays == 0);
    }

    /**
     *
     * @return whether auto
     */
    public boolean isAuto() {
        return auto;
    }

    /* [ Set Methods ] */

    /**
     *
     * @param amount the amount of money for updating
     */
    public void updateMoney(int amount) {
        this.money += amount;
    }

    /**
     *
     * @param location location
     */
    public void setLocation(Square location){
        this.location = location;
    }

    /**
     *
     * @param jailDays jaildays
     */
    public void setJailDays(int jailDays){
        this.jailDays = jailDays;
    }

    /**
     *
     * @param auto true: auto false: not auto
     */
    public void setAuto(boolean auto) {
        this.auto = auto;
    }

    /**
     * @see for quit game
     */
    public void quitGame() {
        this.status = false;
    }

}
