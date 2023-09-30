package Enchanter.Data;

import org.powbot.api.Area;
import org.powbot.api.Tile;

import static Enchanter.Enchanter.*;

public enum BankAreas {
    DEFAULT_VALUE(3159,3493,0,3170,3484,0), //GE
    GRAND_EXCHANGE(3159,3493,0,3170,3484,0),
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
    public String getBankAreaName(){
        return bankName;
    }


}

