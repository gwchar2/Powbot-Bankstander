package Enchanter.Data;

public enum  Level_1 implements Enchantable {
    DEFAULT_VALUE(1637, 2550, 7),
    SAPPHIRE_RING(1637, 2550, 7),
    SAPPHIRE_BRACELET( 11071 ,  11074, 7),
    SAPPHIRE_NECKLACE(1656 ,  3853 , 7),
    SAPPHIRE_AMULET( 1694 ,  1727, 7),
    OPAL_RING( 21081,  21126 , 7),
    OPAL_BRACELET( 21117 ,  21177, 7),
    OPAL_NECKLACE( 21090 ,  21143 , 7),
    OPAL_AMULET( 21108,  21160 , 7);
    
    private final int unenchantedID;
    private final int enchantedID;
    private final int levelReq;


    Level_1(int unenchantedID, int enchantedID, int levelReq){
        this.unenchantedID = unenchantedID;
        this.enchantedID = enchantedID;
        this.levelReq = levelReq;
    }

    public int getUnenchantedID(){
        return unenchantedID;
    }

    public int getEnchantedID(){
        return enchantedID;
    }

    public int getLevelReq(){
        return levelReq;
    }
    public String getName() {
        return name();
    }



}
