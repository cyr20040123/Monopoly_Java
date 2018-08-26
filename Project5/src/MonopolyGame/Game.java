package MonopolyGame;

import java.io.*;
import java.util.*;
import MonopolyGUI.*;

/* Static data of the whole map */

/**
 * @see MapData the enum of each square's info
 */
enum MapData implements Serializable{
    /**
     *  @see for Jailinfo
     */
    Jail(0, "Jail", SquareType.JAIL),
    /**
     *  @see for Goinfo
     */
    Go(1, "GO", SquareType.PARKING),
    /**
     *  @see for Centralinfo
     */
    Central(2, "Central", SquareType.PROPERTY, 850, 90),
    /**
     *  @see for WanChaiinfo
     */
    WanChai(3, "Wan Chai", SquareType.PROPERTY, 750, 70),
    /**
     *  @see for PayTaxinfo
     */
    PayTax(4, "Pay Tax", SquareType.TAX),
    /**
     *  @see for Stanleyinfo
     */
    Stanley(5, "Stanley", SquareType.PROPERTY, 650, 65),
    /**
     *  @see for Pass by jail info
     */
    PassbyJail(6, "Pass by Jail", SquareType.PARKING),
    /**
     *  @see for shekinfo
     */
    ShekO(7, "Shek O", SquareType.PROPERTY, 350, 15),
    /**
     *  @see for MongKokinfo
     */
    MongKok(8, "Mong Kok", SquareType.PROPERTY, 550, 35),
    /**
     *  @see for Chance1info
     */
    Chance1(9, "Chance 1", SquareType.CHANCE),
    /**
     *  @see for TsingYiinfo
     */
    TsingYi(10, "Tsing Yi", SquareType.PROPERTY, 450, 20),
    /**
     *  @see for FreeParkinginfo
     */
    FreeParking(11, "Free Parking", SquareType.PARKING),
    /**
     *  @see for Shatininfo
     */
    Shatin(12, "Shatin", SquareType.PROPERTY, 650, 70),
    /**
     *  @see for Chance2info
     */
    Chance2(13, "Chance 2", SquareType.CHANCE),
    /**
     *  @see for TuenMuninfo
     */
    TuenMun(14, "Tuen Mun", SquareType.PROPERTY, 350, 25),
    /**
     *  @see for Tai Po info
     */
    TaiPo(15, "Tai Po", SquareType.PROPERTY, 550, 20),
    /**
     *  @see for GotoJailinfo
     */
    GotoJail(16, "Go to Jail", SquareType.GOTOJAIL),
    /**
     *  @see for SaiKunginfo
     */
    SaiKung(17, "Sai Kung", SquareType.PROPERTY, 400, 15),
    /**
     *  @see for YuenLonginfo
     */
    YuenLong(18, "Yuen Long", SquareType.PROPERTY, 450, 25),
    /**
     *  @see for Chance 3 info
     */
    Chance3(19, "Chance 3", SquareType.CHANCE),
    /**
     *  @see for Tai Oinfo
     */
    TaiO(20, "Tai O", SquareType.PROPERTY, 650, 30);
    private String name;
    private int price;
    private int rent;
    private int position;
    private SquareType type;

    /**
     *
     * @return the SquareType
     */
    public SquareType getType(){
        return this.type;
    }

    /**
     *
     * @return name
     */
    public String getName() {
        return this.name;
    }

    /**
     *
     * @return price
     */
    public int getPrice() {
        return this.price;
    }

    /**
     *
     * @return rent
     */
    public int getRent() {
        return this.rent;
    }

    /**
     *
     * @return Position
     */
    public int getPosition() {
        return this.position;
    }

    MapData(int position, String name, SquareType type){
        this.position = position;
        this.name = name;
        this.type = type;
        this.price = 0;
        this.rent = 0;
    }
    MapData(int position, String name, SquareType type, int price, int rent){
        this.position = position;
        this.name = name;
        this.type = type;
        this.price = price;
        this.rent = rent;
    }
}


/**
 * @see Game The main function of this game
 */


public class Game implements Serializable, Runnable{
    /* [ User Interface ] */
    private UserInterface UI;

    /* [ Constants ] */
    private final int FIRSTSQUARE = 1, LASTSQUARE = 20;
    private final int JAILPOSITION = 0, JAILNEXT = 7;
    private final int MINPLAYERS = 2, MAXPLAYERS = 6;
    private final int FINE = 90, SALARY = 1500, TAXDIV = 10, ROUND = 5;
    private final int JAILDAYS = 3;
    private final int GAMELENGTH = 100;

    /* [ Compulsory Game Data / Variables ] */
    private int numberOfPlayers;
    private List<Player> playerList;
    private Map<Integer,Square> squareSet;
    private int round;
    private int roundStep;//Detail in method runGame
    private Player currentPlayer;

    /**
     * @see print for print map info
     */
    class print{
        private final int length=120;

        /**
         * @see for print line
         */
        void printline(){
            for (int i= 0;i<length;i++){
                System.out.print("-");
            }
            System.out.println();
        }

        /**
         *
         * @param a Square array
         */
        void printnum(Square a[]){
            System.out.print("|");
            for (Square anA : a) {
                System.out.printf("%10d", anA.getPosition());
                System.out.printf("%10c", '|');
            }
            System.out.println();
            System.out.print("|");
            for (Square anA : a) {
                System.out.printf("%12s", anA.getName());
                System.out.printf("%8c", '|');
            }
            System.out.println();
            System.out.print("|");
            for (Square anA : a) {
                if(anA instanceof  Property){
                    System.out.printf("%9d", ((Property)anA).getPrice());
                    System.out.print("HKD");
                    System.out.printf("%8c", '|');
                }
                else System.out.printf("%20c", '|');
            }
            System.out.println();
            System.out.print("|");
            for (Square anA : a) {
                if(anA instanceof  Property){
                    System.out.printf("%9d", ((Property)anA).getRent());
                    System.out.print("HKD");
                    System.out.printf("%8c", '|');
                }
                else System.out.printf("%20c", '|');
            }
            System.out.println();
            System.out.print("|");
            for (Square anA : a) {
                if(anA instanceof  Property){
                    if(((Property)anA).getAscription()!=null){
                        System.out.printf("%9s", ((Property)anA).getAscription().getName());
                        System.out.print(" Owns");
                        System.out.printf("%6c", '|');
                    }
                    else System.out.printf("%20c", '|');
                }
                else System.out.printf("%20c", '|');
            }
            System.out.println();
            System.out.print("|");
            for (Square anA : a) {
                StringBuilder stringBuilder=new StringBuilder();
                for (Player aPlayerList : playerList) {
                    if (!aPlayerList.isOnline()) continue;
                    if (anA.getPosition() == 6) {
                        if (aPlayerList.getLocation() == anA || aPlayerList.getLocation().getPosition() == 0) {
                            stringBuilder.append(aPlayerList.getPlayerNo() + " ");
                        }
                    } else if (aPlayerList.getLocation() == anA) {
                        stringBuilder.append(aPlayerList.getPlayerNo() + " ");
                    }
                }
                String aa=stringBuilder.toString();
                if(!aa.equals("")){
                    System.out.print("Player:");
                    System.out.printf("%2s",aa);
                    System.out.print("is here");
                    System.out.printf("%4c",'|');
                }
                else System.out.printf("%20c", '|');
            }
            System.out.println();
        }

        /**
         *
         * @param a square a
         * @param b square b
         */
        void printtwo(Square a,Square b){
            System.out.print("|");
            System.out.printf("%10d",a.getPosition());
            System.out.printf("%10c",'|');
            System.out.printf("%80c",'|');
            System.out.printf("%10d",b.getPosition());
            System.out.printf("%10c",'|');
            System.out.println();
            System.out.print("|");
            System.out.printf("%12s",a.getName());
            System.out.printf("%8c",'|');
            System.out.printf("%80c",'|');
            System.out.printf("%12s",b.getName());
            System.out.printf("%8c",'|');
            System.out.println();
            System.out.print("|");
            if(a instanceof Property){
                System.out.printf("%9d",((Property)a).getPrice());
                System.out.print("HKD");
                System.out.printf("%8c",'|');
            }
            else System.out.printf("%20c",'|');
            System.out.printf("%80c",'|');
            if(a instanceof Property){
                System.out.printf("%9d",((Property)a).getPrice());
                System.out.print("HKD");
                System.out.printf("%8c",'|');
            }
            else System.out.printf("%20c",'|');
            System.out.println();
            System.out.print("|");
            if(a instanceof Property){
                System.out.printf("%9d",((Property)a).getRent());
                System.out.print("HKD");
                System.out.printf("%8c",'|');
            }
            else System.out.printf("%20c",'|');
            System.out.printf("%80c",'|');
            if(a instanceof Property){
                System.out.printf("%9d",((Property)a).getRent());
                System.out.print("HKD");
                System.out.printf("%8c",'|');
            }
            else System.out.printf("%20c",'|');
            System.out.println();
            System.out.print('|');
            if(a instanceof  Property){
                if(((Property)a).getAscription()!=null){
                    System.out.printf("%9s", ((Property)a).getAscription().getName());
                    System.out.print(" Owns");
                    System.out.printf("%6c", '|');
                }
                else System.out.printf("%20c", '|');
            }
            else System.out.printf("%20c", '|');
            System.out.printf("%80c",'|');
            if(b instanceof  Property){
                if(((Property)b).getAscription()!=null){
                    System.out.printf("%9s", ((Property)b).getAscription().getName());
                    System.out.print(" Owns");
                    System.out.printf("%6c", '|');
                }
                else System.out.printf("%20c", '|');
            }
            else System.out.printf("%20c", '|');
            System.out.println();
            System.out.print('|');
            StringBuilder stringBuilder=new StringBuilder();
            for (Player aPlayerList : playerList) {
                if (!aPlayerList.isOnline()) continue;
                if (aPlayerList.getLocation() == a) {
                    stringBuilder.append(aPlayerList.getPlayerNo() + " ");
                }
            }
            String aa=stringBuilder.toString();
            if(!aa.equals("")){
                System.out.print("Player:");
                System.out.printf("%2s",aa);
                System.out.print("is here");
                System.out.printf("%4c",'|');
            }
            else System.out.printf("%20c", '|');
            System.out.printf("%80c",'|');
            StringBuilder stringBuilder1=new StringBuilder();
            for (Player aPlayerList : playerList) {
                if (!aPlayerList.isOnline()) continue;
                if (aPlayerList.getLocation() == b) {
                    stringBuilder1.append(aPlayerList.getPlayerNo() + " ");
                }
            }
            aa=stringBuilder1.toString();
            if(!aa.equals("")){
                System.out.print("Player:");
                System.out.printf("%2s",aa);
                System.out.print("is here");
                System.out.printf("%4c",'|');
            }
            else System.out.printf("%20c", '|');
            System.out.println();
        }

        /**
         * @see for print line
         */
        void  printline2(){
            for(int i=0;i<length/6;i++){
                System.out.print('-');
            }
            System.out.printf("%80c",'-');
            for(int i=0;i<length/6-1;i++){
                System.out.print('-');
            }
            System.out.println();
        }
    }

    /* [ Data Delivering ] */

    /**
     *
     * @return PlayreList
     */
    public List<Player> getPlayerList() {
        return playerList;
    }

    /**
     *
     * @return squareSet
     */
    public Map<Integer, Square> getSquareSet() {
        return squareSet;
    }

    /* [ Player Iteration Methods ] */

    /**
     *
     * @return next Player
     */
    private Player getNextPlayer() {
        int i=0;
        boolean foundCurrentPlayer = false;
        while(true){
            if(foundCurrentPlayer){
                if(playerList.get(i).isOnline()) break;
            }
            if(playerList.get(i) == currentPlayer) foundCurrentPlayer = true;
            i++;
            i%=numberOfPlayers;
        }
        return playerList.get(i);
    }

    /**
     * @see for seting next player
     */
    private void setNextPlayer() {
        int lastPlayerNumber = currentPlayer.getPlayerNo();
        currentPlayer = this.getNextPlayer();
        if(currentPlayer.getPlayerNo()<lastPlayerNumber) this.round+=1;
        this.roundStep = 0;
    }

    /* [ Get Random Values ] */

    /**
     *
     * @param min min number
     * @param max max number
     * @return a random Number
     */
    private int getRandomNumber(int min, int max){
        Random random = new Random();
        return random.nextInt(max-min+1) + min;
    }

    /**
     *
     * @return the rusult of rolling dices
     */
    private int[] rollDice(){
        final int DICEMIN = 1, DICEMAX = 6;
        int dice1 = getRandomNumber(DICEMIN, DICEMAX);
        int dice2 = getRandomNumber(DICEMIN, DICEMAX);
        UI.rollDice(dice1, dice2);
        int[] s = new int[2];
        s[0]=dice1;
        s[1]=dice2;
        return s;
    }

    /**
     *
     * @return true: 0, false: 1
     */
    private boolean randomChoice(){
        return getRandomNumber(0, 1) != 0;
    }

    /**
     *
     * @return the random money in square Chance
     */
    private int randomChance(){
        final int CHANCEMULTIPLE = 10, CHANCEMIN = -30, CHANCEMAX = 20;
        return getRandomNumber(CHANCEMIN, CHANCEMAX) * CHANCEMULTIPLE;
    }

    /* [ Game Operations ] */

    /**
     *
     * @return if the game is end
     */
    private boolean isGameEnds(){
        return getNextPlayer()==this.currentPlayer || this.round>GAMELENGTH;
    }

    /**
     *
     * @return if the player pay Fine
     */
    private boolean askPayFine(){
        if(this.currentPlayer.isAuto()){
            boolean s = randomChoice();
            if(s && currentPlayer.getMoney()>FINE) {
                UI.displayMessage("Auto player choose to pay fine.");
                return true;
            }
            else {
                UI.displayMessage("Auto player choose not to pay fine.");
                return false;
            }
        }
        return UI.askPayFine();
    }

    /**
     *
     * @param player the player be checked
     * @return weather the player is out
     */
    private boolean checkPlayerOut(Player player){
        if(!player.isOnline())return true;
        if(player.getMoney()<=0||!player.isOnline()){
            Square tmp = squareSet.get(FIRSTSQUARE);
            do{
                if(tmp instanceof Property){
                    if(((Property) tmp).getAscription()==player) {
                        ((Property) tmp).setAscription(null);
                        UI.changePropertyAscription(((Property) tmp),player);
                    }
                }
                tmp = tmp.getNextSquare();
            }while(tmp!=squareSet.get(FIRSTSQUARE));
            player.quitGame();
            UI.notify("Player "+player.getName()+" has no money left and is out of game.");
            return true;
        }
        return false;
    }

    /**
     *
     * @param steps the steps the current player move
     */
    private void moveCurrentPlayer(int steps){
        int i;
        Square tmp = currentPlayer.getLocation();
        for(i=1;i<=steps;i++) tmp = tmp.getNextSquare();
        UI.playerMove(currentPlayer.getLocation(), tmp, steps);
        if(currentPlayer.getLocation().getPosition()>tmp.getPosition()){
            UI.displayMessage("Player passes through GO square and gain salary of HKD 1500.");
            currentPlayer.updateMoney(SALARY);
            UI.playerUpdateMoney(currentPlayer,SALARY);
        }
        currentPlayer.setLocation(tmp);
    }

    /**
     * @see for report function, display the map and show player's position
     *
     */
    private void report(){
        Square[] squares1={squareSet.get(MapData.FreeParking.getPosition()),squareSet.get(MapData.Shatin.getPosition()),squareSet.get(MapData.Chance2.getPosition()),squareSet.get(MapData.TuenMun.getPosition()),squareSet.get(MapData.TaiPo.getPosition()),squareSet.get(MapData.GotoJail.getPosition())};
        Square[] squares2={squareSet.get(6),squareSet.get(5),squareSet.get(4),squareSet.get(3),squareSet.get(2),squareSet.get(1)};
        print a= new print();
        a.printline();
        a.printnum(squares1);
        a.printline();
        a.printtwo(squareSet.get(10),squareSet.get(MapData.SaiKung.getPosition()));
        a.printline2();
        a.printtwo(squareSet.get(9),squareSet.get(MapData.YuenLong.getPosition()));
        a.printline2();
        a.printtwo(squareSet.get(8),squareSet.get(MapData.Chance3.getPosition()));
        a.printline2();
        a.printtwo(squareSet.get(7),squareSet.get(MapData.TaiO.getPosition()));
        a.printline();
        a.printnum(squares2);
        a.printline();
    }

    /**
     *
     * @return the sorted ArrayList
     */
    public ArrayList getSortedPlayers(){
        ArrayList<Player> sortedPlayerList = new ArrayList<Player>();
        for ( Player p : playerList ) {
            sortedPlayerList.add(p);
        }
        sortedPlayerList.sort(new PlayerComparator());
        return sortedPlayerList;
    }

    /**
     * @see for show Score Board
     */
    private void showScoreBoard() {
        UI.displayResult(this.getSortedPlayers());
    }

    /**
     *  @see for showing game over
     */
    private void gameOver(){
        UI.notify("Game Over");
        this.showScoreBoard();
    }

    /* [ Main Process of the Game ] */

    /**
     * @see Main Process of the Game
     */
    public void runGame(){
        int userChoice;
        int[] dice;
        while(!this.isGameEnds()){
            UI.displayMessage("\nRound "+this.round);
            UI.showPlayerInfo(currentPlayer);
            /* Round Step 0 */
            // [1] Continue [2] Report [3] Set Auto [4] Retire [5] Save [6] load
            if(currentPlayer.isAuto())roundStep=1;
            if(roundStep==0 && !currentPlayer.isAuto()) {
                userChoice = UI.step1ChooseOperation();
                switch (userChoice){
                    case 2:
                        this.report();
                        this.showScoreBoard();
                        continue;
                    case 3:
                        currentPlayer.setAuto(true);
                        break;
                    case 4:
                        currentPlayer.quitGame();
                        this.checkPlayerOut(currentPlayer);
                        UI.notify("Player "+currentPlayer.getName()+" quits game.");
                        this.setNextPlayer();
                        continue;
                    case 5:
                        while (!this.saveGame());
                        continue;
                    case 6:
                        if(this.loadGame()){
                            UI.notify("Game loaded successfully.");
                        }
                        else UI.notify("Failed in loading game.");
                        continue;
                    default:
                        break;
                }
                roundStep++;
            }
            /* Round Step 1 */
            // roll dice and move
            if(roundStep == 1){
                dice = this.rollDice();
                // If player is in jail
                if(currentPlayer.isInJail()){
                    UI.displayMessage(currentPlayer.getName()+" is in jail ("+currentPlayer.getJailDays()+ " days left).");
                    if(dice[0]==dice[1]){
                        currentPlayer.setJailDays(0);
                    }
                    else if(this.askPayFine()){
                        if(currentPlayer.getMoney()<FINE){
                            UI.notify("You can't pay fine because your money is less than 90.");
                        }
                        else{
                            currentPlayer.setJailDays(0);
                            currentPlayer.updateMoney(-FINE);
                            UI.playerUpdateMoney(currentPlayer,-FINE);
                        }
                    }
                    if(currentPlayer.getJailDays()==1){
                        UI.displayMessage(currentPlayer.getName()+" has to pay fine on the last day.");
                        currentPlayer.updateMoney(-FINE);
                        UI.playerUpdateMoney(currentPlayer,-FINE);
                        currentPlayer.setJailDays(0);
                        if(this.checkPlayerOut(currentPlayer)){
                            this.setNextPlayer();
                            continue;
                        }
                    }
                }
                //Not in jail / just out of jail
                if(!currentPlayer.isInJail()){
                    this.moveCurrentPlayer(dice[0]+dice[1]);
                }
                roundStep++;
            }
            /* Round Step 2 */
            // Check the new square
            if(roundStep == 2){
                switch (currentPlayer.getLocation().getType()){
                    case TAX:
                        int tax = (((currentPlayer.getMoney() + ROUND) / TAXDIV) + ROUND) / TAXDIV * TAXDIV;
                        UI.displayMessage("Player "+currentPlayer.getName()+" should pay tax of HKD"+tax+".");
                        currentPlayer.updateMoney(-tax);
                        UI.playerUpdateMoney(currentPlayer,-tax);
                        break;
                    case JAIL:
                        if(currentPlayer.isInJail())currentPlayer.setJailDays(currentPlayer.getJailDays()-1);
                        break;
                    case CHANCE:
                        int chanceMoney = this.randomChance();
                        UI.displayMessage("Player "+currentPlayer.getName()+" gets a chance!");
                        currentPlayer.updateMoney(chanceMoney);
                        UI.playerUpdateMoney(currentPlayer,chanceMoney);
                        if(this.checkPlayerOut(currentPlayer)){
                            this.setNextPlayer(); continue;
                        }
                        break;
                    case GOTOJAIL:
                        UI.displayMessage("Player "+currentPlayer.getName()+" goes to jail.");
                        currentPlayer.setJailDays(JAILDAYS);
                        currentPlayer.setLocation(squareSet.get(JAILPOSITION));
                        UI.playerGotoJail(currentPlayer);
                        break;
                    case PROPERTY:
                        Property curProperty = (Property)currentPlayer.getLocation();
                        UI.displayMessage("Player "+currentPlayer.getName()+" arrives at "+curProperty.getName()+" (Price:"+curProperty.getPrice()+", Rent:"+curProperty.getRent()+").");
                        if( curProperty.getAscription() != null && curProperty.getAscription() != currentPlayer ){
                            UI.displayMessage("This property belongs to "+curProperty.getAscription().getName()+". "+currentPlayer.getName()+" should pay HKD "+curProperty.getRent()+".");
                            currentPlayer.updateMoney( -curProperty.getRent() );
                            curProperty.getAscription().updateMoney(curProperty.getRent());
                            UI.playerUpdateMoney(currentPlayer,-curProperty.getRent());
                            UI.playerUpdateMoney(curProperty.getAscription(), curProperty.getRent());
                            if(this.checkPlayerOut(currentPlayer)){
                                this.setNextPlayer();
                                continue;
                            }
                        }
                        else if(curProperty.getAscription() == null){
                            boolean buy;
                            if(currentPlayer.isAuto()){
                                buy = this.randomChoice();
                                if(buy)UI.displayMessage(currentPlayer.getName()+" auto choose to buy this property.");
                                else UI.displayMessage(currentPlayer.getName()+" auto choose not to buy this property.");
                            }
                            else buy = UI.askForBuying(curProperty);
                            if(buy) {
                                if(currentPlayer.getMoney() > curProperty.getPrice()){
                                    UI.changePropertyAscription (curProperty, currentPlayer);
                                    UI.playerUpdateMoney (currentPlayer,-curProperty.getPrice());
                                    curProperty.setAscription(currentPlayer);
                                    currentPlayer.updateMoney( -curProperty.getPrice() );
                                }
                                else{
                                    UI.displayMessage("Failed because money is not enough.");
                                }
                            }
                        }
                        else{
                            UI.displayMessage("Nothing happens.");
                        }
                        break;
                    default:
                        break;
                }
                roundStep = 0;
            }
            UI.showPlayerInfo(currentPlayer);
            this.setNextPlayer();
        }
        this.gameOver();
    }

    /* [ Save & Load Game ] */

    /**
     *
     * @return true: save succefully
     */
    private boolean saveGame() {
        File file = UI.saveGame();
        FileOutputStream fileOutputStream;
        try {
            fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
            Object tmp = this.playerList;
            objectOutputStream.writeObject(tmp);
            objectOutputStream.flush();
            tmp = this.squareSet;
            objectOutputStream.writeObject(tmp);
            objectOutputStream.flush();
            tmp = this.numberOfPlayers;
            objectOutputStream.writeObject(tmp);
            objectOutputStream.flush();
            tmp = this.round;
            objectOutputStream.writeObject(tmp);
            objectOutputStream.flush();
            tmp = this.roundStep;
            objectOutputStream.writeObject(tmp);
            objectOutputStream.flush();
            tmp = this.currentPlayer;
            objectOutputStream.writeObject(tmp);
            objectOutputStream.flush();
            objectOutputStream.close();
            fileOutputStream.close();
        }
        catch (IOException e) {
            UI.notify("Saving game failed.");
            return false;
        }
        return true;
    }

    /**
     *
     * @return true load successfully
     */
    private boolean loadGame() {
        // Load file.
        File file = this.UI.loadGame(); //Choose file.
        FileInputStream loadStream;
        try {
            loadStream = new FileInputStream(file);
        }
        catch (IOException err) {
            return false;
        }

        // Read object from stream
        Object tmp;
        try {
            ObjectInputStream objIn = new ObjectInputStream(loadStream);
            tmp = objIn.readObject();
            if(tmp instanceof List) this.playerList = (List<Player>) tmp;
            else{ objIn.close(); return false; }
            tmp = objIn.readObject();
            if(tmp instanceof HashMap) this.squareSet = (HashMap<Integer,Square>)tmp;
            else{ objIn.close(); return false; }
            tmp = objIn.readObject();
            this.numberOfPlayers = (int)tmp;
            tmp = objIn.readObject();
            this.round = (int)tmp;
            tmp = objIn.readObject();
            this.roundStep = (int)tmp;
            tmp = objIn.readObject();
            if(tmp instanceof Player) this.currentPlayer = (Player)tmp;
            else{ objIn.close(); return false; }
            objIn.close();
        }
        catch (IOException | ClassNotFoundException e) { return false; }

        // Cloning lastGame to 'this'
        //this.copyData((Game)lastGame);
        UI.notify("Game loaded successfully.");
        return true;
    }

    /* [ Initialization for New Game ] */

    /**
     *
     * @return ture: init a new game successfully
     */
    private boolean initNewGame(){
        /* Init Map */
        this.squareSet = new HashMap<Integer, Square>();
        for (MapData mapinfo:MapData.values()) {
            switch (mapinfo.getType()){
                case PROPERTY:
                    squareSet.put(mapinfo.getPosition(),new Property(mapinfo.getPosition(), mapinfo.getName(), mapinfo.getType(), null, mapinfo.getPrice(), mapinfo.getRent()));
                    break;
                default:
                    squareSet.put(mapinfo.getPosition(), new Square(mapinfo.getPosition(), mapinfo.getName(), mapinfo.getType(), null));
                    break;
            }
        }
        for(int i = 1; i <= LASTSQUARE; i++) {
            squareSet.get(i).setNextSquare(squareSet.get((i+1)%(LASTSQUARE+1)));
        }
        squareSet.get(LASTSQUARE).setNextSquare(squareSet.get(1));
        squareSet.get(JAILPOSITION).setNextSquare(squareSet.get(JAILNEXT));
        /* Init Players */
        //!!! Whether there can be computer players at the beginning of the game?
        this.playerList = new ArrayList<Player>();
        this.numberOfPlayers = UI.askNumberOfPlayers(MINPLAYERS,MAXPLAYERS);
        List<Colors> color = new ArrayList<Colors>();
        color.add(Colors.RED);
        color.add(Colors.BLUE);
        color.add(Colors.YELLOW);
        color.add(Colors.GREEN);
        color.add(Colors.PURPLE);
        color.add(Colors.BLACK);
        String playerName;
        for(int i=0;i<this.numberOfPlayers;i++){
            playerName = UI.askPlayerName(i+1);
            playerList.add(new Player(playerName,color.get(i),squareSet.get(1)));
        }
        /* Init parameter */
        this.round = 1;
        this.roundStep = 0;
        this.currentPlayer = playerList.get(0);
        return true;
    }

    /* [ Constructor ] */

    /**
     *
     * @param GUI true: GUI false:CLI
     * @param inputStream input Stream
     */
    public Game(boolean GUI, InputStream inputStream){
        if(GUI) this.UI = new MonopolyStage().getGUIinterface();//!!! delete parameter
        else this.UI = new CLI(inputStream);
    }

    /**
     * @see for testing
     */

    public void test() {
        UI.askNewOrLoadGame();
        this.initNewGame();
    }

    /**
     *
     * @param UI UserInterFace
     */
    public Game(UserInterface UI){ this.UI = UI; }

    /**
     * @see for run the game
     */

    @Override
    public void run() {
        boolean initSuccessfully = false;
        while(!initSuccessfully) {
            boolean newGame = UI.askNewOrLoadGame();
            if (newGame) initSuccessfully = this.initNewGame();
            else initSuccessfully = this.loadGame();
            if(!initSuccessfully) UI.notify("Failed in loading game.");
        }
        runGame();
    }
}
