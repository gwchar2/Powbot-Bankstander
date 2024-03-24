package Enchanter.Data;

import org.powbot.api.Area;
import org.powbot.api.Tile;
import java.util.Random;

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
        i = random.nextInt(5);
        if (bankName.compareTo("GRAND_EXCHANGE") == 0) {
            bankTiles = new Tile[]{new Tile(3167, 3488), new Tile(3162, 3488), new Tile(3162, 3490), new Tile(3162, 3487), new Tile(3167, 3490)};
            return new Tile(bankTiles[i].getX(),bankTiles[i].getY());
        }
        else if (bankName.compareTo("VARROCK_WEST") == 0 ){
                return new Tile (3185,3436+(i*2));
        }
        else if (bankName.compareTo("VARROCK_EAST") == 0 ) {
            return new Tile(3250+ (i * 2), 3420 );
        }
        else if (bankName.compareTo("EDGEVILLE") == 0 ) {
            bankTiles = new Tile[]{new Tile(3096, 3494), new Tile(3098, 3494), new Tile(3094, 3491), new Tile(3094, 3498)};
            return new Tile(bankTiles[i].getX(),bankTiles[i].getY());
        }
        else
            return new Tile(3167, 3488);
    }


}

