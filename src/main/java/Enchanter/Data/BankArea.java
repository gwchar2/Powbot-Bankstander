package Enchanter.Data;

import org.powbot.api.Area;
import org.powbot.api.Tile;

public enum  BankArea  {
    GRAND_EXCHANGE();

    private Tile topLeft;
    private Tile topRight;
    private Area area;


    BankArea(Tile topLeft, Tile topRight, Area area){
        this.topLeft = topLeft;
        this.topRight = topRight;
        this.area = area;
    }

    public Tile getTopLeft(){
        return topLeft;
    }

    public Tile getTopRight(){
        return topRight;
    }

    public Area area(){
        return area;
    }


}
