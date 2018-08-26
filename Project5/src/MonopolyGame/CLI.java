package MonopolyGame;

import java.io.File;
import java.io.InputStream;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

/**
 * @see CLI Command Line interface
 */

public class CLI implements UserInterface{
    /* [ IO parameter ] */
    private InputStream inputStream;
    private Scanner scanner;

    /* [ Basic Output Functions ] */
    @Override
    public void notify (String msg){
        System.out.println("[Note] "+msg);
    }
    @Override
    public void displayMessage (String msg){
        System.out.println(msg);
    }

    /* [ Basic Input Functions ] */
    //For output, use PrintStream
    @Override
    public String userInputString() {
        //For testing, set the InputStream to some prepared stream.
        String s;
        do {
            s = scanner.nextLine();
            s = s.trim();
            s = s.replace("\n", "");
            s = s.replace("\r", "");
        }while (s.length()<=0);
        return s;
    }
    @Override
    public int userInputInt() {
        int s = 0;
        boolean inputValid = false;
        while(!inputValid){
            try {
                s = scanner.nextInt();
                inputValid = true;
            }
            catch (InputMismatchException f) {
                inputValid = false;
                scanner.nextLine();
                this.notify("Input invalid.");
            }
        }
        return s;
    }

    /* [ Game Display Methods ] */
    @Override
    public void playerUpdateMoney (Player player, int amount) {
        char c = '+';
        if(amount<0){
            amount = -amount;
            c = '-';
        }
        System.out.println(player.getName()+" "+c+" HKD"+amount);
    }
    @Override
    public void changePropertyAscription (Property property, Player player){
        this.displayMessage("Now, "+player.getName()+" owns "+property.getName()+".");
    }
    @Override
    public void playerGotoJail (Player player){
        System.out.println("Player "+player.getName()+" is in Jail now.");
    }
    @Override
    public void rollDice (int dice1, int dice2){
        System.out.println("Roll dice: ["+dice1+", "+dice2+"]");
    }
    @Override
    public void playerMove (Square beg, Square end, int steps){
        this.displayMessage("Move "+steps+" steps: "+beg.getName()+" -> "+end.getName());
    }
    @Override
    public void showPlayerInfo (Player player){
        Square loc = player.getLocation();
        System.out.println("Player #"+player.getPlayerNo()+" "+player.getName()+" is on #"+loc.getPosition()+" "+loc.getName()+" with HKD "+player.getMoney());
    }
    @Override
    public void displayResult(List<Player> playerList){
        System.out.println("\n[ Scoreboard ]");
        int i=0;
        for(Player player : playerList){
            if(!player.isOnline())break;
            i++;
            System.out.println("Rank:"+i+" Player #"+player.getPlayerNo()+":"+player.getName()+" Money: HKD "+player.getMoney());
        }
    }

    /* [ Game Input Methods ] */
    @Override
    public boolean askNewOrLoadGame() {
        boolean inputValid;
        int s;
        do {
            this.displayMessage("Please choose to load a game or start a new game:");
            this.displayMessage("[1] New game\n[2] Load game");
            s = this.userInputInt();
            if( s!=1 && s!=2 ){
                inputValid = false;
                this.notify("Invalid input. Please enter your choice again.");
            }
            else inputValid = true;
        }while(!inputValid);
        return s==1;
    }
    @Override
    public int askNumberOfPlayers(int MIN, int MAX){
        int s;
        do {
            this.displayMessage("Please input the number of players [2-6]:");
            s = userInputInt();
            if(s<MIN || s>MAX){
                this.notify("The number of players should be 2 to 6. Please input again.");
            }
        }while(s<MIN || s>MAX);
        return s;
    }
    @Override
    public String askPlayerName(int playerNumber){
        this.displayMessage("Please input the name of Player "+playerNumber+":");
        return userInputString();
    }
    @Override
    public boolean askForBuying (Property property){
        boolean inputValid;
        int s;
        do {
            this.displayMessage("Please choose if you want to buy this property:");
            this.displayMessage("[1] Buy\n[2] Do not buy");
            s = this.userInputInt();
            if( s!=1 && s!=2 ){
                inputValid = false;
                this.notify("Invalid input. Please enter your choice again.");
            }
            else inputValid = true;
        }while(!inputValid);
        return s==1;
    }
    @Override
    public boolean askPayFine(){
        boolean inputValid;
        int s;
        do {
            this.displayMessage("Please choose if you want to pay fine immediately:");
            this.displayMessage("[1] Pay fine immediately.\n[2] Do not pay this time");
            s = this.userInputInt();
            if( s!=1 && s!=2 ){
                inputValid = false;
                this.notify("Invalid input. Please enter your choice again.");
            }
            else inputValid = true;
        }while(!inputValid);
        return s==1;
    }
    @Override
    public File loadGame (){
        this.displayMessage("Please enter a file path to load game:");
        String filePath = this.userInputString();
        return new File(filePath);
    }
    @Override
    public File saveGame (){
        this.displayMessage("Please enter a file path to save the game:");
        String filePath = this.userInputString();
        return new File(filePath);
    }

    /* [ Game operation methods ] */
    @Override
    public int step1ChooseOperation(){
        boolean inputValid;
        int s;
        do {
            this.displayMessage("Please select operation:");
            this.displayMessage(" [1] Continue (Roll dice)\n [2] Report\n [3] Set Auto\n [4] Retire\n [5] Save\n [6] Load");
            s = this.userInputInt();
            if(s<1||s>6){
                inputValid = false;
                this.notify("Invalid input. Please enter your choice again.");
            }
            else inputValid = true;
        }while(!inputValid);
        return s;
    }

    /**
     *
     * @param inputStream input Stream
     */

    public CLI(InputStream inputStream){
        if(inputStream == null) this.inputStream = System.in;
        else this.inputStream = inputStream;
        this.scanner = new Scanner(this.inputStream);
    }
}
