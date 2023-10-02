package Enchanter.Data;

import org.powbot.api.Area;
import org.powbot.api.Tile;

import java.util.Arrays;
import java.util.Random;

import static Enchanter.Enchanter.*;

public enum BankAreas {
    DEFAULT_VALUE(3144,3510,0,3176,3480,0), //GE
    GRAND_EXCHANGE(3144,3510,0,3176,3480,0),
    VARROCK_WEST(3180,3446,0,3188,3431,0),
    VARROCK_EAST(3250,3422,0,3257,3420,0),
    EDGEVILLE(3090,3500,0,3100,3487,0 );

    private final Area bankArea;


    BankAreas(int x1, int x2, int x3, int y1, int y2, int y3){
        Tile topLeft = new Tile(x1, x2, x3);
        Tile bottomRight = new Tile(y1, y2, y3);
        this.bankArea = new Area(topLeft, bottomRight);
    }
    public Area getBankArea(){
        return bankArea;
    }
    public String getName(){
            return name();
    }
    public Tile makeTile(String bankName) {
        Tile bankTile1;
        int i, x, y;
        Tile[] bankTiles = new Tile[5];
        Random random = new Random();
        switch (BankAreas.valueOf(bankName).getName()) {
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
        return  bankTile1;
    }


}

