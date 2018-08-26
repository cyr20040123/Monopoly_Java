package MonopolyGame;

import java.util.Comparator;

//To compare 2 players

/**
 * @see PlayerComparator to compare 2 players
 */
public class PlayerComparator implements Comparator<Player>{

    @Override
    public int compare(Player arg0, Player arg1) {
        if(!arg0.isOnline())return 1;
        if(!arg1.isOnline())return -1;
        int flag = arg1.getMoney() - arg0.getMoney();
        if(flag==0){
            return arg0.getPlayerNo()- arg1.getPlayerNo();
        }
        else{
            return flag;
        }
    }

}
