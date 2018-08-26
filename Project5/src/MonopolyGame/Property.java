package MonopolyGame;

import com.sun.istack.internal.Nullable;

import java.io.Serializable;

/**
 * @see Property Property class
 */

public class Property extends Square implements Serializable {

    private int price, rent;
    @Nullable private Player ascription;

    /**
     *
     * @param position position
     * @param name name
     * @param type type
     * @param nextSquare next Square
     * @param price price
     * @param rent rent
     */
    public Property(int position, String name, SquareType type, Square nextSquare, int price, int rent){
        super(position, name, type, nextSquare);
        this.price = price;
        this.rent = rent;
    }

    /**
     *
     * @return price
     */

    public int getPrice() {
        return price;
    }

    /**
     *
     * @return rent
     */
    public int getRent() {
        return rent;
    }

    /**
     *
     * @return ascription
     */
    public Player getAscription() {
        return ascription;
    }

    /**
     *
     * @param player the player you want to set to onw this property
     */
    public void setAscription(Player player) {
        this.ascription = player;
    }
}
