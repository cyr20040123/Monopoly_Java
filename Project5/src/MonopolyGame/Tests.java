package MonopolyGame;
import org.junit.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @see Tests Test class
 */

public class Tests {
    /**
     *@see for testing class Player and Player Comparator
     */
    @Test
    public void testPlayer(){
        final int money=2000;
        assert Colors.RED.getName().equals("#F33");
        Player player = new Player("NAME",Colors.RED,null);
        Player player1=new Player("TEST",Colors.BLACK,null);
        PlayerComparator playerComparator=new PlayerComparator();
        playerComparator.compare(player,player1);
        player1.updateMoney(1);
        playerComparator.compare(player,player1);
        assert player.getName().equals("NAME");
        assert player.getMoney()==money;
        assert player.getLocation()==null;
        assert player.getPlayerNo()==1;
        assert player.getJailDays()==0;
        assert player.getColor().equals("#F33");
        player.setJailDays(0);
        player.setLocation(null);
        player.setAuto(false);
        assert !player.isInJail();
        assert player.isOnline();
        assert !player.isAuto();
        player.updateMoney(0);
        player.quitGame();

    }
    /**
     *  @see for testing class Property and Square
     */
    @Test
    public void testProperty(){
        Property property=new Property(1,"aaa",SquareType.PROPERTY,null,0,0);
        assert property.getAscription()==null;
        assert property.getRent()==0;
        assert property.getPrice()==0;
        property.setAscription(null);
        assert property.getName().equals("aaa");
        assert property.getPosition()==1;
        assert property.getType()==SquareType.PROPERTY;
        assert property.getNextSquare()==null;
        property.setNextSquare(null);

    }
    /**
     *  @see for testing class CLI
     */
    @Test
    public void testCLI(){
        Property property=new Property(1,"aaa",SquareType.PROPERTY,null,0,0);
        Player player=new Player("NAME",Colors.RED,property);
        Player player1=new Player("NAME",Colors.BLACK,null);
        List<Player> playerList=new ArrayList<Player>();
        playerList.add(player);
        playerList.add(player1);
        String userInput;
        userInput = "a\n3\n0\n1\na\n1\n1\n4\n0\n1\n0\n1\na\na\n0\n1\n";
        byte[] bytes = userInput.getBytes();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        CLI cli2 = new CLI(inputStream);
        cli2.notify("");
        cli2.displayMessage("");
        cli2.userInputInt();
        cli2.playerUpdateMoney(player,-1);
        cli2.changePropertyAscription(property,player);
        cli2.playerGotoJail(player);
        cli2.rollDice(1,1);
        cli2.playerMove(property,property,0);
        cli2.displayResult(playerList);
        cli2.askNewOrLoadGame();
        cli2.askPlayerName(1);
        cli2.askNumberOfPlayers(2,6);
        cli2.askForBuying(null);
        cli2.askPayFine();
        cli2.loadGame();
        cli2.saveGame();
        cli2.step1ChooseOperation();
        cli2.showPlayerInfo(player);

    }
    /**
     *  @see for testing class Game
     */
    @Test
    public void testGame(){
        String userInput;
        userInput = "1\n6\naaa\nbbb\nccc\nddd\neee\nfff\n5\nsave.dat\n6\nsave.pdy\n6\nsave.dat\n2\n3\n3\n3\n3\n3\n2\n4";
        byte[] bytes = userInput.getBytes();
        InputStream inputStream = new ByteArrayInputStream(bytes);
        Game game=new Game(false,inputStream);
        game.run();
        final int testtime=50;
        final int lostmoney=-1990;
        for(int i=0;i<testtime;i++){
                String userInput1;
                userInput1 = "1\n3\naaa\nbbb\nccc\n1\n2\n1\n1\n3\n3\n3\n";
                byte[] bytes1 = userInput1.getBytes();
                InputStream inputStream1 = new ByteArrayInputStream(bytes1);
                Game game1=new Game(false,inputStream1);
                game1.test();
                for(Square a:game1.getSquareSet().values()){
                    if(a instanceof Property){
                        ((Property)a).setAscription(game1.getPlayerList().get(0));
                    }
                }
                game1.getPlayerList().get(0).setLocation(game1.getSquareSet().get(0));
                game1.getPlayerList().get(0).setJailDays(1);
                game1.getPlayerList().get(1).setLocation(game1.getSquareSet().get(0));
                game1.getPlayerList().get(1).updateMoney(lostmoney);
                game1.getPlayerList().get(1).setJailDays(3);
                game1.runGame();
        }
        String[] args = new String[1];
        args[0]="TEST";
        Main test = new Main();
        String input = "1";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        test.main(args);
        new Game(null);
    }
}
