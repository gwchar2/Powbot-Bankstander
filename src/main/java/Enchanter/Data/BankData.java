package Enchanter.Data;

import Enchanter.Enchanter;
import org.powbot.api.Area;
import org.powbot.api.Condition;
import org.powbot.api.Locatable;
import org.powbot.api.Tile;
import org.powbot.api.rt4.Bank;
import org.powbot.api.rt4.Movement;
import org.powbot.api.rt4.Player;
import org.powbot.api.rt4.Players;
import java.util.Random;
import java.util.concurrent.Callable;

public class BankData extends Enchanter {
    private final String BankName;
    private final Area BankArea;
    private final Tile BankTile;
    public Locatable myBank;

    public BankData(Area Bank) {
        Tile bankTile1;
        this.BankArea = bankArea;
        this.BankName = bankName;
        int i, x, y;
        Tile[] bankTiles = new Tile[5];
        Random random = new Random();
        switch (BankName) {
            case "GRAND_EXCHANGE":
                bankTiles = new Tile[]{new Tile(3167, 3488), new Tile(3162, 3488), new Tile(3162, 3490), new Tile(3165, 3492), new Tile(3167, 3490)};
                i = random.nextInt(5);
                bankTile1 = bankTiles[i];
            case "VARROCK_WEST":
                x = 3185;
                y = 3436;
                for (i = 0; i < 5; i++) {
                    bankTiles[i] = new Tile(x, y);
                    y += 2;
                }
                i = random.nextInt(5);
                bankTile1 = bankTiles[i];
            case "VARROCK_EAST":
                x = 3251;
                y = 3420;
                for (i = 0; i < 6; i++) {
                    bankTiles[i] = new Tile(x, y);
                    x++;
                }
                i = random.nextInt(6);
                bankTile1 = bankTiles[i];
            case "EDGEVILLE":
                bankTiles = new Tile[]{new Tile(3096, 3494), new Tile(3098, 3494), new Tile(3094, 3491), new Tile(3094, 3498)};
                i = random.nextInt(3);
                bankTile1 = bankTiles[i];
            default:
                bankTile1 = new Tile(3167, 3488);

        }
        this.BankTile = bankTile1;
    }

    public Area getBankArea() {
        return this.BankArea;
    }

    public Tile getbankTile() {
        return this.BankTile;
    }

    public String getBankName() {
        return this.BankName;
    }

    public boolean atBank() {
        Player player = Players.local();
            if (BankArea.contains(player)) {
                getLog().info("User is at the: "+BankName +" Bank");
                if (Players.local().tile().equals(BankTile))
                    return true;
                else{
                    Movement.step(BankTile);
                    getLog().info("Moving to the correct Bank Tile!");
                    Condition.wait(()->!Players.local().inMotion(),150, 25);
                    return true;
                }
            }
            else
                getLog().info("Need to walk to the bank!");
            return false;
    }


}






