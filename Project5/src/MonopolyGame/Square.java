package MonopolyGame;

import java.io.Serializable;

/**
 * @see SquareType the enum of SquareType
 */

enum SquareType{
    /**
     * @see for Property
     */
    PROPERTY,
    /**
     * @see for chance
     */
    CHANCE,
    /**
     * @see for Tax
     */
    TAX,
    /**
     * @see for Parking
     */
    PARKING,
    /**
     * @see for Gotojail
     */
    GOTOJAIL,
    /**
     * @see for jail
     */
    JAIL
}

/**
 * @see Square square class
 */

public class Square implements Serializable {
    private SquareType type;
    private String name;
    private int position;
    private Square nextSquare;

    /**
     *
     * @param position position
     * @param name name
     * @param type type
     * @param nextSquare next square
     */

    Square(int position, String name, SquareType type, Square nextSquare) {
        this.position = position;
        this.name = name;
        this.type = type;
        this.nextSquare = nextSquare;
    }

    /**
     *
     * @return name
     */

    public String getName() {
        return name;
    }

    /**
     *
     * @return type
     */
    public SquareType getType() {
        return this.type;
    }

    /**
     *
     * @return positon
     */
    public int getPosition() {
        return position;
    }

    /**
     *
     * @return next square
     */
    public Square getNextSquare() {
        return nextSquare;
    }

    /**
     *
     * @param nextSquare nextSquare
     */

    public void setNextSquare(Square nextSquare){
        this.nextSquare = nextSquare;
    }
}
