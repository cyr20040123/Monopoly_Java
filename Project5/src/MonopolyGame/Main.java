package MonopolyGame;

import java.util.InputMismatchException;
import java.util.Scanner;
import MonopolyGUI.*;

/**
 * @see Main main function
 */
public class Main {
    /**
     *
     * @param args args
     */
    public static void main(String args[]) {
        boolean GUI = false;
        int t=0;
        if( args.length==0 || args[0].equals("TEST") ) {
            boolean inputValid = false;
            while ( !inputValid ) {
                try {
                    System.out.println("Please choose the program mode:");
                    System.out.println("1. Graphical User Interface (GUI)");
                    System.out.println("2. Command-line Interface (CLI)");
                    Scanner sc = new Scanner(System.in);
                    t = sc.nextInt();
                    if (t == 1 || t == 2) inputValid = true;
                }
                catch (InputMismatchException e) { inputValid = false; }
                if( !inputValid ) System.out.println("Invalid input! Please input again.");
            }
            GUI = t==1;
            if(args.length>=1 && args[0].equals("TEST")) return;
        }
        else if(args[0].equals("GUI")) GUI = true;
        if(!GUI){
            Game game = new Game(GUI, null);
            game.run();
        }
        else{
            new MonopolyStage().main(new String[0]);
        }
    }
}
